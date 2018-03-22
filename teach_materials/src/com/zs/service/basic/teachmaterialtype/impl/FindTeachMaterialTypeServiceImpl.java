package com.zs.service.basic.teachmaterialtype.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialtype.TeachMaterialTypeDAO;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findTeachMaterialTypeService")
public class FindTeachMaterialTypeServiceImpl extends EntityServiceImpl<TeachMaterialType, TeachMaterialTypeDAO> implements FindTeachMaterialTypeService {
}
