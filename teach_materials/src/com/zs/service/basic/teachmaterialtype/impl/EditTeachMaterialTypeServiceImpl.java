package com.zs.service.basic.teachmaterialtype.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialtype.FindTeachMaterialTypeByCodeDAO;
import com.zs.dao.basic.teachmaterialtype.FindTeachMaterialTypeByNameDAO;
import com.zs.dao.basic.teachmaterialtype.TeachMaterialTypeDAO;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.EditTeachMaterialTypeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("editTeachMaterialTypeService")
public class EditTeachMaterialTypeServiceImpl extends EntityServiceImpl<TeachMaterialType, TeachMaterialTypeDAO> implements EditTeachMaterialTypeService {

    @Resource
    private FindTeachMaterialTypeByCodeDAO findTeachMaterialTypeByCodeDAO;
    @Resource
    private FindTeachMaterialTypeByNameDAO findTeachMaterialTypeByNameDAO;

    @Override
    public void editTeachMaterialType(TeachMaterialType teachMaterialType, String loginName) throws Exception {
        if(null != teachMaterialType) {
            //是否存在
            TeachMaterialType isExistsTeachMaterialType = super.get(teachMaterialType.getId());
            if(null == isExistsTeachMaterialType){
                throw new BusinessException("教材类型不存在！");
            }
            //查询教材类型编号是否已经存在
            TeachMaterialType validTeachMaterialType = findTeachMaterialTypeByCodeDAO.getTeachMaterialTypeByCode(teachMaterialType.getCode());
            if(null != validTeachMaterialType && validTeachMaterialType.getCode().equals(teachMaterialType.getCode()) && teachMaterialType.getId() != validTeachMaterialType.getId()){
                throw new BusinessException("教材类型编号已经存在！");
            }
            //查询教材类型名称是否已经存在
            validTeachMaterialType = findTeachMaterialTypeByNameDAO.getTeachMaterialTypeByName(teachMaterialType.getName());
            if(null != validTeachMaterialType && validTeachMaterialType.getName().equals(teachMaterialType.getName()) && validTeachMaterialType.getId() != teachMaterialType.getId()){
                throw new BusinessException("教材类型名称已经存在！");
            }
            teachMaterialType.setOperator(loginName);
            teachMaterialType.setCreator(isExistsTeachMaterialType.getCreator());
            teachMaterialType.setCreateTime(isExistsTeachMaterialType.getCreateTime());
            super.update(teachMaterialType);
        }
    }
}
