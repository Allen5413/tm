package com.zs.dao.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderPackage;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询订单号最大的一个，下次生成订单号时，累计后面流水号
 * Created by Allen on 2015/7/23.
 */
public interface FindStudentBookOrderPackageForMaxCodeDAO extends EntityJpaDao<StudentBookOrderPackage, Long> {
    @Query(nativeQuery = true, value = "select t.* from (select * from student_book_order_package where code like ? order by code desc limit 1) t")
    public StudentBookOrderPackage findStudentBookOrderPackageForMaxCode(String codePrefix);
}
