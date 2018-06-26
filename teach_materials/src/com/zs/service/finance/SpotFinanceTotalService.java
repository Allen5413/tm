package com.zs.service.finance;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpense;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 查询学习中心财务总计
 * Created by Allen on 2015/12/7.
 */
public interface SpotFinanceTotalService extends EntityService<SpotExpense> {
    public JSONObject findSpotFinanceTotal(String spotCode)throws Exception;

    public JSONObject findSpotOrderTMInfo(String spotCode, long semesterId)throws Exception;

    public String downSpotOrderTMInfo(String spotCode, long semesterId, String fileName) throws Exception;

    public JSONArray findStudentOwnInfo(String spotCode)throws Exception;

    public JSONArray findStudentAccInfo(String spotCode)throws Exception;

    public List<JSONObject> countReward(int year, String spotCode)throws Exception;

    public String downReward(int year, String spotCode, String fileName)throws Exception;
}
