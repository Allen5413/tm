package com.zs.service.finance.spotexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpense;

/**
 * 修改中心财务折扣
 * Created by Allen on 2016/1/26.
 */
public interface EditSpotExpenseDiscountByIDService extends EntityService<SpotExpense> {
    public String edit(long id, int discount, String userName)throws Exception;
}
