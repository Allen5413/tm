package com.zs.service.sale.studentbookordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxPrintSortDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Student;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMPrintByOrderIdService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/9.
 */
@Service("findStudentBookOrderTMPrintByOrderIdService")
public class FindStudentBookOrderTMPrintByOrderIdServiceImpl extends EntityServiceImpl
        implements FindStudentBookOrderTMPrintByOrderIdService {

    @Resource
    private FindListByWhereDAO findStudentBookOrderTMPrintByOrderIdDAO;
    @Resource
    private FindStudentBookOrderForMaxPrintSortDAO findStudentBookOrderForMaxPrintSortDAO;
    @Resource
    private StudentBookOrderDAO studentBookOrderDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;

    @Override
    @Transactional
    public JSONObject findStudentBookOrderTMPrintByOrderId(String... ids)throws Exception {
        Map<String, String> paramsMap = new HashMap<String, String>();
        String idsStr = "";
        if(null != ids){
            for(int i=0; i< ids.length; i++){
                if(i == ids.length - 1){
                    idsStr += ids[i];
                }else{
                    idsStr += ids[i]+",";
                }
            }
        }
        paramsMap.put("ids", idsStr);
        paramsMap.put("state", StudentBookOrder.STATE_SEND+"");
        List<Object[]> resultList = findStudentBookOrderTMPrintByOrderIdDAO.findListByWhere(paramsMap, null);
        JSONObject result = new JSONObject();
        if(null != resultList && 0 < resultList.size()){
            String beforeOrderCode = "";
            int i = 0;
            int orderTmCount = 0;
            float orderTotalPrice = 0;
            for(Object[] objs : resultList){
                //订单号
                String orderCode = objs[4].toString();
                int state = Integer.parseInt(objs[5].toString());
                float price = Float.parseFloat(objs[10].toString());
                int count = Integer.parseInt(objs[11].toString());

                JSONObject jsonObject = new JSONObject();
                JSONArray tmJSONArray = new JSONArray();

                //如果当前订单号与之前不一样，填订单信息
                if(i == 0 || !beforeOrderCode.equals(orderCode)){
                    //订单填值
                    jsonObject.put("specName", objs[0]);
                    jsonObject.put("levelName", objs[1]);
                    jsonObject.put("code", objs[2]);
                    jsonObject.put("name", objs[3]);
                    jsonObject.put("orderCode", orderCode);
                    jsonObject.put("state", state);
                    jsonObject.put("year", objs[12]);
                    jsonObject.put("quarter", objs[13]);
                    jsonObject.put("spotCode", objs[14]);
                    jsonObject.put("spotName", objs[15]);
                    jsonObject.put("printSort", objs[16]);
                    if(0 < i){
                        //把计算后的订单教材总数量和订单总价 设置到之前的订单中；并清0，重新计算后面的订单
                        JSONObject jsonObject1 = (JSONObject) result.get(beforeOrderCode);
                        jsonObject1.put("orderTmCount", orderTmCount);
                        jsonObject1.put("orderTotalPrice", orderTotalPrice);
                        orderTmCount = 0;
                        orderTotalPrice = 0;
                    }
                }else{
                    jsonObject = (JSONObject) result.get(orderCode);
                    tmJSONArray = (JSONArray) jsonObject.get("orderTM");
                }

                //订单明细填值
                JSONObject tmJSONObject = new JSONObject();
                tmJSONObject.put("courseCode", objs[6]);
                tmJSONObject.put("courseName", objs[7]);
                tmJSONObject.put("tmName", objs[8]);
                tmJSONObject.put("author", objs[9]);
                tmJSONObject.put("price", price);
                tmJSONObject.put("count", count);
                tmJSONArray.add(tmJSONObject);
                jsonObject.put("orderTM", tmJSONArray);

                result.put(orderCode, jsonObject);

                beforeOrderCode = orderCode;

                //计算该订单的教材数量
                orderTmCount += count;
                //计算该订单的教材总价
                orderTotalPrice = new BigDecimal(orderTotalPrice).add(new BigDecimal(new BigDecimal(price).multiply(new BigDecimal(count)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue())).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                //如果执行到最后一条数据
                if(i == resultList.size() - 1){
                    //把计算后的订单教材总数量和订单总价 设置到之前的订单中；并清0，重新计算后面的订单
                    JSONObject jsonObject1 = (JSONObject) result.get(orderCode);
                    jsonObject1.put("orderTmCount", orderTmCount);
                    jsonObject1.put("orderTotalPrice", orderTotalPrice);
                }
                i++;
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void editPrintSort(String... ids) throws Exception {
        //设置打印的编号
        int printSort = 0;
        for(String id : ids){
            StudentBookOrder studentBookOrder = studentBookOrderDAO.get(Long.parseLong(id));
            if(null != studentBookOrder && null == studentBookOrder.getPrintSort() && studentBookOrder.getState() >= StudentBookOrder.STATE_SEND) {
                Student student = findStudentByCodeDAO.getStudentByCode(studentBookOrder.getStudentCode());
                //查询当前最大的顺序号
                Integer maxSort = findStudentBookOrderForMaxPrintSortDAO.findStudentBookOrderForMaxPrintSort(studentBookOrder.getSemesterId(), student.getSpotCode());
                if(null == maxSort){
                    maxSort = 0;
                }
                printSort++;
                studentBookOrder.setPrintSort(maxSort + printSort);
                studentBookOrderDAO.update(studentBookOrder);
            }
        }
    }
}
