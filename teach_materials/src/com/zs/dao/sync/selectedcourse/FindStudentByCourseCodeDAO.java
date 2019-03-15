package com.zs.dao.sync.selectedcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SelectedCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/8.
 */
public interface FindStudentByCourseCodeDAO extends EntityJpaDao<SelectedCourse, Long> {

    /**
     * 通过课程编号查询所有学生的信息
     * @param studentCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select DISTINCT student_code from sync_selected_course where course_code = ?1")
    public List<String> find(String courseCode)throws Exception;

    /**
     * 通过课程编号查询新生学生信息
     * @param studentCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select DISTINCT sc.student_code from sync_selected_course sc, sync_student s, semester sr " +
            "where s.code = sc.student_code and s.study_quarter = sr.year and s.study_quarter = sr.quarter and sr.is_now_semester = 0 and sc.course_code = ?1")
    public List<String> findNewStudent(String courseCode)throws Exception;
}
