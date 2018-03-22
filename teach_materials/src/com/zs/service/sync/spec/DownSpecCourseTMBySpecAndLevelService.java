package com.zs.service.sync.spec;

import com.feinno.framework.common.service.EntityService;

import java.io.File;

/**
 * Created by Allen on 2016/10/20.
 */
public interface DownSpecCourseTMBySpecAndLevelService extends EntityService {
    public String down(String specCode, String levelCode, String fileName)throws Exception;

    public File[] downZip(String fileName)throws Exception;
}
