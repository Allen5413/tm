package com.zs.dao.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderPackage;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询已打包，还未邮寄的包的最大的顺序号
 * Created by Allen on 2015/7/23.
 * Created by Allen on 2015/7/27.
 */
public interface FindStudentBookOrderPackageForMaxSortNotSendDAO extends EntityJpaDao<StudentBookOrderPackage, Long> {
    @Query(nativeQuery = true, value = "select t.* from (select * from student_book_order_package where spot_code = ?1 and semester_id = ?2 and is_once = ?3 order by sort desc limit 1) t")
    public StudentBookOrderPackage findStudentBookOrderPackageForMaxSortNotSend(String spotCode, long semesterId, int isOnce);
}
