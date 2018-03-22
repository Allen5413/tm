package com.zs.dao.finance.spotexpensepay;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotExpensePay;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/1/26.
 */
public interface FindSumPayByCodeDAO extends EntityJpaDao<SpotExpensePay, Long> {
    @Query("select sum(money) from SpotExpensePay where spotCode = ?1")
    public double find(String code);
}
