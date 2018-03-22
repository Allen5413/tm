package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2015/9/9.
 */
public interface FindStudentBookOrderTMPrintByOrderIdService extends EntityService {
    public JSONObject findStudentBookOrderTMPrintByOrderId(String... ids)throws Exception;

    public void editPrintSort(String... ids)throws Exception;
}
