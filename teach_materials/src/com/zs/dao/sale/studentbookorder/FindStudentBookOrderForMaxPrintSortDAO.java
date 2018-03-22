package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个学期学习中心打印的学生订单最大的顺序号
 * Created by Allen on 2015/9/9.
 */
public interface FindStudentBookOrderForMaxPrintSortDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query(nativeQuery = true, value = "select max(sbo.print_sort) from student_book_order sbo, sync_student s where sbo.student_code = s.code and sbo.semester_id = ?1 and s.spot_code = ?2")
    public Integer findStudentBookOrderForMaxPrintSort(Long semesterId, String spotCode);
}
