package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;
import net.sf.json.JSONObject;

/**
 * 学习中心确认订单
 * Created by Allen on 2015/7/8.
 */
public interface ConfirmStudentBookOrderService extends EntityService<StudentBookOrder> {
    /**
     * 确认订单
     * @param id
     * @param spotCode
     * @param loginName
     * @throws Exception
     */
    public void confirmStudentBookOrder(long id, String spotCode, String loginName)throws Exception;

    /**
     * 确认多个订单
     * @param ids
     * @param spotCode
     * @param loginName
     * @throws Exception
     */
    public void confirmStudentBookOrderForMore(String ids, String spotCode, String loginName)throws Exception;

    /**
     * 学生自己确认订单
     * @param idCodeCountArray
     * @param loginName
     * @throws Exception
     */
    public JSONObject confirmStudentBookOrderForStudent(String studentCode, String[] idCodeCountArray, String loginName)throws Exception;
}
