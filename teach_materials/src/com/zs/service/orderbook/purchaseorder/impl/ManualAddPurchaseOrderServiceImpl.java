package com.zs.service.orderbook.purchaseorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.dao.orderbook.purchaseorder.FindPurchaseOrderForMaxCodeDAO;
import com.zs.dao.orderbook.purchaseorder.PurchaseOrderDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.orderbook.purchaseorder.ManualAddPurchaseOrderService;
import com.zs.service.orderbook.purchaseorder.PurchaseOrderService;
import com.zs.service.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialService;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 手动新增订单实现
 * Created by Allen on 2015/5/5.
 */
@Service("manualAddPurchaseOrderService")
public class ManualAddPurchaseOrderServiceImpl extends EntityServiceImpl<PurchaseOrder, PurchaseOrderDAO>
        implements ManualAddPurchaseOrderService {

    @Resource
    private PurchaseOrderTeachMaterialService purchaseOrderTeachMaterialService;
    @Resource
    private FindPurchaseOrderForMaxCodeDAO findPurchaseOrderForMaxCodeDAO;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindSetTeachMaterialByTMIdDAO findSetTeachMaterialByTMIdDAO;
    @Resource
    private FindTeachMaterialCourseByTMIdDAO findTeachMaterialCourseByTMIdDAO;

    @Override
    @Transactional
    public void addPurchaseOrderService(long issueChannelId, String idAndCounts, String loginName) throws Exception {
        //一个教材类型，对应的教材数据。教材类型id - 教材id - 采购数量
        Map<Long, Map<Long, Integer>> map = new HashMap<Long, Map<Long, Integer>>();
        if(StringUtils.isEmpty(idAndCounts)){
            throw new BusinessException("没有传入教材");
        }
        //获取当前学期
        Semester semester = findNowSemesterService.getNowSemester();
        String[] idAndCountArray = idAndCounts.split(",");
        if(null != idAndCountArray && 0 < idAndCountArray.length) {
            for (String idAndCount : idAndCountArray){
                long id = Long.parseLong(idAndCount.split("_")[0]);
                int count = Integer.parseInt(idAndCount.split("_")[1]);
                //查询教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(id);
                if(null != teachMaterial && id == teachMaterial.getId()){
                    long tmTypeId = teachMaterial.getTeachMaterialTypeId();
                    Map<Long, Integer> tmIdCountMap = map.get(tmTypeId);
                    if(null == tmIdCountMap || 1 > tmIdCountMap.size()){
                        tmIdCountMap = new HashMap<Long, Integer>();
                        tmIdCountMap.put(teachMaterial.getId(), count);
                    }else{
                        Integer num = tmIdCountMap.get(teachMaterial.getId());
                        if(null == num || 1 > num){
                            tmIdCountMap.put(teachMaterial.getId(), count);
                        }else{
                            tmIdCountMap.put(teachMaterial.getId(), num + count);
                        }
                    }
                    map.put(tmTypeId, tmIdCountMap);
                }
            }
            //遍历每种类型的教材，添加到数据库
            if(null != map && 0 < map.size()){
                //查询本学期最大的订单号
                int num = 0;
                PurchaseOrder maxCodePurchaseOrder = findPurchaseOrderForMaxCodeDAO.getPurchaseOrderForMaxCode(semester.getId());
                if(null != maxCodePurchaseOrder){
                    String maxOrderCode = maxCodePurchaseOrder.getCode();
                    num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-4, maxOrderCode.length()));
                }
                int i = 1;
                for (Map.Entry<Long, Map<Long, Integer>> entry : map.entrySet()) {
                    long tmTypeId = entry.getKey();
                    Map<Long, Integer> tmIdCountMap = entry.getValue();
                    String orderCode = OrderCodeTools.createPurchaseOrderCodeManual(semester.getYear(), semester.getQuarter(), num+i);
                    //填充订单数据
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setSemesterId(semester.getId());
                    purchaseOrder.setIssueChannelId(issueChannelId);
                    purchaseOrder.setCode(orderCode);
                    purchaseOrder.setTeachMaterialTypeId(tmTypeId);
                    purchaseOrder.setIsAuto(PurchaseOrder.ISAUTO_NO);
                    purchaseOrder.setCreator(loginName);
                    purchaseOrder.setOperator(loginName);
                    //保存订单数据
                    super.save(purchaseOrder);
                    //保存订单明细
                    this.addPurchaseOrderTM(semester.getId(), orderCode, tmIdCountMap, loginName);
                    i++;
                }
            }
        }
    }


    private void addPurchaseOrderTM(long semesterId, String orderCode, Map<Long, Integer> tmIdCountMap, String loginName)throws Exception{
        if(null != tmIdCountMap && 0 < tmIdCountMap.size()){
            for (Map.Entry<Long, Integer> entry : tmIdCountMap.entrySet()) {
                Long tmId = entry.getKey();
                Integer cuont = entry.getValue();

                //获取教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(tmId);
                if(null != teachMaterial && tmId == teachMaterial.getId() ) {
                    //查询教材关联的课程,这里是套教材，就去查购买课程；不是套教材，查关联课程
                    String courseCode = "";
                    if (teachMaterial.getIsSet() == TeachMaterial.ISSET_YES) {
                        SetTeachMaterial setTeachMaterial = findSetTeachMaterialByTMIdDAO.findSetTeachMaterialByTMId(tmId);
                        courseCode = setTeachMaterial.getBuyCourseCode();
                    } else {
                        List<TeachMaterialCourse> teachMaterialCourseList = findTeachMaterialCourseByTMIdDAO.findTeachMaterialCourseByTMId(tmId);
                        if (null != teachMaterialCourseList && 1 == teachMaterialCourseList.size()) {
                            courseCode = teachMaterialCourseList.get(0).getCourseCode();
                        }
                    }

                    //填充明细数据
                    PurchaseOrderTeachMaterial purchaseOrderTeachMaterial = new PurchaseOrderTeachMaterial();
                    purchaseOrderTeachMaterial.setCourseCode(courseCode);
                    purchaseOrderTeachMaterial.setSemesterId(semesterId);
                    purchaseOrderTeachMaterial.setCode(orderCode);
                    purchaseOrderTeachMaterial.setTeachMaterialId(tmId);
                    purchaseOrderTeachMaterial.setTeachMaterialCount(cuont);
                    purchaseOrderTeachMaterial.setPutStorageCount(0);
                    purchaseOrderTeachMaterial.setState(PurchaseOrderTeachMaterial.STATE_NORMAL);
                    purchaseOrderTeachMaterial.setCreator(loginName);
                    purchaseOrderTeachMaterial.setOperator(loginName);
                    //保存订单明细
                    purchaseOrderTeachMaterialService.save(purchaseOrderTeachMaterial);
                }
            }
        }
    }
}
