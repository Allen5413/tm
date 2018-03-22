package com.zs.service.orderbook.purchaseorderteachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialDAO;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.EditPurchaseOrderTMStateByIdService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/7/7.
 */
@Service("editPurchaseOrderTMStateByIdService")
public class EditPurchaseOrderTMStateByIdServiceImpl extends EntityServiceImpl<PurchaseOrderTeachMaterial, PurchaseOrderTeachMaterialDAO>
    implements EditPurchaseOrderTMStateByIdService{

    @Override
    public void editPurchaseOrderTMStateById(long id, int state, String loginName)throws Exception{
        PurchaseOrderTeachMaterial purchaseOrderTeachMaterial = super.get(id);
        if(null == purchaseOrderTeachMaterial){
            throw new BusinessException("没有找到要修改的信息");
        }
        purchaseOrderTeachMaterial.setState(state);
        purchaseOrderTeachMaterial.setOperator(loginName);
        purchaseOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
        super.update(purchaseOrderTeachMaterial);
    }
}
