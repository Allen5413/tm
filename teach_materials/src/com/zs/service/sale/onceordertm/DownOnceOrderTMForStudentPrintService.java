package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/8/13.
 */
public interface DownOnceOrderTMForStudentPrintService extends EntityService {
    public String down(long semesterId, int state, String operateTime, String fileName)throws Exception;
}
