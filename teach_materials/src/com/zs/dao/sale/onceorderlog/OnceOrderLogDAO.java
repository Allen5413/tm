package com.zs.dao.sale.onceorderlog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/12.
 */
public interface OnceOrderLogDAO extends EntityJpaDao<StudentBookOnceOrderLog, Long> {

    /**
     * 在订单状态改为已处理的时候，插入状态变更记录
     * @param loginName
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "INSERT into student_book_once_order_log(order_id, state, operator, operate_time) select id, 2, ?1, now() from student_book_once_order where state = 1 and semester_id = ?2")
    public void addForStateToDoing(String loginName, long semesterId) throws Exception;
}
