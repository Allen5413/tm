package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceByGroupIdDAO;
import com.zs.dao.basic.resource.ResourceDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceByGroupIdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
@Service("findResourceByGroupIdService")
public class FindResourceByGroupIdServiceImpl extends EntityServiceImpl<Resource, ResourceDAO>
        implements FindResourceByGroupIdService {

    @javax.annotation.Resource
    private FindResourceByGroupIdDAO findResourceByGroupIdDAO;

    @Override
    public List<Resource> getResourceByGroupId(Long userGroupId) {
        return findResourceByGroupIdDAO.getResourceByGroupId(userGroupId);
    }
}
