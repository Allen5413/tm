package com.zs.service.orderbook.purchaseorderteachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialDAO;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.StorageService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Entity;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 订书单入库操作实现
 * Created by Allen on 2015/5/5.
 */
@Service("storageService")
public class StorageServiceImpl extends EntityServiceImpl<PurchaseOrderTeachMaterial, PurchaseOrderTeachMaterialDAO>
        implements StorageService {

    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void storage(Long[] ids, String[] counts, Long channelId, String loginName) throws Exception {
        if(null == ids || 0 >= ids.length){
            throw new BusinessException("没有传入订单关联教材ID");
        }
        if(null == counts || 0 >= counts.length){
            throw new BusinessException("没有传入教材入库数量");
        }
        if(null == channelId || 0 >= channelId){
            throw new BusinessException("没有传入发行渠道ID");
        }
        for (int i = 0; i < ids.length; i++) {
            long id = ids[i];
            int count = StringUtils.isEmpty(counts[i]) ? 0 : Integer.parseInt(counts[i]);

            //入库数量大于0，操作数据库
            if(0 < count) {
                //得到对象
                PurchaseOrderTeachMaterial purchaseOrderTeachMaterial = super.get(id);
                if (null != purchaseOrderTeachMaterial) {
                    purchaseOrderTeachMaterial.setPutStorageCount(null == purchaseOrderTeachMaterial.getPutStorageCount() ? count : purchaseOrderTeachMaterial.getPutStorageCount() + count);
                    purchaseOrderTeachMaterial.setOperator(loginName);
                    purchaseOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
                    super.update(purchaseOrderTeachMaterial);

                    //增加教材发行渠道的库存数量
                    TeachMaterialStock teachMaterialStock =
                            findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(purchaseOrderTeachMaterial.getTeachMaterialId(), channelId);
                    if (null != teachMaterialStock && null != teachMaterialStock.getId()) {
                        teachMaterialStock.setStock(teachMaterialStock.getStock() + count);
                        teachMaterialStock.setOperator(loginName);
                        teachMaterialStock.setOperateTime(DateTools.getLongNowTime());
                        findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
                    } else {
                        teachMaterialStock = new TeachMaterialStock();
                        teachMaterialStock.setIssueChannelId(channelId);
                        teachMaterialStock.setTeachMaterialId(purchaseOrderTeachMaterial.getTeachMaterialId());
                        teachMaterialStock.setStock((long) count);
                        teachMaterialStock.setOperator(loginName);
                        findTeachMaterialStockBytmIdAndChannelIdDAO.save(teachMaterialStock);
                    }
                }
            }
        }
    }
}
