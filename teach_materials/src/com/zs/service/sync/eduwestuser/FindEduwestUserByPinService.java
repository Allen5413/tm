package com.zs.service.sync.eduwestuser;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.EduwestUser;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindEduwestUserByPinService extends EntityService<EduwestUser> {
    public EduwestUser getEduwestUserByPin(String pin);
}
