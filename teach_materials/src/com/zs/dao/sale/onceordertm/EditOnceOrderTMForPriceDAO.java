package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 修改学生订书单明细的价格。
 * 教材价格变更后，会把已发送状态前的学生订单明细价格一起改动
 * Created by Allen on 2015/6/1.
 */
public interface EditOnceOrderTMForPriceDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_once_order_tm sbotm set sbotm.price = ?1 where exists(select sbo.order_code from student_book_once_order sbo where sbotm.order_id = sbo.id and sbo.state < ?2) and sbotm.teach_material_id = ?3")
    public void edit(float price, int state, Long tmId);
}
