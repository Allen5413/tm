package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.hibernate.jdbc.Expectation;

/**
 * 把还没有发出的预订单，设置成已发出，并扣除其费用
 * 主要用于奥鹏中心，没有账号登录系统的，由邸老师操作的中心
 * Created by Allen on 2016/1/14 0014.
 */
public interface SetPlaceOrderStateSignByIdService extends EntityService<TeachMaterialPlaceOrder> {
    public void set(long id, String userName)throws Exception;
}
