package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.spotteachmaterialstock.FindStockByTeachMaterialIdAndSpotCodeDao;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.placeorder.FindTMCountForConfirmPlaceOrderDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeDAO;
import com.zs.dao.sale.studentbookordertm.FindTMCountForConfirmOrderDAO;
import com.zs.dao.sale.studentbookordertm.StudentBookOrderTmDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.SpotTeachMaterialStock;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.domain.sync.Student;
import com.zs.service.sale.studentbookorder.SplitStudentBookOrderService;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
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
@Service("splitStudentBookOrderService")
public class SplitStudentBookOrderServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements SplitStudentBookOrderService {

    @Resource
    private FindStudentBookOrderTMByOrderCodeDAO findStudentBookOrderTMByOrderCodeDAO;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private FindStockByTeachMaterialIdAndSpotCodeDao findStockByTeachMaterialIdAndSpotCodeDao;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindStudentBookOrderForMaxCodeDAO findStudentBookOrderForMaxCodeDAO;
    @Resource
    private StudentBookOrderTmDAO studentBookOrderTmDAO;
    @Resource
    private FindTMCountForConfirmOrderDAO findTMCountForConfirmOrderDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindListByWhereDAO findStudentBookOrderTMCountForNotStockDAO;
    @Resource
    private FindTMCountForConfirmPlaceOrderDAO findTMCountForConfirmPlaceOrderDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void splitStudentBookOrder(long id, String loginName) throws Exception {
        boolean isNotStockAll = true;
        List<StudentBookOrderTM> notStockOrderTMList = new ArrayList<StudentBookOrderTM>();
        //查询订单信息
        StudentBookOrder studentBookOrder = super.get(id);
        if(null == studentBookOrder){
            throw new BusinessException("没有找到订单信息");
        }
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();
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
                    notStockOrderTMList.add(studentBookOrderTM);
                }else{
                    isNotStockAll = false;
                }
            }
        }else{
            //遍历明细，判断库存是否够
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                //教材数量小于1本的就不管
                if(1 <= studentBookOrderTM.getCount()) {
                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId());
                    //查询当前学期该渠道下的教材已经确认了的数量
                    BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                    //查询当前学期预订单该渠道下的教材已生成未发货的数量
                    BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                    int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
                    int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
                    if (null == teachMaterialStock || 1 > (teachMaterialStock.getStock() - confirmCount - confirmCount2)) {
                        notStockOrderTMList.add(studentBookOrderTM);
                    } else {
                        isNotStockAll = false;
                    }
                }
            }
        }
        //如果所有的教材都没有库存，就不用拆分了
        if(isNotStockAll){
            throw new BusinessException("订单下的教材都没有库存，不需要拆分");
        }
        //把原有订单修改为库存足够
        studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
        studentBookOrder.setOperator(loginName);
        studentBookOrder.setOperateTime(DateTools.getLongNowTime());
        //修改数据
        super.update(studentBookOrder);

        //重新建一个新的订单，来关联库存不够教材
        if(null != notStockOrderTMList && 0 < notStockOrderTMList.size()){
            //查询当前学期，订单号最大的一个订单
            StudentBookOrder studentBookOrder1 = findStudentBookOrderForMaxCodeDAO.getStudentBookOrderForMaxCode(semester.getId());
            if(null == studentBookOrder1 || StringUtils.isEmpty(studentBookOrder1.getOrderCode())){
                throw new BusinessException("没有找到最大订单信息");
            }
            //生成订单号
            String maxOrderCode = studentBookOrder1.getOrderCode();
            int orderCodeNum = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
            String orderCode = OrderCodeTools.createStudentOrderCodeManual(semester.getYear(), semester.getQuarter(), orderCodeNum+1);
            //添加订单
            StudentBookOrder studentBookOrder2 = new StudentBookOrder();
            studentBookOrder2.setSemesterId(semester.getId());
            studentBookOrder2.setIssueChannelId(studentBookOrder.getIssueChannelId());
            studentBookOrder2.setOrderCode(orderCode);
            studentBookOrder2.setStudentCode(studentBookOrder.getStudentCode());
            studentBookOrder2.setState(StudentBookOrder.STATE_CONFIRMED);
            studentBookOrder2.setIsStock(StudentBookOrder.ISSTOCK_NOT);
            studentBookOrder2.setIsSpotOrder(studentBookOrder.getIsSpotOrder());
            studentBookOrder2.setIsSendStudent(studentBookOrder.getIsSendStudent());
            studentBookOrder2.setStudentSign(StudentBookOrder.STUDENTSIGN_NOT);
            studentBookOrder2.setCreator(loginName);
            studentBookOrder2.setOperator(loginName);
            super.save(studentBookOrder2);
            //把库存不足的订单明细关联过来
            for(StudentBookOrderTM studentBookOrderTM : notStockOrderTMList){
                StudentBookOrderTM studentBookOrderTM1 = new StudentBookOrderTM();
                studentBookOrderTM1.setOrderCode(studentBookOrder2.getOrderCode());
                studentBookOrderTM1.setCourseCode(studentBookOrderTM.getCourseCode());
                studentBookOrderTM1.setTeachMaterialId(studentBookOrderTM.getTeachMaterialId());
                studentBookOrderTM1.setPrice(studentBookOrderTM.getPrice());
                studentBookOrderTM1.setCount(studentBookOrderTM.getCount());
                studentBookOrderTM1.setOperator(loginName);
                //新增
                studentBookOrderTmDAO.save(studentBookOrderTM1);
                studentBookOrderTmDAO.remove(studentBookOrderTM);
            }
        }
    }

    @Override
    @Transactional
    public synchronized void splitStudentBookOrderForAll(String spotCode, String loginName) throws Exception {
        try {
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semester.getId() + "");
            params.put("spotCode", spotCode);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("sbo.id", true);
            //查询订单教材所需数量
            List resultList = findStudentBookOrderTMCountForNotStockDAO.findListByWhere(params, sortMap);
            if (null == resultList || 1 > resultList.size()) {
                throw new BusinessException("当前学期没有库存不足的订单");
            } else {
                //要拆分的订单明细[orderId - orderTmId list]， 如果orderTmId list没有值，说明全部满足库存，也要修改订单状态
                Map<Long, List<Long>> splitOrderTmMap = new HashMap<Long, List<Long>>();
                //渠道下的教材库存map
                Map<Long, Map<Long, Integer>> channelTMStockMap = new HashMap<Long, Map<Long, Integer>>();
                //循环订单时，前面已经用了的教材
                Map<Long, Map<Long, Integer>> isUseChannelTMStockMap = new HashMap<Long, Map<Long, Integer>>();
                //订单是否可以拆分
                boolean isSplit = false;
                long beforeOrderId = 0;
                for (int i = 0; i < resultList.size(); i++) {
                    Object[] objects = (Object[]) resultList.get(i);
                    long orderId = Long.parseLong(objects[0].toString());
                    long issueChannelId = Long.parseLong(objects[1].toString());
                    long orderTMId = Long.parseLong(objects[2].toString());
                    long tmId = Long.parseLong(objects[3].toString());
                    int count = Integer.parseInt(objects[4].toString());
                    int stock = 0;

                    if (0 == i) {
                        beforeOrderId = orderId;
                    }
                    if (beforeOrderId != orderId) {
                        //如果上个订单没有一个库存满足的，就不处理上个订单
                        if(!isSplit){
                            splitOrderTmMap.remove(beforeOrderId);
                        }
                        beforeOrderId = orderId;
                        isSplit = false;
                    }

                    //如果该渠道下的教材库存查出来过，就不需要再查了。没查出来就存到map里
                    Map<Long, Integer> tmStockMap = channelTMStockMap.get(issueChannelId);
                    if (null == tmStockMap || null == tmStockMap.get(tmId)) {
                        if(null == tmStockMap) {
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
                        if(null == isUseTmMap) {
                            isUseTmMap = new HashMap<Long, Integer>();
                        }
                        isUseTmMap.put(tmId, count);
                        isUseChannelTMStockMap.put(issueChannelId, isUseTmMap);
                    } else {
                        isUseTmMap.put(tmId, isUseTmMap.get(tmId) + count);
                    }

                    //判断库存满足和不满足的情况，这里当库存满足时，也放入map里面。意思是如果库存全部满足，也需要修改该订单的状态
                    List<Long> orderTmIdList = splitOrderTmMap.get(orderId);
                    if (0 < stock - isUseTmMap.get(tmId) || 1 > count) {
                        isSplit = true;
                        if(null == orderTmIdList) {
                            splitOrderTmMap.put(orderId, new ArrayList<Long>());
                        }
                    }else{
                        if(null == orderTmIdList){
                            orderTmIdList = new ArrayList<Long>();
                        }
                        orderTmIdList.add(orderTMId);
                        splitOrderTmMap.put(orderId, orderTmIdList);
                    }
                }

                //查询当前学期，订单号最大的一个订单
                StudentBookOrder studentBookOrder1 = findStudentBookOrderForMaxCodeDAO.getStudentBookOrderForMaxCode(semester.getId());
                if(null == studentBookOrder1 || StringUtils.isEmpty(studentBookOrder1.getOrderCode())){
                    throw new BusinessException("没有找到最大订单信息");
                }
                //生成订单号
                String maxOrderCode = studentBookOrder1.getOrderCode();
                int orderCodeNum = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));

                //遍历需要处理的订单
                int num = 0;
                for(Map.Entry<Long, List<Long>> entry : splitOrderTmMap.entrySet()){
                    long orderId = entry.getKey();
                    List<Long> orderTmIdList = entry.getValue();

                    StudentBookOrder studentBookOrder = super.get(orderId);
                    if(null != studentBookOrder) {
                        //修改订单
                        studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
                        studentBookOrder.setOperator(loginName);
                        studentBookOrder.setOperateTime(DateTools.getLongNowTime());
                        super.update(studentBookOrder);

                        //拆分订单
                        if (null != orderTmIdList && 0 < orderTmIdList.size()) {
                            //新增一个订单
                            String orderCode = OrderCodeTools.createStudentOrderCodeManual(semester.getYear(), semester.getQuarter(), orderCodeNum+num+1);
                            StudentBookOrder addStudentBookOrder = new StudentBookOrder();
                            addStudentBookOrder.setOrderCode(orderCode);
                            addStudentBookOrder.setIssueChannelId(studentBookOrder.getIssueChannelId());
                            addStudentBookOrder.setSemesterId(studentBookOrder.getSemesterId());
                            addStudentBookOrder.setIsSpotOrder(studentBookOrder.getIsSpotOrder());
                            addStudentBookOrder.setStudentCode(studentBookOrder.getStudentCode());
                            addStudentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_NOT);
                            addStudentBookOrder.setState(studentBookOrder.getState());
                            addStudentBookOrder.setCreator(loginName);
                            addStudentBookOrder.setOperator(loginName);
                            super.save(addStudentBookOrder);

                            //把库存不足的订单明细关联过来
                            for(Long orderTmId : orderTmIdList){
                                StudentBookOrderTM studentBookOrderTM = studentBookOrderTmDAO.get(orderTmId);
                                if(null != studentBookOrderTM){
                                    studentBookOrderTM.setOrderCode(orderCode);
                                    studentBookOrderTM.setOperator(loginName);
                                    studentBookOrderTM.setOperateTime(DateTools.getLongNowTime());
                                    studentBookOrderTmDAO.update(studentBookOrderTM);
                                }
                            }
                        }
                    }
                    num++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
