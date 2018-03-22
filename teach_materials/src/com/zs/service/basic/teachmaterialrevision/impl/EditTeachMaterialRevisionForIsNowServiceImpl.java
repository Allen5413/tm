package com.zs.service.basic.teachmaterialrevision.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdDAO;
import com.zs.dao.basic.teachmaterialrevision.TeachMaterialRevisionDAO;
import com.zs.dao.placeorder.EditPlaceOrderTMForPriceDAO;
import com.zs.dao.sale.studentbookordertm.EditStudentBookOrderTMForPriceDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOrder;
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
@Service("editTeachMaterialRevisionForIsNowService")
public class EditTeachMaterialRevisionForIsNowServiceImpl
        extends EntityServiceImpl<TeachMaterialRevision, TeachMaterialRevisionDAO>
        implements EditTeachMaterialRevisionService {

    @Resource
    private FindTeachMaterialRevisionBytmIdDAO findTeachMaterialRevisionBytmIdDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private EditStudentBookOrderTMForPriceDAO editStudentBookOrderTMForPriceDAO;
    @Resource
    private EditPlaceOrderTMForPriceDAO editPlaceOrderTMForPriceDAO;

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
                //都修改为不是当前版次
                validTMR.setIsNow(TeachMaterialRevision.ISNOW_NO);
                super.update(validTMR);
            }
        }
        isExistTMR.setIsNow(TeachMaterialRevision.ISNOW_YES);
        isExistTMR.setOperator(loginName);
        isExistTMR.setOperateTime(DateTools.getLongNowTime());
        super.update(isExistTMR);

        //修改教材信息，修改学生订单的价格，订单状态是已发货之前的
        teachMaterial.setPrice(isExistTMR.getPrice());
        teachMaterial.setRevision(isExistTMR.getRevision());
        findTeachMaterialService.update(teachMaterial);

        //修改学生订单的价格，订单状态是已发货之前的
        editStudentBookOrderTMForPriceDAO.editStudentBookOrderTMForPrice(teachMaterial.getPrice(), StudentBookOrder.STATE_SEND, teachMaterial.getId());
        //修改预订单的价格，状态是已发货前的
        editPlaceOrderTMForPriceDAO.editPlaceOrderTMForPrice(teachMaterial.getPrice(), TeachMaterialPlaceOrder.STATE_SEND, teachMaterial.getId());
    }
}
