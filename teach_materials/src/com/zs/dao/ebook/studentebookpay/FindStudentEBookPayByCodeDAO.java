package com.zs.dao.ebook.studentebookpay;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.ebook.StudentEBookPay;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2018/1/3.
 */
public interface FindStudentEBookPayByCodeDAO extends EntityJpaDao<StudentEBookPay, Long> {
    @Query("from StudentEBookPay where studentCode = ?1")
    public StudentEBookPay find(String code)throws Exception;
}
