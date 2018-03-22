package com.zs.service.placeorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.PlaceOrderPackage;

import java.util.Map;

/**
 * Created by Allen on 2015/8/2.
 */
public interface FindPlaceOrderPackagePageByWhereService extends EntityService<PlaceOrderPackage> {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
