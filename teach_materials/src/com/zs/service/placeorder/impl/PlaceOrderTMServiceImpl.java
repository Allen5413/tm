package com.zs.service.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuerange.FindIssueRangeBySpotCodeDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.placeorder.*;
import com.zs.dao.sale.studentbookordertm.FindTMCountForConfirmOrderDAO;
import com.zs.domain.basic.*;
import com.zs.domain.placeorder.PlaceOrderLog;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.placeorder.PlaceOrderTMService;
import com.zs.service.placeorder.bean.PlaceOrderStatus;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Allen on 2015/7/31.
 */
@Service("placeOrderTMService")
public class PlaceOrderTMServiceImpl extends EntityServiceImpl implements PlaceOrderTMService {

    @Resource
    private PlaceOrderTeachMaterialDAO placeOrderTeachMaterialDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private PlaceOrderDAO placeOrderDAO;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;
    @Resource
    private FindIssueRangeBySpotCodeDAO findIssueRangeBySpotCodeDAO;
    @Resource
    private FindTMCountForConfirmOrderDAO findTMCountForConfirmOrderDAO;
    @Resource
    private FindTMCountForConfirmPlaceOrderDAO findTMCountForConfirmPlaceOrderDAO;
    @Resource
    private PlaceOrderLogDAO placeOrderLogDAO;

    /**
     * 新增预订单的教材
     */
    @Override
    @Transactional
    public void add(String idAndCounts, String loginName, String spotCode, String address, String adminName, String phone, String tel, String postalCode) throws Exception {
        if(StringUtils.isEmpty(idAndCounts)){
            throw new BusinessException("没有传入教材信息");
        }
        //获取学习中心的渠道
        IssueRange issueRange = findIssueRangeBySpotCodeDAO.getIssueRangeBySpotCode(spotCode);
        if(null == issueRange){
            throw new BusinessException("该学习中心没有渠道");
        }
        //获取当前学期
        Semester semester = findNowSemesterService.getNowSemester();

        //新增订单
        String maxOrderCode = placeOrderDAO.queryMaxOrderNumber(spotCode, semester.getId());
        if(null == maxOrderCode){
            maxOrderCode = "0";
        }else{
            maxOrderCode = maxOrderCode.substring(maxOrderCode.length() - 6,maxOrderCode.length());
        }
        String orderCode = OrderCodeTools.createSpotOrderCode(semester.getYear(), semester.getQuarter(), spotCode, Integer.parseInt(maxOrderCode) + 1);
        //存储预订单数据
        TeachMaterialPlaceOrder teachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
        teachMaterialPlaceOrder.setCreateTime(DateTools.getLongNowTime());
        teachMaterialPlaceOrder.setCreator(loginName);
        teachMaterialPlaceOrder.setOperator(loginName);
        teachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
        teachMaterialPlaceOrder.setOrderCode(orderCode);
        teachMaterialPlaceOrder.setOrderStatus(TeachMaterialPlaceOrder.STATE_CONFIRMED);
        teachMaterialPlaceOrder.setSemesterId(semester.getId());
        teachMaterialPlaceOrder.setSpotCode(spotCode);
        teachMaterialPlaceOrder.setAddress(address);
        teachMaterialPlaceOrder.setAdminName(adminName);
        teachMaterialPlaceOrder.setPhone(phone);
        teachMaterialPlaceOrder.setTel(tel);
        teachMaterialPlaceOrder.setPostalCode(postalCode);
        teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
        teachMaterialPlaceOrderDAO.save(teachMaterialPlaceOrder);
        //新增订单明细
        String[] idAndCountArray = idAndCounts.split(",");
        if(null != idAndCountArray && 0 < idAndCountArray.length){
            boolean isStock = true;
            for(String idAndCount : idAndCountArray) {
                long tmId = Long.parseLong(idAndCount.split("_")[0]);
                int count = Integer.parseInt(idAndCount.split("_")[1]);
                //获取教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(tmId);
                if(null == teachMaterial || teachMaterial.getId() != tmId){
                    throw new BusinessException("教材信息没有找到");
                }
                //添加教材明细
                PlaceOrderTeachMaterial placeOrderTeachMaterial = new PlaceOrderTeachMaterial();
                placeOrderTeachMaterial.setOrderId(teachMaterialPlaceOrder.getId());
                placeOrderTeachMaterial.setTeachMaterialId(tmId);
                placeOrderTeachMaterial.setTmPrice(teachMaterial.getPrice());
                placeOrderTeachMaterial.setCount(Long.parseLong(String.valueOf(count)));
                placeOrderTeachMaterial.setIsSend(PlaceOrderTeachMaterial.IS_SEND_NOT);
                placeOrderTeachMaterial.setCreator(loginName);
                placeOrderTeachMaterial.setCreateTime(DateTools.getLongNowTime());
                placeOrderTeachMaterial.setOperator(loginName);
                placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
                placeOrderTeachMaterialDAO.save(placeOrderTeachMaterial);

                if(isStock) {
                    //检查所需教材库存是否满足
                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
                    //查询当前学期学生订单该渠道下的教材已经确认未发货的数量
                    BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
                    //查询当前学期预订单该渠道下的教材已生成未发货的数量
                    BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
                    int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
                    int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
                    if ((null == teachMaterialStock && 0 < count + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock()- confirmCount - confirmCount2 - count)) {
                        isStock = false;
                    }
                }
            }
            //如果库存不满足，就修改订单信息
            //2017-09-25  邸老师说不存在库存不足的订单了
//            if(!isStock){
//                teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_NOT);
//                teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
//            }

            //存储订单日志
            PlaceOrderLog PlaceOrderLog = new PlaceOrderLog();
            PlaceOrderLog.setOperateTime(DateTools.getLongNowTime());
            PlaceOrderLog.setOperator(loginName);
            PlaceOrderLog.setOrderId(teachMaterialPlaceOrder.getId());
            PlaceOrderLog.setState((PlaceOrderStatus.CREATED.getCode()));
            placeOrderLogDAO.save(PlaceOrderLog);
        }
    }
}
