package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.kuaidi.push.FindKuaidiPushByNuDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForWXByCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForWXByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.domain.kuaidi.KuaidiPush;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForWXByCodeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/5/24 0024.
 */
@Service("findStudentBookOrderForWXByCodeService")
public class FindStudentBookOrderForWXByCodeServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO> implements FindStudentBookOrderForWXByCodeService {

    @Resource
    private FindStudentBookOrderForWXByCodeDAO findStudentBookOrderForWXByCodeDAO;
    @Resource
    private FindKuaidiPushByNuDAO findKuaidiPushByNuDAO;
    @Resource
    private FindStudentBookOrderForWXByStudentCodeDAO findStudentBookOrderForWXByStudentCodeDAO;

    @Override
    public List<JSONObject> find(String code) {
        List<JSONObject> returnList =new ArrayList<JSONObject>();
        List<Object[]> list = findStudentBookOrderForWXByCodeDAO.find(code);
        if(null != list && 0 < list.size()){
            for(Object[] objs : list){
                String orderCode = objs[1].toString();
                String state = objs[2].toString();
                String nu = null == objs[3] ? "" : objs[3].toString();
                int flag = Integer.parseInt(objs[6].toString());

                JSONObject json = new JSONObject();
                json.put("orderCode", orderCode);
                if("1".equals(state)){
                    json.put("state", "已确认");
                }
                //学生订单
                if(0 == flag){
                    if("2".equals(state)){
                        json.put("state", "分拣中");
                    }
                    if("3".equals(state)){
                        json.put("state", "已打包");
                    }
                    if("4".equals(state)){
                        json.put("state", "已发出");
                    }
                    if("5".equals(state)){
                        json.put("state", "已签收");
                    }
                }
                //一次性订单
                if(1 == flag){
                    if("2".equals(state)){
                        json.put("state", "处理中");
                    }
                    if("3".equals(state)){
                        json.put("state", "分拣中");
                    }
                    if("4".equals(state)){
                        json.put("state", "已打包");
                    }
                    if("5".equals(state)){
                        json.put("state", "已发出");
                    }
                    if("6".equals(state)){
                        json.put("state", "已签收");
                    }
                }

                if(!StringUtils.isEmpty(nu)){
                    String[] nus = nu.split(",");
                    if(null != nus && 0 < nus.length) {
                        KuaidiPush kuaidiPush = findKuaidiPushByNuDAO.find(nus[0]);
                        if(null != kuaidiPush){
                            JSONArray kuaidiJSON = JSONArray.fromObject(kuaidiPush.getData());
                            if(null != kuaidiJSON && 0 < kuaidiJSON.size()){
                                JSONObject jsonObject = (JSONObject) kuaidiJSON.get(0);
                                json.put("kuaidi", jsonObject.get("context"));
                            }
                        }
                    }
                }
                returnList.add(json);
            }
        }
        return returnList;
    }

    @Override
    @Transactional
    public List<JSONObject> findAll(String code) {
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        List<Object[]> list = findStudentBookOrderForWXByStudentCodeDAO.find(code);
        if(null != list){
            for(Object[] obj : list){
                String year = obj[0].toString();
                String quarter = obj[1].toString();
                String orderCode = obj[2].toString();
                String state = obj[3].toString();
                String count = obj[4].toString();
                String money = new BigDecimal(obj[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                String nu = null == obj[6] ? "" : obj[6].toString();
                int flag = Integer.parseInt(obj[8].toString());
                long orderId = Long.parseLong(obj[9].toString());

                JSONObject json = new JSONObject();
                json.put("semester", year+("0".equals(quarter) ? "上学期" : "下学期"));
                json.put("orderCode", orderCode);
                if("1".equals(state)){
                    json.put("state", "已确认");
                }
                //学生订单
                if(0 == flag){
                    if("2".equals(state)){
                        json.put("state", "分拣中");
                    }
                    if("3".equals(state)){
                        json.put("state", "已打包");
                    }
                    if("4".equals(state)){
                        json.put("state", "已发出");
                    }
                    if("5".equals(state)){
                        json.put("state", "已签收");
                    }
                }
                //一次性订单
                if(1 == flag){
                    if("2".equals(state)){
                        json.put("state", "处理中");
                    }
                    if("3".equals(state)){
                        json.put("state", "分拣中");
                    }
                    if("4".equals(state)){
                        json.put("state", "已打包");
                    }
                    if("5".equals(state)){
                        json.put("state", "已发出");
                    }
                    if("6".equals(state)){
                        json.put("state", "已签收");
                    }
                }
                json.put("count", count);
                json.put("money", money);
                json.put("flag", flag);
                json.put("orderId", orderId);
                if(!StringUtils.isEmpty(nu) && Integer.parseInt(state) < 5){
                    String[] nus = nu.split(",");
                    if(null != nus && 0 < nus.length) {
                        KuaidiPush kuaidiPush = findKuaidiPushByNuDAO.find(nus[0]);
                        if(null != kuaidiPush){
                            JSONArray kuaidiJSON = JSONArray.fromObject(kuaidiPush.getData());
                            if(null != kuaidiJSON && 0 < kuaidiJSON.size()){
                                JSONObject jsonObject = (JSONObject) kuaidiJSON.get(0);
                                json.put("kuaidi", jsonObject.get("context"));
                            }
                        }
                    }
                }
                jsonList.add(json);
            }
        }
        return jsonList;
    }

    @Override
    public List<JSONObject> findNotConfirm(String code) {
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        List<Object[]> list = findStudentBookOrderForWXByStudentCodeDAO.findNotConfrim(code);
        if(null != list){
            for(Object[] obj : list){
                String year = obj[0].toString();
                String quarter = obj[1].toString();
                String orderCode = obj[2].toString();
                String count = obj[4].toString();
                String money = new BigDecimal(obj[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                int flag = Integer.parseInt(obj[8].toString());

                JSONObject json = new JSONObject();
                json.put("semester", year+("0".equals(quarter) ? "上学期" : "下学期"));
                json.put("orderCode", orderCode);
                json.put("state", "未确认");
                json.put("count", count);
                json.put("money", money);
                json.put("flag", flag);
                jsonList.add(json);
            }
        }
        return jsonList;
    }
}
