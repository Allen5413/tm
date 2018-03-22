package com.zs.service.orderbook.teachmaterialratio.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.orderbook.purchaseorder.DelPurchaseOrderBySemesterIdDAO;
import com.zs.dao.orderbook.purchaseorderteachmaterial.DelPurchaseOrderTeachMaterialBySemesterIdDAO;
import com.zs.dao.orderbook.teachmaterialratio.FindTeachMaterialRatioBySemesterIdDAO;
import com.zs.dao.orderbook.teachmaterialratio.TeachMaterialRatioDAO;
import com.zs.domain.basic.*;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.domain.orderbook.TeachMaterialRatio;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.orderbook.purchaseorder.PurchaseOrderService;
import com.zs.service.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialService;
import com.zs.service.orderbook.teachmaterialratio.AddTeachMaterialRatioService;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by Allen on 2015/5/5.
*/
@Service("addTeachMaterialRatioService")
public class AddTeachMaterialRatioServiceImpl extends EntityServiceImpl<TeachMaterialRatio, TeachMaterialRatioDAO>
        implements AddTeachMaterialRatioService {

    @Resource
    private FindTeachMaterialRatioBySemesterIdDAO findTeachMaterialRatioBySemesterIdDAO;
    @Resource
    private DelPurchaseOrderBySemesterIdDAO delPurchaseOrderBySemesterIdDAO;
    @Resource
    private DelPurchaseOrderTeachMaterialBySemesterIdDAO delPurchaseOrderTeachMaterialBySemesterIdDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindListByWhereDAO countOldStudentChannelTMtypeTMCountForAddDAO;
    @Resource
    private FindListByWhereDAO countNewStudentChannelTMtypeTMCountForAddDAO;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private PurchaseOrderTeachMaterialService purchaseOrderTeachMaterialService;
    @Resource
    private FindPreviousSemesterService findPreviousSemesterService;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;


    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addTeachMaterialRatio(TeachMaterialRatio teachMaterialRatio, String loginName) throws Exception {
        if(null != teachMaterialRatio){
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            if(null == semester || null == semester.getId()){
                throw new BusinessException("没有设置当前学期！");
            }
            //获取上学期
            Semester beforeSemester = findPreviousSemesterService.getPreviousSemester();

            Long semesterId = semester.getId();
            //查询当前学期是否已经生成，已生成就删掉，从新生成
            this.isCreateTeachMaterialRatioByNowSemester(semesterId);
            //获取旧生数据
            List<Object[]> oldResultList = this.getOldStudent(semester.getYear(), semester.getQuarter());
            //获取新生数据
            List<Object[]> newResultList = this.getNewStudent(semester.getYear(), semester.getQuarter(), beforeSemester.getYear(), beforeSemester.getQuarter());
            //计算旧生订书量
            JSONArray oldStudentJsonArray = this.countStudentBookOrder(oldResultList, Float.valueOf(teachMaterialRatio.getOldRatio()));
            //计算新生订书量
            JSONArray newStudentJsonArray = this.countStudentBookOrder(newResultList, Float.valueOf(teachMaterialRatio.getNewRatio()));
            //合并总订书量
            JSONArray totalJsonArray = this.mergeTotal(oldStudentJsonArray, newStudentJsonArray);
            //减去我们拥有的教材库存
            this.subtractStock(totalJsonArray);
            //保存采购单系数
            this.saveTeachMaterialRatio(teachMaterialRatio, semesterId, loginName);
            //生成采购单
            this.savePurchaseOrder(totalJsonArray, semesterId, loginName, semester.getYear(), semester.getQuarter());
        }
    }

    /**
     * 查询当前学期是否已经生成，已生成就删掉，从新生成
     * @param semesterId
     * @throws Exception
     */
    protected void isCreateTeachMaterialRatioByNowSemester(Long semesterId)throws Exception{
        TeachMaterialRatio validTeachMaterialRatio =
                findTeachMaterialRatioBySemesterIdDAO.getTeachMaterialRatioBySemesterId(semesterId);
        if(null != validTeachMaterialRatio && validTeachMaterialRatio.getSemesterId() == semesterId){
            super.remove(validTeachMaterialRatio);
            //删掉对应的采购单
            delPurchaseOrderBySemesterIdDAO.delPurchaseOrderBySemesterId(semesterId);
            //删掉对应的采购单关联教材
            delPurchaseOrderTeachMaterialBySemesterIdDAO.delPurchaseOrderTeachMaterialBySemesterId(semesterId);
        }
    }

    /**
     * 获得旧生数据
     * @param year
     * @param quarter
     * @return
     * @throws Exception
     */
    protected List<Object[]> getOldStudent(int year, int quarter)throws Exception{
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("year", year+"");
        params.put("quarter", quarter+"");
        List<Object[]> tmList = countOldStudentChannelTMtypeTMCountForAddDAO.findListByWhere(params, null);
        return tmList;
    }

    /**
     * 获得新生数据
     * @param year
     * @param quarter
     * @return
     * @throws Exception
     */
    protected List<Object[]> getNewStudent(int year, int quarter, int enterYear, int enterQuarter)throws Exception{
        Map<String, String> params = new HashMap<String, String>(4);
        params.put("year", year+"");
        params.put("quarter", quarter+"");
        params.put("enterYear", enterYear+"");
        params.put("enterQuarter", enterQuarter+"");
        List<Object[]> tmList = countNewStudentChannelTMtypeTMCountForAddDAO.findListByWhere(params, null);
        return tmList;
    }

    /**
     * 计算订书量
     * @param resultList
     */
    protected JSONArray countStudentBookOrder(List<Object[]> resultList, float Ratio) {
        JSONArray jsonArray = new JSONArray();
        if(null != resultList && 0 < resultList.size()) {
            if(null != resultList && 0 < resultList.size()){
                for (Object[] obj : resultList) {
                    JSONObject json = new JSONObject();
                    //渠道id
                    json.put("issueChannelId", Long.parseLong(obj[0].toString()));
                    //教材类型id
                    json.put("tmTypeId", Long.parseLong(obj[1].toString()));
                    //课程编号
                    json.put("courseCode", obj[2].toString());
                    //教材id
                    json.put("tmId", Long.parseLong(obj[3].toString()));
                    //教材数量
                    json.put("count", Integer.parseInt(obj[4].toString()));
                    if(merge(json, jsonArray)) {
                        jsonArray.add(json);
                    }
                }
                //乘以系数
                for(int i=0; i<jsonArray.size(); i++) {
                    JSONObject totalJson = (JSONObject) jsonArray.get(i);
                    int totalCount = (Integer)totalJson.get("count");
                    totalJson.put("count", (int)Math.ceil(totalCount * Ratio));
                }
            }
        }
        return jsonArray;
    }

    /**
     * 合并相同渠道教材类型教材的数量
     * @param json
     * @return
     */
    protected boolean merge(JSONObject json, JSONArray jsonArray){
        boolean isMerge = true;
        if(null != jsonArray && 0 < jsonArray.size()){
            for(int i=0; i<jsonArray.size(); i++){
                JSONObject totalJson = (JSONObject)jsonArray.get(i);
                if(json.get("issueChannelId").toString().equals(totalJson.get("issueChannelId").toString()) &&
                        json.get("tmTypeId").toString().equals(totalJson.get("tmTypeId").toString()) &&
                        json.get("courseCode").toString().equals(totalJson.get("courseCode").toString()) &&
                        json.get("tmId").toString().equals(totalJson.get("tmId").toString())){
                    int totalCount = (Integer)totalJson.get("count");
                    int count = (Integer)json.get("count");
                    totalJson.put("count", totalCount + count);
                    isMerge = false;
                    break;
                }
            }
        }
        return isMerge;
    }

    /**
     * 计算出旧生和新生数据后，合并在一起
     * @param
     * @return
     */
    protected JSONArray mergeTotal(JSONArray oldStudentJsonArray, JSONArray newStudentJsonArray){
        if(null != newStudentJsonArray && 0 < newStudentJsonArray.size()){
            for(int i=0; i < newStudentJsonArray.size(); i++){
                JSONObject jsonObject = (JSONObject)newStudentJsonArray.get(i);
                if(this.merge(jsonObject, oldStudentJsonArray)) {
                    oldStudentJsonArray.add(jsonObject);
                }
            }
        }
        return oldStudentJsonArray;
    }

    /**
     * 减掉库存
     * @param
     * @return
     */
    protected JSONArray subtractStock(JSONArray jsonArray){
        if(null != jsonArray && 0 < jsonArray.size()){
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                TeachMaterial teachMaterial = findTeachMaterialService.get(Long.parseLong(jsonObject.get("tmId").toString()));
                if(null != teachMaterial) {
                    //查询渠道教材的库存
                    TeachMaterialStock teachMaterialStock =
                            findTeachMaterialStockBytmIdAndChannelIdDAO.
                                    getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), Long.parseLong(jsonObject.get("issueChannelId").toString()));

                    if(null != teachMaterialStock && null != teachMaterialStock.getStock() && 0 < teachMaterialStock.getStock()) {
                        int count = (Integer) jsonObject.get("count");
                        //库存不能有负数
                        jsonObject.put("count", (count - teachMaterialStock.getStock()) < 0 ? 0 : (count - teachMaterialStock.getStock()));
                    }
                }
            }
        }
        return jsonArray;
    }

    /**
     * 保存采购单系数
     * @param
     * @return
     */
    protected void saveTeachMaterialRatio(TeachMaterialRatio teachMaterialRatio, Long semesterId, String loginName){
        teachMaterialRatio.setSemesterId(semesterId);
        teachMaterialRatio.setCreator(loginName);
        teachMaterialRatio.setOperator(loginName);
        super.save(teachMaterialRatio);
    }

    /**
     * 生成采购单
     * @param
     * @return
     */
    protected void savePurchaseOrder(JSONArray jsonArray, Long semesterId, String loginName, int year, int quarter){
        //已经添加过的数据
        JSONArray alreadyAddJSONArray = new JSONArray();
        if(null != jsonArray && 0 < jsonArray.size()){
            for(int i=0; i<jsonArray.size(); i++){
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                Long issueChannelId = Long.parseLong(jsonObject.get("issueChannelId").toString());
                Long teachMaterialTypeId = Long.parseLong(jsonObject.get("tmTypeId").toString());
                String courseCode = jsonObject.get("courseCode").toString();
                Long tmId = Long.parseLong(jsonObject.get("tmId").toString());
                int count = Integer.parseInt(jsonObject.get("count").toString());

                boolean isAdd = false;
                //生成订单号
                String code = OrderCodeTools.createPurchaseOrderCodeAuto(year, quarter, alreadyAddJSONArray.size()+1);
                for (int j = 0; j < alreadyAddJSONArray.size(); j++){
                    JSONObject json = (JSONObject)alreadyAddJSONArray.get(j);
                    if(issueChannelId.toString().equals(json.get("issueChannelId").toString()) &&
                            teachMaterialTypeId.toString().equals(json.get("tmTypeId").toString())){
                        isAdd = true;
                        code = json.get("code").toString();
                        break;
                    }
                }
                if(!isAdd){
                    jsonObject.put("code", code);
                    alreadyAddJSONArray.add(jsonObject);
                    //执行数据保存操作
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setCode(code);
                    purchaseOrder.setIssueChannelId(issueChannelId);
                    purchaseOrder.setTeachMaterialTypeId(teachMaterialTypeId);
                    purchaseOrder.setIsAuto(PurchaseOrder.ISAUTO_YSE);
                    purchaseOrder.setSemesterId(semesterId);
                    purchaseOrder.setCreator(loginName);
                    purchaseOrder.setOperator(loginName);
                    purchaseOrderService.save(purchaseOrder);
                }
                //添加采购单关联教材
                savePurchaseOrderTeachMaterial(semesterId, code, courseCode, tmId, count, loginName);
            }
        }

    }

    /**
     * 生成采购单关联教材
     * @param
     * @return
     */
    protected void savePurchaseOrderTeachMaterial(Long semesterId, String code, String courseCode, Long tmId, int count, String loginName){
        PurchaseOrderTeachMaterial purchaseOrderTeachMaterial = new PurchaseOrderTeachMaterial();
        purchaseOrderTeachMaterial.setSemesterId(semesterId);
        purchaseOrderTeachMaterial.setCode(code);
        purchaseOrderTeachMaterial.setCourseCode(courseCode);
        purchaseOrderTeachMaterial.setTeachMaterialId(tmId);
        purchaseOrderTeachMaterial.setTeachMaterialCount(count);
        purchaseOrderTeachMaterial.setPutStorageCount(0);
        purchaseOrderTeachMaterial.setState(PurchaseOrderTeachMaterial.STATE_NORMAL);
        purchaseOrderTeachMaterial.setCreator(loginName);
        purchaseOrderTeachMaterial.setOperator(loginName);
        purchaseOrderTeachMaterialService.save(purchaseOrderTeachMaterial);
    }
}
