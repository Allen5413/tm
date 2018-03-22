package com.zs.service.sync.spotwx;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpotWx;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindSpotWxByOpenIdService extends EntityService<SpotWx> {
    public SpotWx find(String openId);
}
