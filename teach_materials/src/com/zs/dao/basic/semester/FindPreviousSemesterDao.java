package com.zs.dao.basic.semester;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Semester;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询上一学期信息
 * Created by LihongZhang on 2015/5/12.
 */
public interface FindPreviousSemesterDao extends EntityJpaDao<Semester,Long>{

    @Query("from Semester where year = ?1 and quarter = ?2")
    public Semester getPreviousSemester(int year, int quarter);
}
