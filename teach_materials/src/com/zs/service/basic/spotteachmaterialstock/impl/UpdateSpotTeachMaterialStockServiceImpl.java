package com.zs.service.basic.spotteachmaterialstock.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotteachmaterialstock.FindStockByTeachMaterialIdAndSpotCodeDao;
import com.zs.dao.basic.spotteachmaterialstock.UpdateSpotTeachMaterialStockDao;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.domain.basic.SpotTeachMaterialStock;
import com.zs.service.basic.spotteachmaterialstock.UpdateSpotTeachMaterialStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 实现了修改学习中心教材库的接口
 * Created by LihongZhang on 2015/5/8.
 */
@Service("editSpotTeachMaterialStockService")
public class UpdateSpotTeachMaterialStockServiceImpl extends EntityServiceImpl<SpotTeachMaterialStock,UpdateSpotTeachMaterialStockDao> implements UpdateSpotTeachMaterialStockService {

    @Resource
    private UpdateSpotTeachMaterialStockDao updateSpotTeachMaterialStockDao;

    @Resource
    private FindStockByTeachMaterialIdAndSpotCodeDao findStockByTeachMaterialIdAndSpotCodeDao;

    @Resource
    private TeachMaterialDAO teachMaterialDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void updateSpotTeachMaterialStock(SpotTeachMaterialStock spotTeachMaterialStock, int changeNum) throws Exception {
        if (null != spotTeachMaterialStock){
            //通过教材id和学习中心编号取得值，
            SpotTeachMaterialStock validSpotTeachMaterialStock = findStockByTeachMaterialIdAndSpotCodeDao.getStockByTeachMaterialIdAndSpotCode(spotTeachMaterialStock.getTeachMaterialId(), spotTeachMaterialStock.getSpotCode());
            //如果取得的值不为空时,则比较库存和修改数据
            if(null != validSpotTeachMaterialStock){
                //验证库存数量是否大于要修改的数量
                if ( 0 > validSpotTeachMaterialStock.getStock()-changeNum){
                    //当库存数量不足时，修改库存数量为0
                   updateSpotTeachMaterialStockDao.updateSpotTeachMaterialStock(0,spotTeachMaterialStock.getSpotCode(),spotTeachMaterialStock.getTeachMaterialId());
                }else {
                    //当库存充足时，则从库存中减去需要修改的数量
                    updateSpotTeachMaterialStockDao.updateSpotTeachMaterialStock(validSpotTeachMaterialStock.getStock()-changeNum,spotTeachMaterialStock.getSpotCode(),spotTeachMaterialStock.getTeachMaterialId());
                }
            }
            //通过id无法得到相应的学习教材中心库记录
            else {
                throw new BusinessException("所修改的学习中心教材库存记录不存在");
            }
        }
    }
}
