package com.zs.service.finance.refundstudent.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.finance.refundstudent.FindRefundStudentByCodeDAO;
import com.zs.domain.finance.RefundStudent;
import com.zs.service.finance.refundstudent.FindRefundStudentByCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/7.
 */
@Service("findRefundStudentByCodeService")
public class FindRefundStudentByCodeServiceImpl extends EntityServiceImpl<RefundStudent, FindRefundStudentByCodeDAO>
        implements FindRefundStudentByCodeService{

    @Resource
    private FindRefundStudentByCodeDAO findRefundStudentByCodeDAO;

    @Override
    @Transactional
    public Map<String, Object> find(String code) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        List<Object[]> list = findRefundStudentByCodeDAO.find(code);
        BigDecimal totalMoney = new BigDecimal(0);
        BigDecimal passTotalMoney = new BigDecimal(0);
        BigDecimal notPassTotalMoney = new BigDecimal(0);
        if(null != list && 0 < list.size()){
            for(Object[] obj : list){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", obj[0]);
                map.put("studentCode", obj[1]);
                map.put("name", obj[2]);
                map.put("specName", SpecEnum.getDescn(obj[3].toString()));
                map.put("levelName", LevelEnum.getDescn(obj[4].toString()));
                map.put("state", obj[5]);
                map.put("money", obj[6]);
                map.put("refundDetail", obj[7]);
                map.put("auditDetail", obj[8]);
                map.put("operator", obj[9]);
                map.put("operateTime", obj[10]);
                returnList.add(map);
                totalMoney = totalMoney.add(new BigDecimal(obj[6]+"")).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(Integer.parseInt(obj[5].toString()) == 1){
                    passTotalMoney = passTotalMoney.add(new BigDecimal(obj[6]+"")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                if(Integer.parseInt(obj[5].toString()) == 2){
                    notPassTotalMoney = notPassTotalMoney.add(new BigDecimal(obj[6]+"")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        returnMap.put("list", returnList);
        returnMap.put("totalMoney", totalMoney);
        returnMap.put("passTotalMoney", passTotalMoney);
        returnMap.put("notPassTotalMoney", notPassTotalMoney);
        return returnMap;
    }
}
