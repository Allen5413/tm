package com.zs.dao.finance.studentexpensebuy;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.StudentExpenseBuy;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/25.
 */
public interface FindStudentExpenseBuyByCodeDAO extends EntityJpaDao<StudentExpenseBuy, Long> {
    @Query("from StudentExpenseBuy where studentCode = ?1 order by createTime desc")
    public List<StudentExpenseBuy> getStudentExpenseBuyByCode(String code);
}
