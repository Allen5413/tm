package com.zs.service.basic.setteachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByCourseCodeDAO;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByNameDAO;
import com.zs.dao.basic.setteachmaterial.SetTeachMaterialDAO;
import com.zs.dao.basic.setteachmaterialcourse.SetTeachMaterialCourseDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.basic.SetTeachMaterialCourse;
import com.zs.service.basic.setteachmaterial.AddSetTeachMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("addSetTeachMaterialService")
public class AddSetTeachMaterialServiceImpl extends EntityServiceImpl<SetTeachMaterial, SetTeachMaterialDAO> implements AddSetTeachMaterialService {
    @Resource
    private FindSetTeachMaterialByNameDAO findSetTeachMaterialByNameDAO;
    @Resource
    private FindSetTeachMaterialByCourseCodeDAO findSetTeachMaterialByCourseCodeDAO;
    @Resource
    private SetTeachMaterialCourseDAO setTeachMaterialCourseDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addSetTeachMaterial(SetTeachMaterial setTeachMaterial, String loginName) throws Exception {
        if(null != setTeachMaterial) {
            //查询套教材名称是否已经存在
            SetTeachMaterial validSetTeachMaterial = findSetTeachMaterialByNameDAO.getTeachMaterialByName(setTeachMaterial.getName());
            if(null != validSetTeachMaterial && validSetTeachMaterial.getName().equals(setTeachMaterial.getName())){
                throw new BusinessException("套教材名称已经存在！");
            }
            //查询套教材购买课程是否已经存在
            validSetTeachMaterial = findSetTeachMaterialByCourseCodeDAO.getSetTeachMaterialByCourseCode(setTeachMaterial.getBuyCourseCode());
            if(null != validSetTeachMaterial && validSetTeachMaterial.getBuyCourseCode().equals(setTeachMaterial.getBuyCourseCode())){
                throw new BusinessException("购买课程已经存在！");
            }
            setTeachMaterial.setCreator(loginName);
            setTeachMaterial.setOperator(loginName);
            super.save(setTeachMaterial);
            //添加套教材与课程的关联
            SetTeachMaterialCourse setTeachMaterialCourse = new SetTeachMaterialCourse();
            setTeachMaterialCourse.setSetTeachMaterialId(setTeachMaterial.getId());
            setTeachMaterialCourse.setCourseCode(setTeachMaterial.getBuyCourseCode());
            setTeachMaterialCourse.setCreator(loginName);
            setTeachMaterialCourseDAO.save(setTeachMaterialCourse);
        }
    }
}
