package com.zs.service.ebook.spotreplacepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePay;

/**
 * Created by Allen on 2016/1/4.
 */
public interface FindSpotReplacePayByIdService extends EntityService<SpotReplacePay> {
    public SpotReplacePay find(long id)throws Exception;

}
