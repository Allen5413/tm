package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 删除一个订单下面有数量的教材明细，用于复制订单明细时，删除掉以前的所需要的订单明细
 * Created by Allen on 2016/6/28.
 */
public interface DelOnceOrderTMForHaveCountByOrderIdDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from student_book_once_order_tm where order_id = ?1 and count > 0")
    public void del(long orderId);
}
