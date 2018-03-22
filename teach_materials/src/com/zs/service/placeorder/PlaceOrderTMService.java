package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/7/31.
 */
public interface PlaceOrderTMService extends EntityService {
    /**
     * 新增预订单的教材
     * @param idAndCounts
     * @param loginName
     * @throws Exception
     */
    public void add(String idAndCounts, String loginName, String spotCode, String address, String adminName, String phone, String tel, String postalCode)throws Exception;
}
