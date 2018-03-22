package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/7/22.
 */
public interface FindOnceOrderByCodeDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    @Query("FROM StudentBookOnceOrder where orderCode = ?1")
    public StudentBookOnceOrder find(String orderCode);
}
