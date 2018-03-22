package com.zs.service.finance.studentexpensebuy.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpensebuy.FindStudentExpenseBuyByCodeDAO;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.service.finance.studentexpensebuy.FindStudentExpenseBuyByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/25.
 */
@Service("findStudentExpenseBuyByCodeService")
public class FindStudentExpenseBuyByCodeServiceImpl
    extends EntityServiceImpl<StudentExpenseBuy, FindStudentExpenseBuyByCodeDAO>
    implements FindStudentExpenseBuyByCodeService {

    @Resource
    private FindStudentExpenseBuyByCodeDAO findStudentExpenseBuyByCodeDAO;

    @Override
    public Map<String, Map<Double, List<StudentExpenseBuy>>> getStudentExpenseBuyByCode(String code) {
        Map<String, Map<Double, List<StudentExpenseBuy>>> map = new HashMap<String, Map<Double, List<StudentExpenseBuy>>>();
        List<StudentExpenseBuy> resultList = findStudentExpenseBuyByCodeDAO.getStudentExpenseBuyByCode(code);
        if(null != resultList && 0 < resultList.size()){
            for (StudentExpenseBuy studentExpenseBuy : resultList){
                String semester = studentExpenseBuy.getSemester().getSemester();
                Double totalBuy = null;
                Map<Double, List<StudentExpenseBuy>> map2 = null;
                List<StudentExpenseBuy> studentExpenseBuyList = null;
                //判断该学期下时候有值
                if(null == map.get(semester)){
                    map2 = new HashMap<Double, List<StudentExpenseBuy>>();
                    map2.put(0d, new ArrayList<StudentExpenseBuy>());
                }else{
                    map2 = map.get(semester);
                }

                //得到之前该学期下的值
                for (Map.Entry<Double, List<StudentExpenseBuy>> entry : map2.entrySet()) {
                    totalBuy = entry.getKey();
                    studentExpenseBuyList = entry.getValue();
                }

                //重新计算新的值
                totalBuy = new BigDecimal(totalBuy).add(new BigDecimal(studentExpenseBuy.getMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                studentExpenseBuyList.add(studentExpenseBuy);
                map2 = new HashMap<Double, List<StudentExpenseBuy>>(1);
                map2.put(totalBuy, studentExpenseBuyList);

                //存入新的值
                map.put(semester, map2);
            }
        }
        return map;
    }
}
