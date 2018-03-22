package com.zs.service.ebook.spotreplacepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePay;

/**
 * Created by Allen on 2018/1/3.
 */
public interface AddSpotReplacePayService extends EntityService<SpotReplacePay> {
    public void add(String[] codes, float money, SpotReplacePay spotReplacePay)throws Exception;
}
