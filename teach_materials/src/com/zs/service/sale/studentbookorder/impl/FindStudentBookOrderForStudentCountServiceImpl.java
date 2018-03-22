package com.zs.service.sale.studentbookorder.impl;

import com.zs.tools.StringTools;
import  net.sf.json.JSONObject;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForStudentCountDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForStudentCountService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
@Service("findStudentBookOrderForStudentCountService")
public class FindStudentBookOrderForStudentCountServiceImpl extends EntityServiceImpl implements FindStudentBookOrderForStudentCountService {

    @Resource
    private FindStudentBookOrderForStudentCountDAO findStudentBookOrderForStudentCountDAO;

    @Override
    public List<JSONObject> find(long semesterId, String spotCode, int state, int countNum) throws Exception {
        List<JSONObject> list = new ArrayList<JSONObject>();
        if(state == StudentBookOrder.STATE_CONFIRMED) {
            BigInteger orderCount = null;
            if(StringUtils.isEmpty(spotCode)) {
                orderCount = findStudentBookOrderForStudentCountDAO.findOrderCount(semesterId, state);
            }else{
                orderCount = findStudentBookOrderForStudentCountDAO.findOrderCount(semesterId, state, spotCode);
            }
            int pageCountNum = orderCount.intValue() % countNum > 0 ? (orderCount.intValue() / countNum) + 1 : orderCount.intValue() / countNum;
            for (int i = 0; i < pageCountNum; i++) {
                int pageNum = i * countNum;
                Object[] objs = null;
                if(StringUtils.isEmpty(spotCode)) {
                    objs = findStudentBookOrderForStudentCountDAO.find(semesterId, state, pageNum, countNum);
                }else{
                    objs = findStudentBookOrderForStudentCountDAO.find(semesterId, state, pageNum, countNum, spotCode);
                }
                if (null != objs) {
                    JSONObject json = new JSONObject();
                    json.put("orderCount", objs[0].toString());
                    json.put("tmCount", objs[1].toString());
                    json.put("priceTotal", objs[2].toString());
                    list.add(json);
                }
            }
        }else{
            //查询已打印的，需要按打印时间统计
            List<Object[]> resultList = null;
            if(StringUtils.isEmpty(spotCode)) {
                resultList = findStudentBookOrderForStudentCountDAO.findPrint(semesterId, state);
            }else{
                resultList = findStudentBookOrderForStudentCountDAO.findPrint(semesterId, state, spotCode);
            }
            if(null != resultList && 0 < resultList.size()) {
                for(Object[] objs : resultList) {
                    JSONObject jsonObject = new JSONObject();
                    //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                    StudentBookOrder studentBookOrder = new StudentBookOrder();
                    studentBookOrder.setOperateTime((Date) objs[3]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    jsonObject = JSONObject.fromObject(studentBookOrder, jsonConfig);
                    jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                    jsonObject.put("operator", objs[4]);

                    jsonObject.put("orderCount", objs[0]);
                    jsonObject.put("tmCount", objs[1]);
                    jsonObject.put("priceTotal", StringTools.getFinancePrice(objs[2].toString()));
                    list.add(jsonObject);
                }
            }
        }
        return list;
    }
}
