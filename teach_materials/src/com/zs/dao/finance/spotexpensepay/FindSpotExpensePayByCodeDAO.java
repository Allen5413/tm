package com.zs.dao.finance.spotexpensepay;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotExpensePay;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/8.
 */
public interface FindSpotExpensePayByCodeDAO extends EntityJpaDao<SpotExpensePay, Long> {
    @Query("from SpotExpensePay where spotCode = ?1 order by createTime desc")
    public List<SpotExpensePay> getSpotExpensePayByCode(String code);
}
