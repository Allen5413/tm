package com.zs.service.sync.spotwx;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpotWx;

import java.util.List;

/**
 * Created by Allen on 2016/9/8.
 */
public interface FindSpotWxByCodeService extends EntityService<SpotWx> {
    public List<SpotWx> find(String code);
}
