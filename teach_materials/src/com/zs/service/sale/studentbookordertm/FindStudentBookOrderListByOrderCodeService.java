package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/5/26.
 */
public interface FindStudentBookOrderListByOrderCodeService extends EntityService {
    public JSONArray getStudentBookOrderListByOrderCode(String orderCode);
}
