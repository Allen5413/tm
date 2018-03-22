package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;
import net.sf.json.JSONObject;

/**
 * 手动添加学生增补教材
 * Created by Allen on 2015/5/13.
 */
public interface AddOnceOrderService extends EntityService<StudentBookOnceOrder> {
    public JSONObject add(String studentCode, String idAndCounts, String loginName)throws Exception;

}
