package com.zs.service.basic.teachmaterialrevision.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdDAO;
import com.zs.dao.basic.teachmaterialrevision.TeachMaterialRevisionDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.basic.teachmaterialrevision.AddTeachMaterialRevisionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/3.
 */
@Service("addTeachMaterialRevisionService")
public class AddTeachMaterialRevisionServiceImpl
    extends EntityServiceImpl<TeachMaterialRevision, TeachMaterialRevisionDAO>
    implements AddTeachMaterialRevisionService{

    @Resource
    private FindTeachMaterialRevisionBytmIdDAO findTeachMaterialRevisionBytmIdDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addTeachMaterialRevision(TeachMaterialRevision teachMaterialRevision, String loginName) throws Exception {
        if(null == teachMaterialRevision){
            throw new BusinessException("版次对象为空");
        }
        //获取教材信息
        TeachMaterial teachMaterial = findTeachMaterialService.get(teachMaterialRevision.getTeachMaterialId());
        if(null == teachMaterial){
            throw new BusinessException("没有找到教材信息");
        }

        //获取该教材的其他版次信息
        List<TeachMaterialRevision> list = findTeachMaterialRevisionBytmIdDAO.getTeachMaterialRevisionBytmId(teachMaterial.getId());
        if(null != list && 0 < list.size()){
            for(TeachMaterialRevision validTMR : list){
                if(validTMR.getRevision().equals(teachMaterialRevision.getRevision())){
                    throw new BusinessException("版次已经存在");
                }
                //修改之前的版次为不是当前版次
                validTMR.setIsNow(TeachMaterialRevision.ISNOW_NO);
                super.update(validTMR);
            }
        }
        teachMaterialRevision.setIsNow(TeachMaterialRevision.ISNOW_YES);
        teachMaterialRevision.setCreator(loginName);
        teachMaterialRevision.setOperator(loginName);
        super.save(teachMaterialRevision);
        //修改教材信息
        teachMaterial.setPrice(teachMaterialRevision.getPrice());
        teachMaterial.setRevision(teachMaterialRevision.getRevision());
        findTeachMaterialService.update(teachMaterial);
    }
}
