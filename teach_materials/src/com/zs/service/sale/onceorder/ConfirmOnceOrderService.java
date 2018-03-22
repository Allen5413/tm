package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

/**
 * 学习中心确认订单
 * Created by Allen on 2015/7/8.
 */
public interface ConfirmOnceOrderService extends EntityService<StudentBookOnceOrder> {
    /**
     * 确认订单
     * @param id
     * @param spotCode
     * @param loginName
     * @throws Exception
     */
    public void confirmOnceOrder(long id, String spotCode, String loginName)throws Exception;

    /**
     * 确认多个订单
     * @param ids
     * @param spotCode
     * @param loginName
     * @throws Exception
     */
    public void confirmOnceOrderForMore(String ids, String spotCode, String loginName)throws Exception;
}
