package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询学生信息
 * 在生成采购单时，需要按照当前开课计划的选课数，通过选课的学生的所在学习中心按渠道范围分组生成订单
 * Created by Allen on 2015/5/9.
 */
public interface FindStudentForAddPurchaseOrderDAO extends EntityJpaDao<Student, Long> {
    @Query(nativeQuery=true, value="select s.spot_code from sync_begin_schedule bs, sync_selected_course sc, sync_student s " +
            "where bs.course_code = sc.course_code and bs.academic_year = ?1 and bs.term = ?2 " +
            "and unix_timestamp(sc.operate_time) between unix_timestamp(?3) and unix_timestamp(?4) " +
            "group by s.spot_code")
    public List<Student> getStudentForAddPurchaseOrder(int year, int quarter, String beginDate, String endDate);
}
