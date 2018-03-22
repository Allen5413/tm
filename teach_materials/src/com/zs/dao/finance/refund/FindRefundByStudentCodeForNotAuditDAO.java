package com.zs.dao.finance.refund;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Refund;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一个学生还未被审核的退款申请信息
 * Created by Allen on 2016/1/12.
 */
public interface FindRefundByStudentCodeForNotAuditDAO extends EntityJpaDao<Refund, Long> {
    @Query("select r from Refund r, RefundStudent rs where r.code = rs.code and r.state < 3 and rs.studentCode = ?1")
    public List<Refund> find(String studentCode);
}
