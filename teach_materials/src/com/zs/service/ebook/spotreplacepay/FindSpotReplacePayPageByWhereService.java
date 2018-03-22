package com.zs.service.ebook.spotreplacepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePay;

import java.util.Map;

/**
 * Created by Allen on 2016/1/4.
 */
public interface FindSpotReplacePayPageByWhereService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;

}
