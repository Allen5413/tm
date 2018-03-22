package com.zs.service.sync.spec;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spec;

/**
 * Created by Allen on 2015/8/4.
 */
public interface FindSpecByCodeService extends EntityService<Spec> {
    public Spec findSpecByCode(String code);
}
