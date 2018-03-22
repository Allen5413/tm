package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.spotteachmaterialstock.FindStockByTeachMaterialIdAndSpotCodeDao;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.placeorder.FindTMCountForConfirmPlaceOrderDAO;
import com.zs.dao.sale.studentbookorder.BatchStudentBookOrderDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeDAO;
import com.zs.dao.sale.studentbookordertm.FindTMCountForConfirmOrderDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.SpotTeachMaterialStock;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.domain.sync.Student;
import com.zs.service.sale.studentbookorder.RefreshStudentBookOrderService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/8.
 */
@Service("refreshStudentBookOrderService")
public class RefreshStudentBookOrderServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements RefreshStudentBookOrderService {

    @Resource
    private FindStudentBookOrderTMByOrderCodeDAO findStudentBookOrderTMByOrderCodeDAO;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private FindStockByTeachMaterialIdAndSpotCodeDao findStockByTeachMaterialIdAndSpotCodeDao;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindTMCountForConfirmOrderDAO findTMCountForConfirmOrderDAO;
    @Resource
    private FindTMCountForConfirmPlaceOrderDAO findTMCountForConfirmPlaceOrderDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private BatchStudentBookOrderDAO batchStudentBookOrderDAO;
    @Resource
    private FindListByWhereDAO findStudentBookOrderTMCountForNotStockDAO;

    @Override
    public void refreshStudentBookOrder(long id, String loginName) throws Exception {
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();

        //库存是否够
        boolean isStock = true;
        //查询订单信息
        StudentBookOrder studentBookOrder = super.get(id);
        if(null == studentBookOrder){
            throw new BusinessException("没有找到订单信息");
        }
        //查询订单明细
        List<StudentBookOrderTM> studentBookOrderTMList = findStudentBookOrderTMByOrderCodeDAO.findStudentBookOrderTMByOrderCode(studentBookOrder.getOrderCode());
        if(null == studentBookOrderTMList || 1 > studentBookOrderTMList.size()){
            throw new BusinessException("没有找到该订单的明细");
        }
        //是否是学习中心订单，是的话，教材库存获取学习中心的库存；不是就获取渠道库存
        if(studentBookOrder.getIsSpotOrder() == StudentBookOrder.ISSPOTORDER_YES){
            //查询学生信息
            Student student = findStudentByCodeDAO.getStudentByCode(studentBookOrder.getStudentCode());
            if(null == student){
                throw new BusinessException("没有找到订单的学生信息");
            }
            if(StringUtils.isEmpty(student.getSpotCode())){
                throw new BusinessException("没有找到学习中心信息");
            }
            //遍历明细，判断库存是否够
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                SpotTeachMaterialStock spotTeachMaterialStock = findStockByTeachMaterialIdAndSpotCodeDao.getStockByTeachMaterialIdAndSpotCode(studentBookOrderTM.getTeachMaterialId(), student.getSpotCode());
                if(null == spotTeachMaterialStock || 1 > spotTeachMaterialStock.getStock()){
                    isStock = false;
                    break;
                }
            }
        }else{
            //遍历明细，判断库存是否够
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                //如果需要购买这本书
                if( 0 < studentBookOrderTM.getCount()) {
                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId());
                    //查询当前学期学生订单该渠道下的教材已经确认未发货的数量
                    BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                    //查询当前学期预订单该渠道下的教材已生成未发货的数量
                    BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                    int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
                    int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
                    if (null == teachMaterialStock) {
                        isStock = false;
                        break;
                    } else {
                        Long stock = null == teachMaterialStock.getStock() ? 0 : teachMaterialStock.getStock();
                        if (0 > (stock - confirmCount - confirmCount2)) {
                            isStock = false;
                            break;
                        }
                    }
                }
            }
        }
        //如果库存不足，不修改，否则修改
        if(isStock){
            studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
            studentBookOrder.setOperator(loginName);
            //修改数据
            super.update(studentBookOrder);
        }
    }

    @Override
    @Transactional
    public synchronized void refreshStudentBookOrderForAll(String loginName) throws Exception {
        try {
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semester.getId() + "");
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("sbo.id", true);
            //查询订单教材所需数量
            List resultList = findStudentBookOrderTMCountForNotStockDAO.findListByWhere(params, sortMap);
            if (null == resultList || 1 > resultList.size()) {
                throw new BusinessException("当前学期没有库存不足的订单");
            } else {
                //库存满足的订单
                List<StudentBookOrder> studentBookOrderList = new ArrayList<StudentBookOrder>();
                //渠道下的教材库存map
                Map<Long, Map<Long, Integer>> channelTMStockMap = new HashMap<Long, Map<Long, Integer>>();
                //循环订单时，前面已经用了的教材
                Map<Long, Map<Long, Integer>> isUseChannelTMStockMap = new HashMap<Long, Map<Long, Integer>>();
                //订单是否库存够
                boolean isStock = true;
                long beforeOrderId = 0;
                for (int i = 0; i < resultList.size(); i++) {
                    Object[] objects = (Object[]) resultList.get(i);
                    long orderId = Long.parseLong(objects[0].toString());
                    long issueChannelId = Long.parseLong(objects[1].toString());
                    long tmId = Long.parseLong(objects[3].toString());
                    int count = Integer.parseInt(objects[4].toString());
                    int stock = 0;

                    if (0 == i) {
                        beforeOrderId = orderId;
                    }
                    if (beforeOrderId != orderId) {
                        if (isStock) {
                            StudentBookOrder studentBookOrder = super.get(beforeOrderId);
                            if (null != studentBookOrder) {
                                studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
                                studentBookOrder.setOperator(loginName);
                                studentBookOrder.setOperateTime(DateTools.getLongNowTime());
                                studentBookOrderList.add(studentBookOrder);
                            }
                        }
                        isStock = true;
                        beforeOrderId = orderId;
                    }

                    //如果需要购买这本书
                    if(0 < count) {
                        //如果该渠道下的教材库存查出来过，就不需要再查了。没查出来就存到map里
                        Map<Long, Integer> tmStockMap = channelTMStockMap.get(issueChannelId);
                        if (null == tmStockMap || null == tmStockMap.get(tmId)) {
                            if (null == tmStockMap) {
                                tmStockMap = new HashMap<Long, Integer>();
                            }
                            //查询当前渠道教材的数量
                            TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(tmId, issueChannelId);
                            //查询当前学期学生订单该渠道下的教材已经确认未发货的数量
                            BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(tmId, issueChannelId, semester.getId());
                            //查询当前学期预订单该渠道下的教材已生成未发货的数量
                            BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(tmId, issueChannelId, semester.getId());
                            int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
                            int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
                            if (null == teachMaterialStock || null == teachMaterialStock.getStock()) {
                                stock = 0;
                            } else {
                                stock = Integer.parseInt(teachMaterialStock.getStock().toString()) - confirmCount - confirmCount2;
                            }
                            tmStockMap.put(tmId, stock);
                            channelTMStockMap.put(issueChannelId, tmStockMap);
                        } else {
                            stock = null == tmStockMap.get(tmId) ? 0 : tmStockMap.get(tmId);
                        }

                        //得到该渠道下的教材之前订单要发的数量
                        Map<Long, Integer> isUseTmMap = isUseChannelTMStockMap.get(issueChannelId);
                        if (null == isUseTmMap || null == isUseTmMap.get(tmId)) {
                            if (null == isUseTmMap) {
                                isUseTmMap = new HashMap<Long, Integer>();
                            }
                            isUseTmMap.put(tmId, count);
                            isUseChannelTMStockMap.put(issueChannelId, isUseTmMap);
                        } else {
                            isUseTmMap.put(tmId, isUseTmMap.get(tmId) + count);
                        }

                        //当库存不满足的时候
                        if (0 > stock - isUseTmMap.get(tmId) && isStock) {
                            isStock = false;
                        }
                    }
                }

                if (null != studentBookOrderList) {
                    batchStudentBookOrderDAO.batchEdit(studentBookOrderList, 1000);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
