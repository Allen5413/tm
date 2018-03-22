package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/7/8.
 */
public interface FindStudentBookOrderTMByOrderCodeAndTMIdDAO extends EntityJpaDao<StudentBookOrderTM, Long> {
    @Query("select sbotm from StudentBookOrderTM sbotm where sbotm.teachMaterialId = ?1 and sbotm.orderCode = ?2")
    public List<StudentBookOrderTM> find(long tmId, String orderCode);
}
