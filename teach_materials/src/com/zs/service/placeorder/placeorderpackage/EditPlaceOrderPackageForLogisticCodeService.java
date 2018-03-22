package com.zs.service.placeorder.placeorderpackage;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.PlaceOrderPackage;

/**
 * Created by Allen on 2016/3/23.
 */
public interface EditPlaceOrderPackageForLogisticCodeService extends EntityService<PlaceOrderPackage> {
    public void edit(long id, String logisticCodes, String loginName);
}
