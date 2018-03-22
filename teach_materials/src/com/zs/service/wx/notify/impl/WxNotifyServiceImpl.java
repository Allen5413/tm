package com.zs.service.wx.notify.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderForMaxCodeDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;
import com.zs.dao.sale.studentbookorderlog.StudentBookOrderLogDAO;
import com.zs.dao.sync.spotwx.FindSpotWxByCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.dao.wx.paylog.FindWxPayLogByCodeDAO;
import com.zs.dao.wx.notifylog.FindNotifyByOutTradeNoDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOnceOrderLog;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderLog;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.domain.wx.WxNotifyLog;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.ebook.studentebookpay.AddStudentEbookPayService;
import com.zs.service.ebook.studentebookpay.EditStudentEbookPayForQsService;
import com.zs.service.finance.studentexpense.FindStudentExpenseByCodeService;
import com.zs.service.finance.studentexpensepay.AddStudentExpensePayService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.service.wx.notify.WxNotifyService;
import com.zs.tools.DateTools;
import com.zs.tools.HttpRequestTools;
import com.zs.tools.OrderCodeTools;
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
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/10/28.
 */
@Service("wxNotifyService")
public class WxNotifyServiceImpl extends EntityServiceImpl<WxNotifyLog, FindNotifyByOutTradeNoDAO> implements WxNotifyService {

    @Resource
    private AddStudentExpensePayService addStudentExpensePayService;
    @Resource
    private FindWxPayLogByCodeDAO findWxPayLogByCodeDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindSpotWxByCodeDAO findSpotWxByCodeDAO;
    @Resource
    private FindStudentExpenseByCodeService findStudentExpenseByCodeService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;
    @Resource
    private StudentBookOrderLogDAO studentBookOrderLogDAO;
    @Resource
    private FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO findStudentBookOnceOrderByStudentCodeForUnConfirmDAO;
    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;
    @Resource
    private FindStudentBookOnceOrderForMaxCodeDAO findStudentBookOnceOrderForMaxCodeDAO;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private EditStudentEbookPayForQsService editStudentEbookPayForQsService;
    @Resource
    private AddStudentEbookPayService addStudentEbookPayService;

    @Override
    @Transactional
    public synchronized void notify(Map<String,String> params, int isPC) throws Exception {
        String orderCode = params.get("out_trade_no");
        String returnCode = params.get("return_code");

        WxNotifyLog wxNotifyLog = new WxNotifyLog();
        wxNotifyLog.setAppid(params.get("appid"));
        wxNotifyLog.setTradeType(params.get("trade_type"));
        wxNotifyLog.setReturnMsg(params.get("return_msg"));
        wxNotifyLog.setBankType(params.get("bank_type"));
        wxNotifyLog.setCashFee(Integer.parseInt(params.get("cash_fee")));
        wxNotifyLog.setFeeType(params.get("fee_type"));
        wxNotifyLog.setIsSubscribe(params.get("is_subscribe"));
        wxNotifyLog.setMchId(params.get("mch_id"));
        wxNotifyLog.setNonceStr(params.get("nonce_str"));
        wxNotifyLog.setOpenId(params.get("openid"));
        wxNotifyLog.setOutTradeNo(orderCode);
        wxNotifyLog.setResultCode(params.get("result_code"));
        wxNotifyLog.setReturnCode(returnCode);
        wxNotifyLog.setSign(params.get("sign"));
        wxNotifyLog.setTimeEnd(params.get("time_end"));
        wxNotifyLog.setTotalFee(Integer.parseInt(params.get("total_fee")));
        wxNotifyLog.setTradeType(params.get("trade_type"));
        wxNotifyLog.setTransactionId(params.get("transaction_id"));
        super.save(wxNotifyLog);

        //查询支付订单
        WxPayLog wxPayLog = findWxPayLogByCodeDAO.find(wxNotifyLog.getOutTradeNo());
        if(null != wxPayLog) {
            //如果是未通知
            if(wxPayLog.getState() == WxPayLog.STATE_NOT_NOTIFY){
                //成功，记录学生交费记录
                if("SUCCESS".equals(returnCode) && !StringUtils.isEmpty(wxPayLog.getStudentCode())){
                    this.addPay(wxNotifyLog.getTotalFee(), wxPayLog.getStudentCode());
                    this.notufySpot(wxPayLog.getStudentCode(), wxNotifyLog.getTotalFee());
                }
                //修改状态为已通知
                wxPayLog.setState(WxPayLog.STATE_YES_NOTIFY);
                findWxPayLogByCodeDAO.update(wxPayLog);
            }

            //如果是通过pc端支付的，需要把该学生当前未确认的订单修改成已确认，并且标记该学生以后产生的订单都自动确认
            //2017-08-30，微信公众号也修改为PC端一样的逻辑
            if(1 == isPC){
                Semester semester = findNowSemesterDAO.getNowSemester();
                Student student = findStudentByCodeDAO.getStudentByCode(wxPayLog.getStudentCode());
                if(null != student) {
                    student.setIsForeverSnedTm(Student.IS_FOREVER_SNEDTM_YES);
                    findStudentByCodeDAO.update(student);
                    //学生订单
                    List<StudentBookOrder> orderList = findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO.find(wxPayLog.getStudentCode(), semester.getId());
                    if (null != orderList) {
                        for (StudentBookOrder studentBookOrder : orderList) {
                            studentBookOrder.setState(StudentBookOrder.STATE_CONFIRMED);
                            studentBookOrder.setOperator(student.getName());
                            studentBookOrder.setOperateTime(DateTools.getLongNowTime());
                            findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO.update(studentBookOrder);
                            //记录订单状态变更日志
                            StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
                            studentBookOrderLog.setOrderCode(studentBookOrder.getOrderCode());
                            studentBookOrderLog.setState(studentBookOrder.getState());
                            studentBookOrderLog.setOperator(student.getName());
                            studentBookOrderLogDAO.save(studentBookOrderLog);
                        }
                    }
                    //学生一次性订单
                    List<StudentBookOnceOrder> onceOrderList = findStudentBookOnceOrderByStudentCodeForUnConfirmDAO.find(wxPayLog.getStudentCode());
                    if (null != onceOrderList) {
                        //得到当前学期最大的订单号
                        int num = 0;
                        StudentBookOnceOrder maxCodeOrder = findStudentBookOnceOrderForMaxCodeDAO.find(semester.getId());
                        if(null != maxCodeOrder){
                            String maxOrderCode = maxCodeOrder.getOrderCode();
                            num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
                        }
                        for (StudentBookOnceOrder studentBookOnceOrder : onceOrderList) {
                            //生成学生订单号
                            String onceOrderCode = OrderCodeTools.createStudentOnceOrderCodeForConfirm(semester.getYear(), semester.getQuarter(), num + 1);

                            studentBookOnceOrder.setOrderCode(onceOrderCode);
                            studentBookOnceOrder.setState(StudentBookOnceOrder.STATE_CONFIRMED);
                            studentBookOnceOrder.setOperator(student.getName());
                            studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
                            findStudentBookOnceOrderByStudentCodeForUnConfirmDAO.update(studentBookOnceOrder);
                            //记录订单状态变更日志
                            StudentBookOnceOrderLog studentBookOnceOrderLog = new StudentBookOnceOrderLog();
                            studentBookOnceOrderLog.setOrderId(studentBookOnceOrder.getId());
                            studentBookOnceOrderLog.setState(studentBookOnceOrder.getState());
                            studentBookOnceOrderLog.setOperator(student.getName());
                            onceOrderLogDAO.save(studentBookOnceOrderLog);
                            num++;
                        }
                    }
                }
            }
        }
    }

    /**
     * 电子教材通知
     * @param params
     * @throws Exception
     */
    @Override
    public void ebookNotify(Map<String, String> params) throws Exception {
        String orderCode = params.get("out_trade_no");
        String returnCode = params.get("return_code");

        WxNotifyLog wxNotifyLog = new WxNotifyLog();
        wxNotifyLog.setAppid(params.get("appid"));
        wxNotifyLog.setTradeType(params.get("trade_type"));
        wxNotifyLog.setReturnMsg(params.get("return_msg"));
        wxNotifyLog.setBankType(params.get("bank_type"));
        wxNotifyLog.setCashFee(Integer.parseInt(params.get("cash_fee")));
        wxNotifyLog.setFeeType(params.get("fee_type"));
        wxNotifyLog.setIsSubscribe(params.get("is_subscribe"));
        wxNotifyLog.setMchId(params.get("mch_id"));
        wxNotifyLog.setNonceStr(params.get("nonce_str"));
        wxNotifyLog.setOpenId(params.get("openid"));
        wxNotifyLog.setOutTradeNo(orderCode);
        wxNotifyLog.setResultCode(params.get("result_code"));
        wxNotifyLog.setReturnCode(returnCode);
        wxNotifyLog.setSign(params.get("sign"));
        wxNotifyLog.setTimeEnd(params.get("time_end"));
        wxNotifyLog.setTotalFee(Integer.parseInt(params.get("total_fee")));
        wxNotifyLog.setTradeType(params.get("trade_type"));
        wxNotifyLog.setTransactionId(params.get("transaction_id"));
        super.save(wxNotifyLog);

        //查询支付订单
        WxPayLog wxPayLog = findWxPayLogByCodeDAO.find(wxNotifyLog.getOutTradeNo());
        if(null != wxPayLog) {
            //如果是未通知
            if(wxPayLog.getState() == WxPayLog.STATE_NOT_NOTIFY){
                //修改状态为已通知
                wxPayLog.setState(WxPayLog.STATE_YES_NOTIFY);
                findWxPayLogByCodeDAO.update(wxPayLog);
            }

            //记录学生的电子教材交费数据，并推送消息给青书
            Student student = findStudentByCodeService.getStudentByCode(wxPayLog.getStudentCode());
            if(null != student) {
                this.addEBookPay(student.getCode(), student.getName(), wxNotifyLog.getTotalFee());
                Spot spot = findSpotByCodeService.getSpotByCode(student.getSpotCode());
                String buyerPayAmount = new BigDecimal(wxNotifyLog.getTotalFee()+"").divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                JSONObject resultJSON = HttpRequestTools.reqQingShu(student.getCode(), student.getName(), spot.getName(), student.getIdcardType(),
                        student.getIdcardNo(), student.getMobile(), buyerPayAmount);
                if(null != resultJSON) {
                    JSONObject data = StringUtils.isEmpty(resultJSON.get("Data").toString()) || "null".equals(resultJSON.get("Data").toString().toLowerCase()) ? null : (JSONObject) resultJSON.get("Data");
                    editStudentEbookPayForQsService.edit(student.getCode(),
                            null == resultJSON.get("HR") ? "" : resultJSON.get("HR").toString(),
                            null == resultJSON.get("ErrorMessage") ? "" : resultJSON.get("ErrorMessage").toString(),
                            null == resultJSON.get("Remark") ? "" : resultJSON.get("Remark").toString(),
                            null == data || null == data.get("QingShuOrderNum") ? "" : data.get("QingShuOrderNum").toString());
                }
            }
        }
    }

    protected void addPay(int money, String studentCode)throws Exception{
        float pay = new BigDecimal(money+"").divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        StudentExpensePay studentExpensePay = new StudentExpensePay();
        studentExpensePay.setStudentCode(studentCode);
        studentExpensePay.setMoney(pay);
        studentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_STUDENT);
        studentExpensePay.setPayType(StudentExpensePay.PAY_TYPE_WX);
        studentExpensePay.setArrivalTime(DateTools.getLongNowTime());
        addStudentExpensePayService.addStudentExpensePay(studentExpensePay, studentCode, studentCode);
    }

    protected void addEBookPay(String studentCode, String studentName, long money)throws Exception{
        StudentEBookPay studentEBookPay = new StudentEBookPay();
        studentEBookPay.setStudentCode(studentCode);
        studentEBookPay.setPayType(StudentEBookPay.PAY_TYPE_WX);
        studentEBookPay.setArrivalTime(DateTools.getShortNowTime());
        studentEBookPay.setMoney(money);
        studentEBookPay.setCreator(studentName);
        addStudentEbookPayService.add(studentEBookPay);
    }

    protected void notufySpot(String studentCode, int money)throws Exception{
        Student student = findStudentByCodeDAO.getStudentByCode(studentCode);
        if(null != student){
            List<SpotWx> spotWxList = findSpotWxByCodeDAO.find(student.getSpotCode());
            if(null != spotWxList){
                for(SpotWx spotWx : spotWxList){
                    if(!StringUtils.isEmpty(spotWx.getOpenId())){
                        this.sendBuyForWXToSpot(studentCode, spotWx.getAdminName(), spotWx.getOpenId(), new BigDecimal(money).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP), student.getName());
                    }
                }
            }
        }
    }

    public void sendBuyForWXToSpot(String studentCode, String name, String openId, BigDecimal money, String stuName)throws Exception {
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


        //把结果发送给用户
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(openId);
        //正式id
        templateMessage.setTemplateId("z2hstS4E6QUS4QzoA9x_4FbWaFC8g6Ep_M85l08D4oc");
        //测试id
        //templateMessage.setTemplateId("SEVDpUQVJzLn9B06O0OHOXyyi3pGQnk2ZL8tKFqqQ8c");
        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + name + "您好，您中心的学生["+studentCode+stuName+"]的刚刚缴费"+money+", 他的当前账户信息如下"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", json.get("sumPay").toString(), "#173177"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", json.get("sumBuy").toString(), "#173177"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", json.get("sumAcc").toString(), "#173177"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword4", json.get("sumOwn").toString(), "#173177"));
        templateMessage.getDatas().add(new WxMpTemplateData("remark", "以上信息截止" + DateTools.getLongNowTime().toString().substring(0, 19)));
        templateMessage.setUrl("http://xiwang.attop.com/wxSearch/finance.htm?code=" + studentCode+"&isSpotUser=1");
        wxMpService.templateSend(templateMessage);

    }
}
