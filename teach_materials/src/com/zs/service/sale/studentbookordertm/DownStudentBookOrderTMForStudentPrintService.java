package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/8/13.
 */
public interface DownStudentBookOrderTMForStudentPrintService extends EntityService {
    public String down(long semesterId, int state, String operateTime, String spotCode, String fileName)throws Exception;
}
