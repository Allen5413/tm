package com.zs.service.sync.eduwestuser;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.EduwestUser;

import java.util.List;

/**
 * Created by Allen on 2015/8/18.
 */
public interface FindEduwestUserBySpotCodeService extends EntityService<EduwestUser> {
    public List<EduwestUser> getEduwestUserBySpotCode(String spotCode);
}
