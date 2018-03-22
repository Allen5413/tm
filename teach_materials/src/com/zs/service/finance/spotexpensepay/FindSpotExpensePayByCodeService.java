package com.zs.service.finance.spotexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpensePay;

import java.util.List;

/**
 * Created by Allen on 2015/6/8.
 */
public interface FindSpotExpensePayByCodeService extends EntityService<SpotExpensePay> {
    public List<SpotExpensePay> getSpotExpensePayByCode(String code);
}
