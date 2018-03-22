package com.zs.service.basic.teachmaterialrevision.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialrevision.TeachMaterialRevisionDAO;
import com.zs.dao.placeorder.EditPlaceOrderTMForPriceDAO;
import com.zs.dao.sale.onceordertm.EditOnceOrderTMForPriceDAO;
import com.zs.dao.sale.studentbookordertm.EditStudentBookOrderTMForPriceDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.basic.teachmaterialrevision.EditTeachMaterialRevisionService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/6/3.
 */
@Service("editTeachMaterialRevisionForPriceService")
public class EditTeachMaterialRevisionForPriceServiceImpl
    extends EntityServiceImpl<TeachMaterialRevision, TeachMaterialRevisionDAO>
    implements EditTeachMaterialRevisionService {

    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private EditStudentBookOrderTMForPriceDAO editStudentBookOrderTMForPriceDAO;
    @Resource
    private EditPlaceOrderTMForPriceDAO editPlaceOrderTMForPriceDAO;
    @Resource
    private EditOnceOrderTMForPriceDAO editOnceOrderTMForPriceDAO;

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

        isExistTMR.setPrice(teachMaterialRevision.getPrice());
        isExistTMR.setOperator(loginName);
        isExistTMR.setOperateTime(DateTools.getLongNowTime());
        isExistTMR.setVersion(teachMaterialRevision.getVersion());
        super.update(isExistTMR);

        //修改教材信息，修改学生订单的价格，订单状态是已发货之前的
        if(isExistTMR.getIsNow() == TeachMaterialRevision.ISNOW_YES) {
            teachMaterial.setPrice(isExistTMR.getPrice());
            findTeachMaterialService.update(teachMaterial);
            //修改学生订单的价格，订单状态是已发货之前的
            editStudentBookOrderTMForPriceDAO.editStudentBookOrderTMForPrice(teachMaterial.getPrice(), StudentBookOrder.STATE_SEND, teachMaterial.getId());
            //修改学生一次性订单的价格，订单状态是已发送之前的
            editOnceOrderTMForPriceDAO.edit(teachMaterial.getPrice(), StudentBookOnceOrder.STATE_SEND, teachMaterial.getId());
            //修改预订单的价格，状态是已发货前的
            editPlaceOrderTMForPriceDAO.editPlaceOrderTMForPrice(teachMaterial.getPrice(), TeachMaterialPlaceOrder.STATE_SEND, teachMaterial.getId());
        }
    }
}
