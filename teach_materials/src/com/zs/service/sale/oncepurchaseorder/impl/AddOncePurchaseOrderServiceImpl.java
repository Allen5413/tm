package com.zs.service.sale.oncepurchaseorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.placeorder.PlaceOrderLogDAO;
import com.zs.dao.placeorder.placeorder.EditPlaceOrderForStateDoingDAO;
import com.zs.dao.sale.onceorder.EditOnceOrderForStateDoingDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.dao.sale.oncepurchaseorder.FindAddOncePurchaeOrderTMListDAO;
import com.zs.dao.sale.oncepurchaseorder.FindOncePurchaseOrderForMaxCodeDAO;
import com.zs.dao.sale.oncepurchaseorder.OncePurchaseOrderDAO;
import com.zs.dao.sale.oncepurchaseordertm.OncePurchaseOrderTMDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.OncePurchaseOrder;
import com.zs.domain.sale.OncePurchaseOrderTM;
import com.zs.service.sale.oncepurchaseorder.AddOncePurchaseOrderService;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/6/21.
 */
@Service("addOncePurchaseOrderService")
public class AddOncePurchaseOrderServiceImpl extends EntityServiceImpl<OncePurchaseOrder, OncePurchaseOrderDAO> implements AddOncePurchaseOrderService {

    @Resource
    private FindAddOncePurchaeOrderTMListDAO findAddOncePurchaeOrderTMListDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private OncePurchaseOrderTMDAO oncePurchaseOrderTMDAO;
    @Resource
    private EditOnceOrderForStateDoingDAO editOnceOrderForStateDoingDAO;
    @Resource
    private EditPlaceOrderForStateDoingDAO editPlaceOrderForStateDoingDAO;
    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;
    @Resource
    private PlaceOrderLogDAO placeOrderLogDAO;
    @Resource
    private FindOncePurchaseOrderForMaxCodeDAO findOncePurchaseOrderForMaxCodeDAO;

    @Override
    @Transactional
    public void add(String loginName) throws Exception{
        //已经添加过的数据
        JSONArray alreadyAddJSONArray = new JSONArray();
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();
        if(null == semester || null == semester.getId()){
            throw new BusinessException("没有设置当前学期！");
        }
        //获取要采购的教材数据
        List<Object[]> list = findAddOncePurchaeOrderTMListDAO.find();

        for(Object[] objs : list){
            long issueChannelId = Long.parseLong(objs[0].toString());
            long teachMaterialTypeId = Long.parseLong(objs[1].toString());
            String courseCode = objs[2].toString();
            long tmId = Long.parseLong(objs[3].toString());
            int count = Integer.parseInt(objs[4].toString());

            boolean isAdd = false;
            //得到当前学期最大的订单号
            int num = 0;
            OncePurchaseOrder maxCodeOrder = findOncePurchaseOrderForMaxCodeDAO.find(semester.getId());
            if(null != maxCodeOrder){
                String maxOrderCode = maxCodeOrder.getCode();
                num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
            }
            //生成订单号
            String code = OrderCodeTools.createPurchaseOrderCodeOnce(semester.getYear(), semester.getQuarter(), num + alreadyAddJSONArray.size() + 1);
            for (int j = 0; j < alreadyAddJSONArray.size(); j++){
                JSONObject json = (JSONObject)alreadyAddJSONArray.get(j);
                long icId = null == json.get("issueChannelId") ? -1 : Long.parseLong(json.get("issueChannelId").toString());
                long tmypeId = null == json.get("teachMaterialTypeId") ? -1 : Long.parseLong(json.get("teachMaterialTypeId").toString());
                if(issueChannelId == icId && teachMaterialTypeId == tmypeId){
                    isAdd = true;
                    code = null == json.get("code") ? "" : json.get("code").toString();
                    break;
                }
            }
            if(!isAdd){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("issueChannelId", issueChannelId);
                jsonObject.put("teachMaterialTypeId", teachMaterialTypeId);
                jsonObject.put("courseCode", courseCode);
                jsonObject.put("tmId", tmId);
                jsonObject.put("count", count);
                jsonObject.put("code", code);
                alreadyAddJSONArray.add(jsonObject);
                //执行数据保存操作
                OncePurchaseOrder oncePurchaseOrder = new OncePurchaseOrder();
                oncePurchaseOrder.setCode(code);
                oncePurchaseOrder.setIssueChannelId(issueChannelId);
                oncePurchaseOrder.setTeachMaterialTypeId(teachMaterialTypeId);
                oncePurchaseOrder.setState(OncePurchaseOrder.STATE_NO);
                oncePurchaseOrder.setSemesterId(semester.getId());
                oncePurchaseOrder.setCreator(loginName);
                oncePurchaseOrder.setOperator(loginName);
                super.save(oncePurchaseOrder);
            }
            //添加采购单关联教材
            this.savePurchaseOrderTeachMaterial(semester.getId(), code, courseCode, tmId, count, loginName);
        }
        //记录已确认的订单状态改为处理中的日子
        onceOrderLogDAO.addForStateToDoing(loginName, semester.getId());
        placeOrderLogDAO.addForStateToDoing(loginName, semester.getId());
        //修改已确认的订单状态修改为处理中
        editOnceOrderForStateDoingDAO.editor();
        editPlaceOrderForStateDoingDAO.editor();
    }

    /**
     * 生成采购单关联教材
     * 2018-07-05 邸老师要求在数量上都乘系数1.6
     * @param
     * @return
     */
    protected void savePurchaseOrderTeachMaterial(Long semesterId, String code, String courseCode, Long tmId, int count, String loginName){
        OncePurchaseOrderTM oncePurchaseOrderTM = new OncePurchaseOrderTM();
        oncePurchaseOrderTM.setSemesterId(semesterId);
        oncePurchaseOrderTM.setCode(code);
        oncePurchaseOrderTM.setCourseCode(courseCode);
        oncePurchaseOrderTM.setTeachMaterialId(tmId);
        oncePurchaseOrderTM.setTeachMaterialCount((int)Math.ceil(count*1.6));
        oncePurchaseOrderTM.setPutStorageCount(0);
        oncePurchaseOrderTM.setState(OncePurchaseOrderTM.STATE_NORMAL);
        oncePurchaseOrderTM.setCreator(loginName);
        oncePurchaseOrderTM.setOperator(loginName);
        oncePurchaseOrderTMDAO.save(oncePurchaseOrderTM);
    }
}
