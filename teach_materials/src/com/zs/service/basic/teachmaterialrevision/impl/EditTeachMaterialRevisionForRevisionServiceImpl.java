package com.zs.service.basic.teachmaterialrevision.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdDAO;
import com.zs.dao.basic.teachmaterialrevision.TeachMaterialRevisionDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.basic.teachmaterialrevision.EditTeachMaterialRevisionService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
@Service("editTeachMaterialRevisionForRevisionService")
public class EditTeachMaterialRevisionForRevisionServiceImpl
        extends EntityServiceImpl<TeachMaterialRevision, TeachMaterialRevisionDAO>
        implements EditTeachMaterialRevisionService {

    @Resource
    private FindTeachMaterialRevisionBytmIdDAO findTeachMaterialRevisionBytmIdDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editTeachMaterialRevision(TeachMaterialRevision teachMaterialRevision, String loginName) throws Exception {
        if(null == teachMaterialRevision){
            throw new BusinessException("版次对象为空");
        }
        //获取版次信息
        TeachMaterialRevision isExistTMR = super.get(teachMaterialRevision.getId());
        if(null == isExistTMR){
            throw new BusinessException("版次信息没有找到");
        }
        //获取教材信息
        TeachMaterial teachMaterial = findTeachMaterialService.get(isExistTMR.getTeachMaterialId());
        if(null == teachMaterial){
            throw new BusinessException("没有找到教材信息");
        }

        //获取该教材的其他版次信息
        List<TeachMaterialRevision> list = findTeachMaterialRevisionBytmIdDAO.getTeachMaterialRevisionBytmId(teachMaterial.getId());
        if(null != list && 0 < list.size()){
            for(TeachMaterialRevision validTMR : list){
                if(teachMaterialRevision.getRevision().equals(validTMR.getRevision()) && validTMR.getId() != teachMaterialRevision.getId()){
                    throw new BusinessException("版次已经存在");
                }
            }
        }
        isExistTMR.setRevision(teachMaterialRevision.getRevision());
        isExistTMR.setOperator(loginName);
        isExistTMR.setOperateTime(DateTools.getLongNowTime());
        isExistTMR.setVersion(teachMaterialRevision.getVersion());
        super.update(isExistTMR);

        //修改教材信息
        if(isExistTMR.getIsNow() == TeachMaterialRevision.ISNOW_YES) {
            teachMaterial.setRevision(isExistTMR.getRevision());
            findTeachMaterialService.update(teachMaterial);
        }
    }
}
