package com.zs.service.sale.oncepurchaseorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.oncepurchaseorder.FindOncePurchaseOrderByCodeDAO;
import com.zs.dao.sale.oncepurchaseorder.OncePurchaseOrderDAO;
import com.zs.domain.sale.OncePurchaseOrder;
import com.zs.service.sale.oncepurchaseorder.EditOncePurchaseOrderStateByCodeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/6/22.
 */
@Service("editOncePurchaseOrderStateByCodeService")
public class EditOncePurchaseOrderStateByCodeServiceImpl extends EntityServiceImpl<OncePurchaseOrder, OncePurchaseOrderDAO>
    implements EditOncePurchaseOrderStateByCodeService {

    @Resource
    private FindOncePurchaseOrderByCodeDAO findOncePurchaseOrderByCodeDAO;

    @Override
    public void editor(String code, String loginName) throws Exception {
        OncePurchaseOrder oncePurchaseOrder = findOncePurchaseOrderByCodeDAO.find(code);
        if(null == oncePurchaseOrder){
            throw new BusinessException("没有找到该采购单");
        }
        oncePurchaseOrder.setState(OncePurchaseOrder.STATE_YSE);
        oncePurchaseOrder.setOperator(loginName);
        oncePurchaseOrder.setOperateTime(DateTools.getLongNowTime());
        super.update(oncePurchaseOrder);
    }
}
