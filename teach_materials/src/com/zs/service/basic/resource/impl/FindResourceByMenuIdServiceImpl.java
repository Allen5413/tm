package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceByMenuIdDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceByMenuIdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
@Service("findResourceByMenuIdService")
public class FindResourceByMenuIdServiceImpl extends EntityServiceImpl<Resource, FindResourceByMenuIdDAO>
        implements FindResourceByMenuIdService {

    @javax.annotation.Resource
    private FindResourceByMenuIdDAO findResourceByMenuIdDAO;

    @Override
    public List<Resource> getResourceByMenuIdService(Long menuId) {
        return findResourceByMenuIdDAO.getResourceByMenuID(menuId);
    }
}
