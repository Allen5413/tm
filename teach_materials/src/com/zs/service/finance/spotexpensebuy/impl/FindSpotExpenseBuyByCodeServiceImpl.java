package com.zs.service.finance.spotexpensebuy.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.finance.spotexpensebuy.FindSpotExpenseBuyByCodeDAO;
import com.zs.domain.finance.SpotExpenseBuy;
import com.zs.service.finance.spotexpensebuy.FindSpotExpenseBuyByCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Allen on 2015/6/8.
 */
@Service("findSpotExpenseBuyByCodeService")
public class FindSpotExpenseBuyByCodeServiceImpl
    extends EntityServiceImpl
    implements FindSpotExpenseBuyByCodeService {

    @Resource
    private FindSpotExpenseBuyByCodeDAO findSpotExpenseBuyByCodeDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional
    public Map<String, Map<Double, List<SpotExpenseBuy>>> getSpotExpenseBuyByCode(String code) {
        Map<String, Map<Double, List<SpotExpenseBuy>>> map = new HashMap<String, Map<Double, List<SpotExpenseBuy>>>();

        List<SpotExpenseBuy> list = findSpotExpenseBuyByCodeDAO.find(code);
        if (null != list && 0 < list.size()){
            for(SpotExpenseBuy spotExpenseBuy : list){
                String semester = findNowSemesterDAO.get(spotExpenseBuy.getSemesterId()).getSemester();
                Double totalBuy = null;
                Map<Double, List<SpotExpenseBuy>> map2 = null;
                List<SpotExpenseBuy> spotExpenseBuyList = null;
                if(null == map.get(semester)){
                    map2 = new HashMap<Double, List<SpotExpenseBuy>>();
                    map2.put(0d, new ArrayList<SpotExpenseBuy>());
                }else{
                    map2 = map.get(semester);
                }

                //得到之前该学期下的值
                for (Map.Entry<Double, List<SpotExpenseBuy>> entry : map2.entrySet()) {
                    totalBuy = entry.getKey();
                    spotExpenseBuyList = entry.getValue();
                }

                //重新计算新的值
                totalBuy = new BigDecimal(totalBuy).add(new BigDecimal(spotExpenseBuy.getMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                spotExpenseBuyList.add(spotExpenseBuy);
                map2 = new HashMap<Double, List<SpotExpenseBuy>>(1);
                map2.put(totalBuy, spotExpenseBuyList);

                //存入新的值
                map.put(semester, map2);
            }
        }
        return map;
    }
}
