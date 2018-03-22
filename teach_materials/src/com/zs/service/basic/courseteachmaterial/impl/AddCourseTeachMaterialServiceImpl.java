package com.zs.service.basic.courseteachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.courseteachmaterial.DelCourseTeachMaterialBySemesterIdDAO;
import com.zs.dao.basic.courseteachmaterial.FindCourseTeachMaterialForAddDAO;
import com.zs.domain.basic.CourseTeachMaterial;
import com.zs.service.basic.courseteachmaterial.AddCourseTeachMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/12/14 0014.
 */
@Service("addCourseTeachMaterialService")
public class AddCourseTeachMaterialServiceImpl extends EntityServiceImpl implements AddCourseTeachMaterialService {

    @Resource
    private FindCourseTeachMaterialForAddDAO findCourseTeachMaterialForAddDAO;
    @Resource
    private DelCourseTeachMaterialBySemesterIdDAO delCourseTeachMaterialBySemesterIdDAO;

    @Override
    @Transactional
    public void add(long semesterId, String userName) throws Exception {
        //删除传入学期的数据
        delCourseTeachMaterialBySemesterIdDAO.del(semesterId);

        //添加课程教材对应表
        List<Object[]> list = findCourseTeachMaterialForAddDAO.find(semesterId);
        for(Object[] objs : list){
            String courseCode = objs[0].toString();
            Long tmId = null == objs[1] ? null : Long.parseLong(objs[1].toString());

            CourseTeachMaterial courseTeachMaterial = new CourseTeachMaterial();
            courseTeachMaterial.setSemesterId(semesterId);
            courseTeachMaterial.setCourseCode(courseCode);
            courseTeachMaterial.setTeachMaterialId(tmId);
            courseTeachMaterial.setCreator(userName);
            courseTeachMaterial.setOperator(userName);
            findCourseTeachMaterialForAddDAO.save(courseTeachMaterial);
        }

        //删除一个课程对应多条记录的，tmid为null的记录
        delCourseTeachMaterialBySemesterIdDAO.delTMIdIsNullForMoreCourse(semesterId);
    }
}
