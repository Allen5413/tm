package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/8/12.
 */
public interface DownStudentBookOrderForSpotPrintService extends EntityService {
    public void down(String spotCode, String semesterId, String state, String operateTime, String fileName)throws Exception;
}
