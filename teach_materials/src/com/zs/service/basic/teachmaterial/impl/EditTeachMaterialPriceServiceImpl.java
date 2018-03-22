package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.dao.placeorder.EditPlaceOrderTMForPriceDAO;
import com.zs.dao.sale.onceordertm.EditOnceOrderTMForPriceDAO;
import com.zs.dao.sale.studentbookordertm.EditStudentBookOrderTMForPriceDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.basic.teachmaterial.EditTeachMaterialPriceService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/20.
 */
@Service("editTeachMaterialPriceService")
public class EditTeachMaterialPriceServiceImpl extends EntityServiceImpl<TeachMaterial, TeachMaterialDAO>
    implements EditTeachMaterialPriceService {

    @Resource
    private EditStudentBookOrderTMForPriceDAO editStudentBookOrderTMForPriceDAO;
    @Resource
    private EditPlaceOrderTMForPriceDAO editPlaceOrderTMForPriceDAO;
    @Resource
    private EditOnceOrderTMForPriceDAO editOnceOrderTMForPriceDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editTeachMaterialPrice(TeachMaterial teachMaterial, String loginName) {
        //是否存在
        TeachMaterial isExistsTeachMaterial = super.get(teachMaterial.getId());
        if (null == isExistsTeachMaterial) {
            throw new BusinessException("教材不存在！");
        }
        isExistsTeachMaterial.setPrice(teachMaterial.getPrice());
        isExistsTeachMaterial.setVersion(teachMaterial.getVersion());
        isExistsTeachMaterial.setOperator(loginName);
        isExistsTeachMaterial.setOperateTime(DateTools.getLongNowTime());
        super.update(isExistsTeachMaterial);

        //修改学生订单的价格，订单状态是已发送之前的
        editStudentBookOrderTMForPriceDAO.editStudentBookOrderTMForPrice(isExistsTeachMaterial.getPrice(), StudentBookOrder.STATE_SEND, isExistsTeachMaterial.getId());
        //修改学生一次性订单的价格，订单状态是已发送之前的
        editOnceOrderTMForPriceDAO.edit(isExistsTeachMaterial.getPrice(), StudentBookOnceOrder.STATE_SEND, isExistsTeachMaterial.getId());
        //修改预订单的价格，状态是已发货前的
        editPlaceOrderTMForPriceDAO.editPlaceOrderTMForPrice(teachMaterial.getPrice(), TeachMaterialPlaceOrder.STATE_SEND, teachMaterial.getId());
    }
}

