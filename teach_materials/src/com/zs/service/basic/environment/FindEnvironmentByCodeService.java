package com.zs.service.basic.environment;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Environment;

/**
 * Created by Allen on 2015/5/13.
 */
public interface FindEnvironmentByCodeService extends EntityService<Environment> {
    public Environment getEnvironmentByCode(String code);
}
