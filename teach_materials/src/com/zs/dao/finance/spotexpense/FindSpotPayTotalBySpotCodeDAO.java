package com.zs.dao.finance.spotexpense;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotExpense;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/9.
 */
public interface FindSpotPayTotalBySpotCodeDAO extends EntityJpaDao<SpotExpense,Long> {

    @Query(nativeQuery = true, value = "select ifnull(sum(pay),0) from spot_expense where spot_code = ?1")
    public double find(String spotCode);
}
