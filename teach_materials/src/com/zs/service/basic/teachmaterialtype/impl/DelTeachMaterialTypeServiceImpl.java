package com.zs.service.basic.teachmaterialtype.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialByTypeIdDAO;
import com.zs.dao.basic.teachmaterialtype.TeachMaterialTypeDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.DelTeachMaterialTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("delTeachMaterialTypeService")
public class DelTeachMaterialTypeServiceImpl extends EntityServiceImpl<TeachMaterialType, TeachMaterialTypeDAO> implements DelTeachMaterialTypeService {

    @Resource
    private FindTeachMaterialByTypeIdDAO findTeachMaterialByTypeIdDAO;

    @Override
    public void delTeachMaterialType(Long id) throws Exception {
        TeachMaterialType teachMaterialType = super.get(id);
        if(null != teachMaterialType){
            //查询教材类别下面是否有教材，有教材不能删
            List<TeachMaterial> teachMaterialList = findTeachMaterialByTypeIdDAO.getTeachMaterialByTypeId(id);
            if(null != teachMaterialList && 0 < teachMaterialList.size()){
                throw new BusinessException("该类型下面还有教材，不能被删除！");
            }
            super.remove(teachMaterialType);
        }
    }
}
