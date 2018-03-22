package com.zs.service.placeorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/8/3.
 */
public interface FindPlaceOrderPackageForSpotCountPageService extends EntityService {
    public PageInfo findPlaceOrderPackageForSpotCountPage(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap);
}
