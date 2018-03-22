package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.finance.invoice.FindAddInvoiceStudentListService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Service("findAddInvoiceStudentListService")
public class FindAddInvoiceStudentListServiceImpl extends EntityServiceImpl implements FindAddInvoiceStudentListService {

    @Resource
    private FindListByWhereDAO findAddInvoiceStudentListDAO;

    @Override
    @Transactional
    public JSONArray find(Map<String, String> params) {
        List<Object[]> resultList = findAddInvoiceStudentListDAO.findListByWhere(params, null);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                BigDecimal pay  = new BigDecimal(objs[4]+"").setScale(2, BigDecimal.ROUND_UP);
                BigDecimal money  = new BigDecimal(objs[5]+"").setScale(2, BigDecimal.ROUND_UP);
                BigDecimal openMoney = pay.subtract(money).setScale(2, BigDecimal.ROUND_UP);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("studentCode", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("specName", SpecEnum.getDescn(objs[2]+""));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[3]+""));
                jsonObject.put("pay", pay);
                jsonObject.put("money", money);
                jsonObject.put("openMoney",  openMoney);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
