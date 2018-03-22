package com.zs.service.basic.resource;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

/**
 * 编辑菜单信息
 * Created by Allen on 2015/4/27.
 */
public interface EditResourceService extends EntityService<Resource> {

    public void editResource(Resource resource, String loginName)throws Exception;
}
