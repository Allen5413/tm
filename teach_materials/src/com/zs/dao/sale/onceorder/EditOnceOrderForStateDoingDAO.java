package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/6/21.
 */
public interface EditOnceOrderForStateDoingDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_once_order set state = 2 where state = 1")
    public void editor()throws Exception;
}
