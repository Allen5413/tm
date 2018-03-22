package com.zs.service.basic.resource;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
public interface FindResourceByGroupIdService extends EntityService<Resource> {
    public List<Resource> getResourceByGroupId(Long userGroupId);
}
