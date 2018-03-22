package com.zs.service.sync.province;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Province;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindProvinceByCodeService extends EntityService<Province> {
    public Province getProvinceByCode(String code);
}
