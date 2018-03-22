package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.sync.student.FindStudentForFinanceAccListService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/5 0005.
 */
@Service("findStudentForFinanceAccListService")
public class FindStudentForFinanceAccListServiceImpl extends EntityServiceImpl
        implements FindStudentForFinanceAccListService {

    @Resource
    private FindListByWhereDAO findStudentForFinanceAccListDAO;

    @Override
    @Transactional
    public JSONArray find(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object[]> resultList = findStudentForFinanceAccListDAO.findListByWhere(paramsMap, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                double pay = null == objs[4] ? 0 : Double.parseDouble(objs[4].toString());
                double buy = null == objs[5] ? 0 : Double.parseDouble(objs[5].toString());
                double money = null == objs[6] ? 0 : Double.parseDouble(objs[6].toString());
                double acc = new BigDecimal(pay).subtract(new BigDecimal(buy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double refundMoney = 0;
                if(money <= buy){
                    refundMoney = acc;
                }else{
                    refundMoney = new BigDecimal(pay).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("specName", SpecEnum.getDescn(objs[2].toString()));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[3].toString()));
                jsonObject.put("pay", pay);
                jsonObject.put("buy", buy);
                jsonObject.put("acc", acc);
                jsonObject.put("money", money);
                jsonObject.put("canRefundMoney", refundMoney);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
