package com.zs.dao.finance.spotexpensebuy;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotExpenseBuy;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/25.
 */
public interface FindSpotExpenseBuyByCodeDAO extends EntityJpaDao<SpotExpenseBuy, Long> {
    @Query("from SpotExpenseBuy where spotCode = ?1 order by createTime desc")
    public List<SpotExpenseBuy> find(String code);
}
