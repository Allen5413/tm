package com.zs.dao.finance.refund;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Refund;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/1/6.
 */
public interface FindRefundForMaxCodeDAO extends EntityJpaDao<Refund, Long> {
    @Query(nativeQuery = true, value = "select t.* from (select * from refund where code like CONCAT('%',DATE_FORMAT(now(),'%Y%m%d'),'%') ORDER BY RIGHT(code,2) desc limit 1) t")
    public Refund findRefundForMaxCode();
}
