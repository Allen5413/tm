package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/9/24.
 */
public interface FindStudentBookOrderCourseCountDAO extends EntityJpaDao<StudentBookOrderTM, Long> {

    @Query("select sbotm from StudentBookOrderTM sbotm where sbotm.orderCode = ?1 and sbotm.courseCode = ?2")
    public List<StudentBookOrderTM> findStudentBookOrderCourseCount(String orderCode, String courseCode);
}
