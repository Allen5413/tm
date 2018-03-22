package com.zs.dao.finance.refundstudent;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.RefundStudent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/12/12.
 */
public interface DelRefundStudentByCodeDAO extends EntityJpaDao<RefundStudent, Long> {
    @Modifying
    @Query("delete from RefundStudent where code = ?1")
    public void del(String code);
}
