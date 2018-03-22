package com.zs.service.basic.resource;


import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

/**
 * 新增资源信息
 * Created by Allen on 2015/4/27.
 */
public interface AddResourceService extends EntityService<Resource> {

    public void addResource(Resource resource, String loginName)throws Exception;
}
