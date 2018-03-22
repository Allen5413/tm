package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 查询学生订单信息，用于微信推送
 * Created by Allen on 2016/5/24 0024.
 */
public interface FindStudentBookOrderForWXByCodeService extends EntityService<StudentBookOrder> {
    /**
     * 查询正在处理的订单
     * @param code
     * @return
     */
    public List<JSONObject> find(String code);

    /**
     * 查询所有有效的订单
     * @param code
     * @return
     */
    public List<JSONObject> findAll(String code);

    /**
     * 查询未处理的订单
     * @param code
     * @return
     */
    public List<JSONObject> findNotConfirm(String code);
}
