package com.zs.dao.sync.student;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;

import java.util.List;

/**
 * Created by Allen on 2015/5/8.
 */
public interface StudentDAO extends EntityJpaDao<Student,Long> {
	
	@Query("select a from Student a where a.code = ?1")
	public Student queryStudentByStuCode(String stuCode);

	/**
	 * 查询一个学生购买的所有订单教材明细
	 * @param studentCode
	 * @return
	 * @throws Exception
	 */
	@Query(nativeQuery = true, value = "select s.name, tm.name tmName, sbotm.count, sbotm.price, sbotm.count*sbotm.price " +
			"from student_book_order sbo, student_book_order_tm sbotm, sync_student s, teach_material tm " +
			"where sbo.order_code = sbotm.order_code and sbo.student_code = s.code and sbotm.teach_material_id = tm.id " +
			"and sbotm.is_send = 1 and sbotm.count > 0 and sbo.state > 3 and sbo.student_code = ?1 " +
			"union " +
			"select s.name, tm.name tmName, sbotm.count, sbotm.price, sbotm.count*sbotm.price " +
			"from student_book_once_order sbo, student_book_once_order_tm sbotm, sync_student s, teach_material tm " +
			"where sbo.id = sbotm.order_id and sbo.student_code = s.code and sbotm.teach_material_id = tm.id " +
			"and sbotm.is_send = 1 and sbotm.count > 0 and sbo.state > 4 and sbo.student_code = ?1")
	public List<Object[]> findOrderTmByStudentCode(String studentCode)throws Exception;
}
