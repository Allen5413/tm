package com.zs.service.finance.studentexpensebuy;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/10/14.
 */
public interface DownStuEBService extends EntityService {
    public String down(String code, String fileName)throws Exception;
}
