package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/11/4.
 */
public interface EditStudentBookOrderTMByStateAndTMIdAndSemesterIdForCountDAO extends EntityJpaDao<StudentBookOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_order_tm sbotm INNER JOIN student_book_order sbo " +
            "on sbotm.order_code = sbo.order_code and sbo.state < ?1 and sbotm.teach_material_id = ?2 " +
            "and semester_id = ?3 set sbotm.count = 0")
    public void edit(int state, long tmId, long semesterId)throws Exception;
}
