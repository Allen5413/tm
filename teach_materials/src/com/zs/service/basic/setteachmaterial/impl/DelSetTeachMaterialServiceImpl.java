package com.zs.service.basic.setteachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.SetTeachMaterialDAO;
import com.zs.dao.basic.setteachmaterialcourse.DelSetTeachMaterialCourseBystmIdDAO;
import com.zs.dao.basic.setteachmaterialtm.DelSetTeachMaterialTMBystmIdDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.DelSetTeachMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("delSetTeachMaterialService")
public class DelSetTeachMaterialServiceImpl extends EntityServiceImpl<SetTeachMaterial, SetTeachMaterialDAO> implements DelSetTeachMaterialService {

    @Resource
    private DelSetTeachMaterialCourseBystmIdDAO delSetTeachMaterialCourseBystmIdDAO;
    @Resource
    private DelSetTeachMaterialTMBystmIdDAO delSetTeachMaterialTMBystmIdDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void delSetTeachMaterial(Long id) throws Exception {
        SetTeachMaterial setTeachMaterial = super.get(id);
        if(null != setTeachMaterial){
            //删除套教材与课程的关联
            delSetTeachMaterialCourseBystmIdDAO.delSetTeachMaterialCourseBystmId(id);
            //删除套教材与教材的关联
            delSetTeachMaterialTMBystmIdDAO.delSetTeachMaterialTMBystmId(id);
            //删除套教材
            super.remove(setTeachMaterial);
        }
    }
}
