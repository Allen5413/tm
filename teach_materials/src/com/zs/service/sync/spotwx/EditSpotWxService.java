package com.zs.service.sync.spotwx;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpotWx;

/**
 * Created by Allen on 2016/9/8.
 */
public interface EditSpotWxService extends EntityService<SpotWx> {
    public void edit(long id, String idcard, String name, String loginName);
}
