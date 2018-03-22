package com.zs.service.ebook.spotreplacepaydetail;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePayDetail;

/**
 * Created by Allen on 2018/1/3.
 */
public interface AddSpotReplacePayDetailService extends EntityService<SpotReplacePayDetail> {
    public void add(long srpId, String code, long money, String loginName)throws Exception;
}
