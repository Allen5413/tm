package com.zs.service.basic.teachmaterialstock.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.basic.teachmaterialstock.TeachMaterialStockDAO;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.AddTeachMaterialStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/8/11.
 */
@Service("addTeachMaterialStockService")
public class AddTeachMaterialStockServiceImpl extends EntityServiceImpl<TeachMaterialStock, TeachMaterialStockDAO>
        implements AddTeachMaterialStockService {

    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;

    @Override
    public void add(TeachMaterialStock teachMaterialStock, String loginName) throws Exception {
        if(null != teachMaterialStock) {
            //查询该教材的渠道库存是否存在，存在的就提示
            TeachMaterialStock teachMaterialStock1 = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterialStock.getTeachMaterialId(), teachMaterialStock.getIssueChannelId());
            if(null != teachMaterialStock1){
                throw new BusinessException("该渠道已经存在库存！");
            }
            teachMaterialStock.setOperator(loginName);
            super.save(teachMaterialStock);
        }
    }
}
