package com.zs.service.finance.spotexpenseoth;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpenseOth;

/**
 * Created by Allen on 2016/1/11.
 */
public interface SetSpotExpenseOthBySpotCodeService extends EntityService<SpotExpenseOth> {
    public void reset(String spotCode)throws Exception;
}
