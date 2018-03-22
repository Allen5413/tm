package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/8/13.
 */
public interface DownStudentBookOrderTMForSpotPrintService extends EntityService {
    public String down(String spotCode, String semesterId, String state, String operateTime, String fileName)throws Exception;
}
