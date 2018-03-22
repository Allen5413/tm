package com.zs.dao.finance.refundstudent;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.RefundStudent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 通过退款批次编号查询明细
 * Created by Allen on 2016/1/7.
 */
public interface FindRefundStudentByCodeDAO extends EntityJpaDao<RefundStudent, Long> {
    @Query(nativeQuery = true, value = "select rs.id, s.code, s.name, s.spec_code, s.level_code, rs.state, rs.money, rs.refund_detail, rs.audit_detail, rs.operator, rs.operate_time from refund_student rs, sync_student s " +
            "where rs.student_code = s.code and rs.code = ?1 " +
            "order by rs.operate_time desc")
    public List<Object[]> find(String code);
}
