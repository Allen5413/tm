package com.zs.service.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.sale.onceorder.FindOnceOrderByPackageIdDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByPackageIdDAO;
import com.zs.dao.sale.studentbookorderpackage.StudentBookOrderPackageDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.domain.sale.*;
import com.zs.domain.sync.Student;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.finance.studentexpense.FindStudentExpenseByCodeService;
import com.zs.service.finance.studentexpensebuy.AddStudentExpenseBuyService;
import com.zs.service.sale.onceorder.EditOnceOrderForStateService;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStateService;
import com.zs.service.sale.studentbookorderpackage.SendStudentBookOrderPackageService;
import com.zs.tools.DateTools;
import com.zs.tools.HttpRequestTools;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.WxMpTemplateData;
import com.zs.weixin.mp.bean.WxMpTemplateMessage;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 录入教材包的物流单号，发货过程
 * Created by Allen on 2015/7/27.
 */
@Service("sendStudentBookOrderPackageService")
public class SendStudentBookOrderPackageServiceImpl extends EntityServiceImpl<StudentBookOrderPackage, StudentBookOrderPackageDAO> implements SendStudentBookOrderPackageService {

    @Resource
    private FindStudentBookOrderByPackageIdDAO findStudentBookOrderByPackageIdDAO;
    @Resource
    private FindOnceOrderByPackageIdDAO findOnceOrderByPackageIdDAO;
    @Resource
    private EditStudentBookOrderForStateService editStudentBookOrderForStateService;
    @Resource
    private EditOnceOrderForStateService editOnceOrderForStateService;
    @Resource
    private AddStudentExpenseBuyService addStudentExpenseBuyService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindStudentBookOrderTMByOrderCodeDAO findStudentBookOrderTMByOrderCodeDAO;
    @Resource
    private FindOnceOrderTMByOrderIdDAO findOnceOrderTMByOrderIdDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private FindStudentExpenseByCodeService findStudentExpenseByCodeService;

    @Override
    @Transactional
    public void sendStudentBookOrderPackage(Long[] ids, String[] logisticCodes, String loginName) throws Exception {
        try {
            if (null == ids) {
                throw new BusinessException("没有传入教材包id");
            }
            if (null == logisticCodes) {
                throw new BusinessException("没有传入物流单号");
            }
            if (ids.length != logisticCodes.length) {
                throw new BusinessException("传入教材包对应物流单号不一致");
            }
            //修改教材包
            Timestamp operateTime = DateTools.getLongNowTime();
            for (int i = 0; i < ids.length; i++) {
                long id = ids[i];
                String logisticCode = logisticCodes[i];
                //查询教材包
                StudentBookOrderPackage studentBookOrderPackage = super.get(id);
                //修改教材包订单
                if (null != studentBookOrderPackage) {
                    studentBookOrderPackage.setLogisticCode(logisticCode);
                    studentBookOrderPackage.setSendTime(operateTime);
                    studentBookOrderPackage.setOperator(loginName);
                    studentBookOrderPackage.setOperateTime(operateTime);
                    super.update(studentBookOrderPackage);
                }
                //学生订单
                if(studentBookOrderPackage.getIsOnce() == StudentBookOrderPackage.IS_ONCE_NOT){
                    this.editStudentOrderState(id, operateTime, loginName);
                }
                //一次性订单
                if(studentBookOrderPackage.getIsOnce() == StudentBookOrderPackage.IS_ONCE_YES){
                    this.editOnceOrderState(id, operateTime, loginName);
                }
                //发起快递100的请求
                if(logisticCode.indexOf(",") >= 0){
                    for(String logisticCode2 : logisticCode.split(",")){
                        HttpRequestTools.reqKuaidi(logisticCode2);
                    }
                }else {
                    HttpRequestTools.reqKuaidi(logisticCode);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    protected void editStudentOrderState(long pId, Timestamp operateTime, String loginName)throws Exception{
        //获取该教材包下的订单
        List<StudentBookOrder> studentBookOrderList = findStudentBookOrderByPackageIdDAO.findStudentBookOrderByPackageId(pId);
        if (null != studentBookOrderList) {
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            //修改订单状态，记录订单日志
            for (StudentBookOrder studentBookOrder : studentBookOrderList) {
                //查询学生信息
                Student student = findStudentByCodeDAO.getStudentByCode(studentBookOrder.getStudentCode());

                editStudentBookOrderForStateService.editStudentBookOrderForState(studentBookOrder.getOrderCode(), null, StudentBookOrder.STATE_SEND, operateTime, loginName);
                //查询订单下的明细
                List<StudentBookOrderTM> studentBookOrderTMList = findStudentBookOrderTMByOrderCodeDAO.findStudentBookOrderTMByOrderCode(studentBookOrder.getOrderCode());
                BigDecimal money = new BigDecimal(0);
                if (null != studentBookOrderTMList && 0 < studentBookOrderTMList.size()) {
                    for (StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList) {
                        //查询教材信息
                        TeachMaterial teachMaterial = findTeachMaterialService.get(studentBookOrderTM.getTeachMaterialId());
                        if (null != teachMaterial && 0 < studentBookOrderTM.getCount()) {
                            money = money.add(new BigDecimal(studentBookOrderTM.getCount()).multiply(new BigDecimal(studentBookOrderTM.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                            //记录学生消费
                            StudentExpenseBuy studentExpenseBuy = new StudentExpenseBuy();
                            studentExpenseBuy.setStudentCode(studentBookOrder.getStudentCode());
                            studentExpenseBuy.setSemester(semester);
                            studentExpenseBuy.setType(StudentExpenseBuy.TYPE_BUY_TM);
                            studentExpenseBuy.setDetail("购买了" + studentBookOrderTM.getCount() + "本，[" + teachMaterial.getName() + "] 教材");
                            studentExpenseBuy.setMoney(new BigDecimal(studentBookOrderTM.getCount()).multiply(new BigDecimal(studentBookOrderTM.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                            studentExpenseBuy.setTeachMaterialId(teachMaterial.getId());
                            addStudentExpenseBuyService.addStudentExpenseBuy(studentExpenseBuy, loginName);
                            //减去教材库存
                            if (null != student) {
                                IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(student.getSpotCode());
                                if (null != issueRange) {
                                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
                                    if (null != teachMaterialStock) {
                                        teachMaterialStock.setStock(teachMaterialStock.getStock() - studentBookOrderTM.getCount());
                                        findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
                                    }
                                }
                            }
                            //记录该订单明细为已发出
                            studentBookOrderTM.setIsSend(StudentBookOrderTM.IS_SEND_YES);
                            findStudentBookOrderTMByOrderCodeDAO.update(studentBookOrderTM);
                        }
                    }
                }
                if(!StringUtils.isEmpty(student.getOpenId())) {
                    this.sendBuyForWX(student.getCode(), student.getName(), studentBookOrder.getOrderCode(), student.getOpenId(), money);
                }
            }
        }
    }

    protected void editOnceOrderState(long pId, Timestamp operateTime, String loginName)throws Exception{
        //获取该教材包下的订单
        List<StudentBookOnceOrder> studentBookOnceOrderList = findOnceOrderByPackageIdDAO.find(pId);
        if (null != studentBookOnceOrderList) {
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            //修改订单状态，记录订单日志
            for (StudentBookOnceOrder studentBookOnceOrder : studentBookOnceOrderList) {
                //查询学生信息
                Student student = findStudentByCodeDAO.getStudentByCode(studentBookOnceOrder.getStudentCode());

                editOnceOrderForStateService.editor(studentBookOnceOrder.getOrderCode(), null, StudentBookOnceOrder.STATE_SEND, operateTime, loginName);
                //查询订单下的明细
                List<StudentBookOnceOrderTM> studentBookOnceOrderTMList = findOnceOrderTMByOrderIdDAO.find(studentBookOnceOrder.getId());
                BigDecimal money = new BigDecimal(0);
                if (null != studentBookOnceOrderTMList && 0 < studentBookOnceOrderTMList.size()) {
                    for (StudentBookOnceOrderTM studentBookOnceOrderTM : studentBookOnceOrderTMList) {
                        //查询教材信息
                        TeachMaterial teachMaterial = findTeachMaterialService.get(studentBookOnceOrderTM.getTeachMaterialId());
                        if (null != teachMaterial && 0 < studentBookOnceOrderTM.getCount()) {
                            money = money.add(new BigDecimal(studentBookOnceOrderTM.getCount()).multiply(new BigDecimal(studentBookOnceOrderTM.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                            //记录学生消费
                            StudentExpenseBuy studentExpenseBuy = new StudentExpenseBuy();
                            studentExpenseBuy.setStudentCode(studentBookOnceOrder.getStudentCode());
                            studentExpenseBuy.setSemester(semester);
                            studentExpenseBuy.setType(StudentExpenseBuy.TYPE_BUY_TM);
                            studentExpenseBuy.setDetail("购买了" + studentBookOnceOrderTM.getCount() + "本，[" + teachMaterial.getName() + "] 教材");
                            studentExpenseBuy.setMoney(new BigDecimal(studentBookOnceOrderTM.getCount()).multiply(new BigDecimal(studentBookOnceOrderTM.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                            studentExpenseBuy.setTeachMaterialId(teachMaterial.getId());
                            addStudentExpenseBuyService.addStudentExpenseBuy(studentExpenseBuy, loginName);
                            //减去教材库存
                            if (null != student) {
                                IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(student.getSpotCode());
                                if (null != issueRange) {
                                    TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
                                    if (null != teachMaterialStock) {
                                        teachMaterialStock.setStock(teachMaterialStock.getStock() - studentBookOnceOrderTM.getCount());
                                        findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
                                    }
                                }
                            }
                            //记录该订单明细为已发出
                            studentBookOnceOrderTM.setIsSend(StudentBookOnceOrderTM.IS_SEND_YES);
                            findOnceOrderTMByOrderIdDAO.update(studentBookOnceOrderTM);
                        }
                    }
                }
                if(!StringUtils.isEmpty(student.getOpenId())) {
                    this.sendBuyForWX(student.getCode(), student.getName(), studentBookOnceOrder.getOrderCode(), student.getOpenId(), money);
                }
            }
        }
    }

    public void sendBuyForWX(String studentCode, String name, String orderCode, String openId, BigDecimal money)throws Exception {
        //查询用户余额
        JSONObject json = findStudentExpenseByCodeService.find(studentCode);

        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        WxMpService wxMpService;
        /**
         * 测试账号
        config.setAppId("wx9670fc1f7d7fc941"); // 设置微信公众号的appid
        config.setSecret("2c4698cb1c075da218691476f0e5f482"); // 设置微信公众号的app corpSecret
        config.setOauth2redirectUri("http://allen.ittun.com/wxSearch/openPay.htm");*/

        /**
         * 正式账号*/
        config.setAppId("wx79ba7069388a101a"); // 设置微信公众号的appid
        config.setSecret("1bc0d069914b1f904168fe57c0e65102"); // 设置微信公众号的app corpSecret
        config.setOauth2redirectUri("http://xiwang.attop.com/wxSearch/openPay.htm");

        config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
        config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);

        //把绑定结果发送给用户
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(openId);
        templateMessage.setTemplateId("fLXJdZgeSrOKu7NRy8AS5DXXGI7ckHJpJ8OFVKC9UoY ");
        templateMessage.setUrl("http://xiwang.attop.com/wxSearch/order.htm?code=" + studentCode);
        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + name + "您好，您的订单：" + orderCode + "已发货，并扣除您的教材费如下："));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", DateTools.getLongNowTime().toString()));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", money+""));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", "0".equals(json.get("sumAcc").toString()) ?
                "0".equals(json.get("sumOwn").toString()) ? "0" : "-"+json.get("sumOwn").toString() : json.get("sumAcc").toString()));

        //wxMpService.templateSend(templateMessage);

    }
}
