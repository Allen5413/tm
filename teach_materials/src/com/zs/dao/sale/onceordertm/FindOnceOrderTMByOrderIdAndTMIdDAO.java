package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/7/8.
 */
public interface FindOnceOrderTMByOrderIdAndTMIdDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Query("select sbotm from StudentBookOnceOrderTM sbotm where sbotm.teachMaterialId = ?1 and sbotm.orderId = ?2")
    public List<StudentBookOnceOrderTM> find(long tmId, long orderId);
}
