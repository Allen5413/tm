package com.zs.service.sync.spotwx;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpotWx;


/**
 * Created by Allen on 2016/9/8.
 */
public interface FindSpotWxByIdCardService extends EntityService<SpotWx> {
    public SpotWx find(String idCard);
}
