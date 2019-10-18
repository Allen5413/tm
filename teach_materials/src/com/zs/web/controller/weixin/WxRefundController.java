package com.zs.web.controller.weixin;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.ebook.studentebookpay.FindStudentEBookPayByCodeDAO;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.domain.wx.WxPayLog;
import com.zs.domain.wx.WxRefundLog;
import com.zs.service.api.GetStudentFinanceService;
import com.zs.service.finance.studentexpense.FindStudentExpenseByCodeService;
import com.zs.service.finance.studentexpensepay.RefundWxStudentExpensePayService;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.service.wx.notify.WxNotifyService;
import com.zs.service.wx.paylog.AddWxPayLogService;
import com.zs.service.wx.paylog.FindWxPayLogByStudentCodeService;
import com.zs.service.wx.paylog.FindWxPayLogForMaxCodeService;
import com.zs.service.wx.refundlog.AddWxRefundLogService;
import com.zs.service.wx.refundlog.FindWxRefundLogForMaxCodeService;
import com.zs.tools.HttpRequestTools;
import com.zs.tools.QRCodeTools;
import com.zs.tools.StringTools;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.common.util.crypto.WxCryptUtil;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import com.zs.weixin.mp.bean.result.WxMpPrepayIdResult;
import com.zs.weixin.mp.bean.result.WxMpRefundResult;
import freemarker.template.utility.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/7/5.
 */
@Controller
@RequestMapping(value = "/wxRefund")
public class WxRefundController extends LoggerController {
    private static Logger log = Logger.getLogger(WxSearchController.class);

    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;
    @Resource
    private FindSpotWxByOpenIdService findSpotWxByOpenIdService;
    @Resource
    private FindWxPayLogForMaxCodeService findWxPayLogForMaxCodeService;
    @Resource
    private AddWxPayLogService addWxPayLogService;
    @Resource
    private WxNotifyService wxNotifyService;
    @Resource
    private GetStudentFinanceService getStudentFinanceService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindStudentEBookPayByCodeDAO findStudentEBookPayByCodeDAO;
    @Resource
    private FindWxPayLogByStudentCodeService findWxPayLogByStudentCodeService;
    @Resource
    private FindStudentExpenseByCodeService findStudentExpenseByCodeService;
    @Resource
    private FindWxRefundLogForMaxCodeService findWxRefundLogForMaxCodeService;
    @Resource
    private AddWxRefundLogService addWxRefundLogService;
    @Resource
    private RefundWxStudentExpensePayService refundWxStudentExpensePayService;

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;


    /**
     * 退款，正式用
     * @param request
     */
    @RequestMapping(value = "refund")
    @ResponseBody
    public JSONObject refund(HttpServletRequest request, @RequestParam("code")String code, @RequestParam("money")double money){
        JSONObject jsonObject = new JSONObject();
        try{
            config = new WxMpInMemoryConfigStorage();
            /**
             * 测试账号
             config.setAppId("wx9670fc1f7d7fc941"); // 设置微信公众号的appid
             config.setSecret("2c4698cb1c075da218691476f0e5f482"); // 设置微信公众号的app corpSecret
             config.setOauth2redirectUri("http://xiwang.attop.com/wxSearch/openPay.htm");*/

            /**
             * 正式账号*/
            config.setAppId("wx79ba7069388a101a"); // 设置微信公众号的appid
            config.setSecret("1bc0d069914b1f904168fe57c0e65102"); // 设置微信公众号的app corpSecret
            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            config.setPartnerId("1356478102");
            config.setPartnerKey("xiWangWeiXinPay20160705161520LQS");
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);

            //获取时间戳
            String timeStamp = System.currentTimeMillis()+"";
            //查询学生当前余额
            JSONObject json = findStudentExpenseByCodeService.find(code);
            if(Double.parseDouble(json.get("sumAcc").toString()) <= 0){
                throw new BusinessException("该学生没有余额，不能退款");
            }
            if(Double.parseDouble(json.get("sumAcc").toString()) < money){
                throw new BusinessException("该学生余额不够，不能退款");
            }
            //查询学生的微信支付订单号
            List<WxPayLog> wxPayLogList = findWxPayLogByStudentCodeService.find(code);
            if(null == wxPayLogList || 1 > wxPayLogList.size()){
                throw new BusinessException("该学生没有微信支付，不能微信退款");
            }
            //这里防止退款金额大于微信缴费单笔金额，微信是不能退的
            String orderCode = "";
            boolean isRefund = false;
            int totalFee = 0;
            for (WxPayLog wxPayLog : wxPayLogList){
                if(wxPayLog.getMoney() >= (int) (money * 100)){
                    isRefund = true;
                    orderCode = wxPayLog.getOrderCode();
                    totalFee = wxPayLog.getMoney();
                    break;
                }
            }
            if(!isRefund){
                throw new BusinessException("该学生退款金额大于微信支付金额");
            }

            //生成退订单号
            String refundCode = timeStamp + findWxRefundLogForMaxCodeService.find(timeStamp);
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("out_trade_no", orderCode);
            parameters.put("out_refund_no", refundCode);
            parameters.put("total_fee", totalFee+"");
            parameters.put("refund_fee", ((int) (money * 100))+"");
            parameters.put("notify_url", "http://xiwang.attop.com/wxRefund/notify.htm");
            WxMpRefundResult wxMpRefundResult = wxMpService.getRefundId(parameters);
            if(null != wxMpRefundResult){
                WxRefundLog wxRefundLog = new WxRefundLog();
                wxRefundLog.setStudentCode(code);
                wxRefundLog.setOrderCode(refundCode);
                wxRefundLog.setPayOrderCode(orderCode);
                wxRefundLog.setMoney((int) (money * 100));
                wxRefundLog.setReturnCode(wxMpRefundResult.getReturn_code());
                wxRefundLog.setReturnMsg(wxMpRefundResult.getReturn_msg());
                wxRefundLog.setResultCode(wxMpRefundResult.getResult_code());
                wxRefundLog.setErrCodeDes(wxMpRefundResult.getErr_code_des());
                addWxRefundLogService.save(wxRefundLog);
            }
            if(!StringUtils.isEmpty(wxMpRefundResult.getReturn_code()) && "FAIL".equals(wxMpRefundResult.getReturn_code())){
                throw new BusinessException(wxMpRefundResult.getReturn_msg());
            }
            if(!StringUtils.isEmpty(wxMpRefundResult.getErr_code_des())){
                throw new BusinessException(wxMpRefundResult.getErr_code_des());
            }

            if("SUCCESS".equals(wxMpRefundResult.getReturn_code()) && "OK".equals(wxMpRefundResult.getReturn_msg()) && "SUCCESS".equals(wxMpRefundResult.getResult_code())){
                //添加学生的交费信息
                refundWxStudentExpensePayService.refund(code, money, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "微信退款");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 退款回调通知，正式用
     * @param request
     */
    @RequestMapping(value = "notify")
    @ResponseBody
    public String notify(HttpServletRequest request){
        System.out.println("-----------------------------------  notify ---------------------------------------");
        InputStream is = null;
        try {
            // 解析结果存储在HashMap
            Map<String, String> map = new HashMap<String, String>();
            is = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
                System.out.println(e.getName()+":     "+e.getText());
            }
            //wxNotifyService.notify(map, 1);
        } catch (Exception er) {
            er.printStackTrace();
        }finally {
            // 释放资源
            if(null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                is = null;
            }
        }
        String returnStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        return returnStr;
    }
}
