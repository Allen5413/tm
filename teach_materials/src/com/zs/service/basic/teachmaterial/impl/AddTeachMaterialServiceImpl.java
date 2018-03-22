package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.*;
import com.zs.dao.basic.teachmaterialcourse.TeachMaterialCourseDAO;
import com.zs.dao.sync.course.FindCourseByCodeDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.domain.sync.Course;
import com.zs.service.basic.teachmaterial.AddTeachMaterialService;
import com.zs.service.basic.teachmaterialrevision.AddTeachMaterialRevisionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/4/29.
 */
@Service("addTeachMaterialService")
public class AddTeachMaterialServiceImpl extends EntityServiceImpl<TeachMaterial, TeachMaterialDAO> implements AddTeachMaterialService {

    @Resource
    private FindTeachMaterialByNameDAO findTeachMaterialByNameDAO;
    @Resource
    private FindTeachMaterialByIsbnDAO findTeachMaterialByIsbnDAO;
    @Resource
    private AddTeachMaterialRevisionService addTeachMaterialRevisionService;
    @Resource
    private TeachMaterialCourseDAO teachMaterialCourseDAO;
    @Resource
    private FindCourseByCodeDAO findCourseByCodeDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addTeachMaterial(TeachMaterial teachMaterial, String courseCode, String loginName) throws Exception {
        if(null != teachMaterial){
            //验证教材名称是否已经存在 2015-12-15取消验证
//            List<TeachMaterial> teachMaterialList = findTeachMaterialByNameDAO.getTeachMaterialByName(teachMaterial.getName());
//            if(null != teachMaterialList && 0 < teachMaterialList.size()){
//                for(TeachMaterial validTeachMaterial : teachMaterialList){
//                    if(validTeachMaterial.getName().equals(teachMaterial.getName())){
//                        throw new BusinessException("教材名称已经存在");
//                    }
//                }
//            }

            //验证教材编号是否已经存在
            if(!StringUtils.isEmpty(teachMaterial.getIsbn())) {
                List<TeachMaterial> teachMaterialList2 = findTeachMaterialByIsbnDAO.getTeachMaterialByIsbn(teachMaterial.getIsbn());
                if (null != teachMaterialList2 && 0 < teachMaterialList2.size()) {
                    for (TeachMaterial validTeachMaterial : teachMaterialList2) {
                        if (validTeachMaterial.getIsbn().equals(teachMaterial.getIsbn())) {
                            throw new BusinessException("教材ISBN已经存在");
                        }
                    }
                }
            }
            teachMaterial.setState(TeachMaterial.STATE_ENABLE);
            teachMaterial.setCreator(loginName);
            teachMaterial.setOperator(loginName);
            super.save(teachMaterial);
            //增加教材版次
            TeachMaterialRevision teachMaterialRevision = new TeachMaterialRevision();
            teachMaterialRevision.setTeachMaterialId(teachMaterial.getId());
            teachMaterialRevision.setRevision(teachMaterial.getRevision());
            teachMaterialRevision.setPrice(teachMaterial.getPrice());
            teachMaterialRevision.setIsNow(TeachMaterialRevision.ISNOW_YES);
            teachMaterialRevision.setCreator(loginName);
            teachMaterialRevision.setOperator(loginName);
            addTeachMaterialRevisionService.save(teachMaterialRevision);
            //如果课程编号有值，就增加关联课程编号
            if(!StringUtils.isEmpty(courseCode)){
                Course course = findCourseByCodeDAO.find(courseCode);
                if(course == null || StringUtils.isEmpty(course.getName())){
                    throw new BusinessException("课程编号："+courseCode+"，没有找到");
                }
                TeachMaterialCourse teachMaterialCourse = new TeachMaterialCourse();
                teachMaterialCourse.setCourseCode(courseCode);
                teachMaterialCourse.setCreator(loginName);
                teachMaterialCourse.setTeachMaterialId(teachMaterial.getId());
                teachMaterialCourseDAO.save(teachMaterialCourse);
            }
        }
    }
}
