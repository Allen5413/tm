package com.zs.service.sync.spot;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindSpotByCodeService extends EntityService<Spot> {
    public Spot getSpotByCode(String code);
}
