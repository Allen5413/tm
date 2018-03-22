package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/9/10.
 */
public interface DownOnceForTakeBookBySpotCodeService extends EntityService {
    public String down(String spotCode, String fileName)throws Exception;
}
