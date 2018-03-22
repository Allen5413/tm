package com.zs.service.basic.resource;

import com.alibaba.fastjson.JSONArray;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;


/**
 * Created by Allen on 2016/9/25 0025.
 */
public interface FindResourceForMenuService extends EntityService<Resource> {
    public JSONArray find();
}
