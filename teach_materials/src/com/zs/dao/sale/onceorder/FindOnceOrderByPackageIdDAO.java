package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/7/25.
 */
public interface FindOnceOrderByPackageIdDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    @Query("FROM StudentBookOnceOrder where packageId = ?1 order by studentCode")
    public List<StudentBookOnceOrder> find(long packageId);
}
