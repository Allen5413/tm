package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.statis.FindOrderCountBySemesterIdDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.statis.FindOrderConfirmService;
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
 * Created by Allen on 2015/9/6.
 */
@Service("findOrderConfirmService")
public class FindOrderConfirmServiceImpl extends EntityServiceImpl implements FindOrderConfirmService {

    @Resource
    private FindListByWhereDAO findOrderConfirmDAO;
    @Resource
    private FindOrderCountBySemesterIdDAO findOrderCountBySemesterIdDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional
    public JSONObject findListByWhere()throws Exception{
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();
        //查询当前学期的总订单数量
        Long studentOrderCount = findOrderCountBySemesterIdDAO.findStudentOrderCountBySemesterId(semester.getId());
        //总订单数
        int orderCount = studentOrderCount.intValue();
        //未确认的订单数
        int unConfirmCount = orderCount;

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("semesterId", semester.getId()+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("date", true);
        JSONObject result = new JSONObject();
        //查询已确认的订单数据
        List<Object[]> resultList = findOrderConfirmDAO.findListByWhere(paramsMap, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                String date = objs[0].toString();
                int confirmCount = Integer.parseInt(objs[1].toString());
                jsonObject.put("date", date);
                jsonObject.put("confirmCount", confirmCount);
                jsonObject.put("percent", new BigDecimal(100 * confirmCount).divide(new BigDecimal(orderCount), 2, BigDecimal.ROUND_HALF_UP)+"%");
                jsonArray.add(jsonObject);
                unConfirmCount -= confirmCount;
            }
            result.put("confirmOrderList", jsonArray);
        }
        result.put("unConfirmOrderCount", unConfirmCount);
        result.put("unConfirmOrderPercent", new BigDecimal(100 * unConfirmCount).divide(new BigDecimal(orderCount), 2, BigDecimal.ROUND_HALF_UP)+"%");
        result.put("semesterStr", semester.getSemester2());
        return result;
    }
}
