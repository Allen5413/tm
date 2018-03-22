package com.zs.service.finance.spotexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpense;

/**
 * Created by Allen on 2016/1/26.
 */
public interface ResetSpotExpenseForPayBySpotCodeService extends EntityService<SpotExpense> {
    public void reset(String spotCode, String userName)throws Exception;
}
