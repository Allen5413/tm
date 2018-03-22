package com.zs.service.basic.resource;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

/**
 * 删除资源信息
 * Created by Allen on 2015/4/27.
 */
public interface DelResourceService extends EntityService<Resource> {

    public void delResource(Long id)throws Exception;
}
