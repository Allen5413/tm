package com.zs.service.sync.province;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Province;

/**
 * Created by Allen on 2015/6/1.
 */
public interface FindProvinceBySpotCodeService extends EntityService<Province> {
    public Province getProvinceBySpotCode(String code);
}
