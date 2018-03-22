package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/9/10.
 */
public interface DownStudentForTakeBookBySpotCodeService extends EntityService {
    public String down(String spotCode, String fileName)throws Exception;
}
