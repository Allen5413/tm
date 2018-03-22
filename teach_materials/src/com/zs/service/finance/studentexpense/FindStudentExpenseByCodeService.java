package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpense;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindStudentExpenseByCodeService extends EntityService<StudentExpense> {
    public JSONObject find(String code);
}
