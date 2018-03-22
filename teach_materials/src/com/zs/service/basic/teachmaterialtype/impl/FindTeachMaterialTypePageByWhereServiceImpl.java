package com.zs.service.basic.teachmaterialtype.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.teachmaterialtype.TeachMaterialTypeDAO;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypePageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findTeachMaterialTypePageByWhereService")
public class FindTeachMaterialTypePageByWhereServiceImpl
        extends EntityServiceImpl<TeachMaterialType, TeachMaterialTypeDAO>
        implements FindTeachMaterialTypePageByWhereService {

    @Resource
    public FindPageByWhereDAO findTeachMaterialTypePageByWhereDAO;

    @Override
    public PageInfo<TeachMaterialType> findPageByWhere(PageInfo<TeachMaterialType> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findTeachMaterialTypePageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
