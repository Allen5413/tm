package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;
import net.sf.json.JSONObject;

/**
 * 手动添加学生增补教材
 * Created by Allen on 2015/5/13.
 */
public interface AddStudentBookOrderService extends EntityService<StudentBookOrder> {
    public JSONObject addStudentBookOrder(String studentCode, String idAndCounts, String loginName)throws Exception;

}
