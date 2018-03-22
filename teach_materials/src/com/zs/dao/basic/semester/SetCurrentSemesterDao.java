package com.zs.dao.basic.semester;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Semester;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * 设置当前学期
 * Created by LihongZhang on 2015/5/11.
 */
public interface SetCurrentSemesterDao extends EntityJpaDao<Semester,Long> {
    /**
     * 修改当前学期为上一学期
     */
    @Modifying
    @Query("update Semester set isNowSemester = ?1,operator = ?2,operateTime = ?3 where isNowSemester =?4")
    public void setSemesterNotNowSemester(int isNowSemesterNo, String operator, Date operateTime, int isNowSemesterYes);


    /**
     *把此学期设为当前学期
     * @param id
     */
    @Modifying
    @Query("update Semester set isNowSemester = ?1,operator = ?2,operateTime = ?3 where id = ?4")
    public void setSemesterIsNowSemester(int isNowSemesterYes, String operator, Date operateTime, Long id);

}
