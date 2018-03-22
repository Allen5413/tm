package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/11/4.
 */
public interface EditOnceOrderTMByStateAndTMIdAndSemesterIdForCountDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_once_order_tm sbotm INNER JOIN student_book_once_order sbo " +
            "on sbotm.order_id = sbo.id and sbo.state < ?1 and sbotm.teach_material_id = ?2 " +
            "and semester_id = ?3 set sbotm.count = 0")
    public void edit(int state, long tmId, long semesterId)throws Exception;
}
