package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2017/3/14.
 */
public interface FindOnceOrderByTMIdForStateDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    @Query("select sbo from StudentBookOnceOrder sbo, StudentBookOnceOrderTM sbotm where sbo.id = sbotm.orderId and sbo.state BETWEEN ?1 and ?2 and sbotm.teachMaterialId = ?3")
    public List<StudentBookOnceOrder> find(int state, int state2, long tmId);
}
