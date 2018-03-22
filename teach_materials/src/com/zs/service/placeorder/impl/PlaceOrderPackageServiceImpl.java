package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.PlaceOrderPackageDAO;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.service.placeorder.PlaceOrderPackageService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/10/10.
 */
@Service("placeOrderPackageService")
public class PlaceOrderPackageServiceImpl extends EntityServiceImpl<PlaceOrderPackage, PlaceOrderPackageDAO>
    implements PlaceOrderPackageService{
}
