package com.zs.service.basic.environment.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.environment.EnvironmentDAO;
import com.zs.domain.basic.Environment;
import com.zs.service.basic.environment.UpdateEnvironmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by LihongZhang on 2015/5/27.
 */
@Service("updateEnvironmentService")
public class UpdateEnvironmentServiceImpl extends EntityServiceImpl<Environment,EnvironmentDAO> implements UpdateEnvironmentService {

    @Resource
    private EnvironmentDAO environmentDAO;
    @Override
    public void updateE(Environment environment, String userName) throws Exception {
        if (null != environment){
            //查询是否存在该环境变量的记录
            Environment viledEnvironment = environmentDAO.get(environment.getId());
            if(null == viledEnvironment){
                throw new BusinessException("没找到要修改环境变量信息");
            }
            //设置操作人
            environment.setOperator(userName);
            environment.setName(viledEnvironment.getName());
            environment.setCode(viledEnvironment.getCode());
            //执行更新
            environmentDAO.update(environment);
        }
    }
}
