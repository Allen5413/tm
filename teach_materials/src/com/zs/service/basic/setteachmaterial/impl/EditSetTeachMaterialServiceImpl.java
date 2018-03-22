package com.zs.service.basic.setteachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByCourseCodeDAO;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByNameDAO;
import com.zs.dao.basic.setteachmaterial.SetTeachMaterialDAO;
import com.zs.dao.basic.setteachmaterialcourse.DelSetTeachMaterialCourseBystmIdAndCourseCodeDAO;
import com.zs.dao.basic.setteachmaterialcourse.FindSetTeachMaterialCourseBystmIdAndCourseCodeDAO;
import com.zs.dao.basic.setteachmaterialcourse.SetTeachMaterialCourseDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.basic.SetTeachMaterialCourse;
import com.zs.service.basic.setteachmaterial.EditSetTeachMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("editSetTeachMaterialService")
public class EditSetTeachMaterialServiceImpl extends EntityServiceImpl<SetTeachMaterial, SetTeachMaterialDAO> implements EditSetTeachMaterialService {
    @Resource
    private FindSetTeachMaterialByNameDAO findSetTeachMaterialByNameDAO;
    @Resource
    private FindSetTeachMaterialByCourseCodeDAO findSetTeachMaterialByCourseCodeDAO;
    @Resource
    private FindSetTeachMaterialCourseBystmIdAndCourseCodeDAO findSetTeachMaterialCourseBystmIdAndCourseCodeDAO;
    @Resource
    private DelSetTeachMaterialCourseBystmIdAndCourseCodeDAO delSetTeachMaterialCourseBystmIdAndCourseCodeDAO;
    @Resource
    private SetTeachMaterialCourseDAO setTeachMaterialCourseDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSetTeachMaterial(SetTeachMaterial setTeachMaterial, String loginName) throws Exception {
        if(null != setTeachMaterial) {
            //是否存在
            SetTeachMaterial isExistsSetTeachMaterial = super.get(setTeachMaterial.getId());
            if(null == isExistsSetTeachMaterial){
                throw new BusinessException("套教材不存在！");
            }
            //查询套教材名称是否已经存在
            SetTeachMaterial validSetTeachMaterial = findSetTeachMaterialByNameDAO.getTeachMaterialByName(setTeachMaterial.getName());
            if(null != validSetTeachMaterial && validSetTeachMaterial.getName().equals(setTeachMaterial.getName()) && validSetTeachMaterial.getId() != setTeachMaterial.getId()){
                throw new BusinessException("套教材名称已经存在！");
            }
            //查询套教材购买课程是否已经存在
            validSetTeachMaterial = findSetTeachMaterialByCourseCodeDAO.getSetTeachMaterialByCourseCode(setTeachMaterial.getBuyCourseCode());
            if(null != validSetTeachMaterial && validSetTeachMaterial.getBuyCourseCode().equals(setTeachMaterial.getBuyCourseCode()) && validSetTeachMaterial.getId() != setTeachMaterial.getId()){
                throw new BusinessException("购买课程已经存在！");
            }

            //比较修改前的购买课程和修改后的购买课程是否一致，不一致就删掉修改前的购买课程的关联
            if(!setTeachMaterial.getBuyCourseCode().equals(isExistsSetTeachMaterial.getBuyCourseCode())){
                //删掉修改前的购买课程的关联
                delSetTeachMaterialCourseBystmIdAndCourseCodeDAO.delSetTeachMaterialCourseBystmIdAndCourseCode(isExistsSetTeachMaterial.getId(), isExistsSetTeachMaterial.getBuyCourseCode());
                //查询修改后的购买课程是否在该套教材的关联课程中
                SetTeachMaterialCourse setTeachMaterialCourse =
                        findSetTeachMaterialCourseBystmIdAndCourseCodeDAO.getSetTeachMaterialCourseBystmIdAndCourseCode(setTeachMaterial.getId(), setTeachMaterial.getBuyCourseCode());
                //如果不存在，就要添加关联
                if(null == setTeachMaterialCourse || 0 >= setTeachMaterialCourse.getId()){
                    setTeachMaterialCourse = new SetTeachMaterialCourse();
                    setTeachMaterialCourse.setSetTeachMaterialId(setTeachMaterial.getId());
                    setTeachMaterialCourse.setCourseCode(setTeachMaterial.getBuyCourseCode());
                    setTeachMaterialCourse.setCreator(loginName);
                    setTeachMaterialCourseDAO.save(setTeachMaterialCourse);
                }
            }

            //修改套教材信息
            setTeachMaterial.setOperator(loginName);
            setTeachMaterial.setCreator(isExistsSetTeachMaterial.getCreator());
            setTeachMaterial.setCreateTime(isExistsSetTeachMaterial.getCreateTime());
            super.update(setTeachMaterial);
        }
    }
}
