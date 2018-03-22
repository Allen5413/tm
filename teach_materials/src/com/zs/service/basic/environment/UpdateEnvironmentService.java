package com.zs.service.basic.environment;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Environment;

/**
 * Created by LihongZhang on 2015/5/27.
 */
public interface UpdateEnvironmentService extends EntityService<Environment> {
    /**
     * 修改环境变量的方法
     * @param environment
     * @param userName
     * @throws Exception
     */
    public void updateE(Environment environment, String userName) throws Exception;
}
