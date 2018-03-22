package com.zs.service.basic.resource;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
public interface FindResourceByMenuIdService extends EntityService<Resource> {
    public List<Resource> getResourceByMenuIdService(Long menuId);
}
