package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/11/15.
 */
public interface CountOnceOrderForConfirmStudentDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    /**
     * 查询已确认的
     * @param spotCode
     * @return
     */
    @Modifying
    @Query(nativeQuery =  true, value = "select s.code, s.name, s.level_code, s.spec_code, sum(sbotm.price) totalPrice, sum(sbotm.count * sbotm.price) price " +
            "from sync_student s, student_book_once_order sbo, student_book_once_order_tm sbotm " +
            "where s.code = sbo.student_code and sbo.id = sbotm.order_id and sbo.state > 0 and s.spot_code = ?1 " +
            "group by s.code, s.name, s.level_code, s.spec_code")
    public List<Object[]> findConfirm(String spotCode);

    /**
     * 查询未确认的
     * @param spotCode
     * @return
     */
    @Modifying
    @Query(nativeQuery =  true, value = "select s.code, s.name, s.level_code, s.spec_code, sum(sbotm.price) totalPrice, sum(sbotm.count * sbotm.price) price " +
            "from sync_student s, student_book_once_order sbo, student_book_once_order_tm sbotm " +
            "where s.code = sbo.student_code and sbo.id = sbotm.order_id and sbo.state = 0 and s.spot_code = ?1 " +
            "group by s.code, s.name, s.level_code, s.spec_code")
    public List<Object[]> findNotConfirm(String spotCode);
}