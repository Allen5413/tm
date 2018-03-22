package com.zs.service.statis;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2016/1/14.
 */
public interface FindTotalFinanceForSemesterService extends EntityService {
    public JSONObject find()throws Exception;

    public List<JSONObject> findBySemesterForSpot(int year, int quarter)throws Exception;
}
