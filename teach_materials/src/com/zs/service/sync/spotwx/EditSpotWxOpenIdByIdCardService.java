package com.zs.service.sync.spotwx;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpotWx;
import net.sf.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by Allen on 2016/5/24.
 */
public interface EditSpotWxOpenIdByIdCardService extends EntityService<SpotWx> {
    public JSONObject edit(String idCard, String openId, Timestamp boundTime)throws Exception;

    public JSONObject cancelBound(String openId)throws Exception;
}
