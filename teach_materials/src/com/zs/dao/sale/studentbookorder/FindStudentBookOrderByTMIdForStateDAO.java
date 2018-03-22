package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/9/8.
 */
public interface FindStudentBookOrderByTMIdForStateDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query("select sbo from StudentBookOrder sbo, StudentBookOrderTM sbotm where sbo.orderCode = sbotm.orderCode and sbo.state BETWEEN ?1 and ?2 and sbotm.teachMaterialId = ?3")
    public List<StudentBookOrder> findStudentBookOrderByTMIdForState(int state, int state2, long tmId);
}
