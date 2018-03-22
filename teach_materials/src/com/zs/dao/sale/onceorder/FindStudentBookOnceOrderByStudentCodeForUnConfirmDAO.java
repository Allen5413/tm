package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    @Query("FROM StudentBookOnceOrder WHERE studentCode = ?1 and state = 0")
    public List<StudentBookOnceOrder> find(String code);
}
