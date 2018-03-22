package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;

/**
 * Created by Allen on 2016/11/9.
 */
public interface AddPlaceOrderForSpecAllService extends EntityService<TeachMaterialPlaceOrder> {
    public void add(String spotCode, String address, String adminName,String phone,String tel,String postalCode,
                    String enYear, String enQuarter, String specCode, String levelCode, int personNum, String courseCodes, String loginName)throws Exception;
}
