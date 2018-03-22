package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/5/31.
 */
public interface FindOnceOrderCountByStudentCodeAndSemesterIdDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {

    /**
     * 查询学生一个学期有没有一次性订单
     * @param studentCode
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT count(*) FROM student_book_once_order where student_code = ?1")
    public BigInteger findOrderCount(String studentCode);

    /**
     * 查询学生一个学期有没有一次性订单
     * @param studentCode
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT count(*) FROM student_book_once_order where student_code = ?1 and semester_id = ?2")
    public BigInteger findOrderCount(String studentCode, long semesterId);

    /**
     * 查询学生一个学期有没有未发出的一次性订单
     * @param studentCode
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT count(*) FROM student_book_once_order where student_code = ?1 and semester_id = ?2 and state < 5")
    public BigInteger findNotSendOrderCount(String studentCode, long semesterId);
}
