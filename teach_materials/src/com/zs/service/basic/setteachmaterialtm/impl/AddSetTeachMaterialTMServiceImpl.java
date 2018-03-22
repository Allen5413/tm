package com.zs.service.basic.setteachmaterialtm.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterialtm.DelSetTeachMaterialTMBystmIdDAO;
import com.zs.dao.basic.setteachmaterialtm.SetTeachMaterialTMDAO;
import com.zs.domain.basic.SetTeachMaterialTM;
import com.zs.service.basic.setteachmaterialtm.AddSetTeachMaterialTMService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/21.
 */
@Service("addSetTeachMaterialTMService")
public class AddSetTeachMaterialTMServiceImpl extends EntityServiceImpl<SetTeachMaterialTM, SetTeachMaterialTMDAO>
        implements AddSetTeachMaterialTMService {

    @Resource
    private DelSetTeachMaterialTMBystmIdDAO delSetTeachMaterialTMBystmIdDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addSetTeachMaterialTM(Long setTeachMaterialId, String tmIds, String loginName) throws Exception {
        if(null == setTeachMaterialId || 0 == setTeachMaterialId){
            throw new BusinessException("没有传入套教材ID");
        }
        //删除掉以前的关联
        delSetTeachMaterialTMBystmIdDAO.delSetTeachMaterialTMBystmId(setTeachMaterialId);
        if(!StringUtils.isEmpty(tmIds)) {
            //循环增加新的关联
            String[] tmIdArray = tmIds.split(",");
            if(null != tmIdArray && 0 < tmIdArray.length) {
                for (String tmId : tmIdArray) {
                    SetTeachMaterialTM setTeachMaterialTM = new SetTeachMaterialTM();
                    setTeachMaterialTM.setSetTeachMaterialId(setTeachMaterialId);
                    setTeachMaterialTM.setTeachMaterialId(Long.parseLong(tmId));
                    setTeachMaterialTM.setCreator(loginName);
                    super.save(setTeachMaterialTM);
                }
            }
        }
    }
}
