package com.zs.web.controller.weixin;

import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Student;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.finance.studentexpensebuy.FindStudentExpenseBuyByCodeService;
import com.zs.service.finance.studentexpensepay.FindStudentExpensePayByCodeService;
import com.zs.service.kuaidi.push.FindKuaidiPushByNuService;
import com.zs.service.sale.onceordertm.FindOnceOrderTMListByOrderCodeService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForWXByCodeService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderListByOrderCodeService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.service.wx.notify.WxNotifyService;
import com.zs.service.wx.paylog.AddWxPayLogService;
import com.zs.service.wx.paylog.FindWxPayLogForMaxCodeService;
import com.zs.tools.HttpRequestTools;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.common.util.crypto.WxCryptUtil;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import com.zs.weixin.mp.bean.result.WxMpPrepayIdResult;
import net.sf.json.JSONArray;
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
 * 从微信端跳转过来查询的url请求
 * Created by Allen on 2016/5/26.
 */
@Controller
@RequestMapping(value = "/wxSearch")
public class WxSearchController extends LoggerController {
    private static Logger log = Logger.getLogger(WxSearchController.class);

    @Resource
    private FindStudentExpensePayByCodeService findStudentExpensePayByCodeService;
    @Resource
    private FindStudentExpenseBuyByCodeService findStudentExpenseBuyByCodeService;
    @Resource
    private FindStudentBookOrderForWXByCodeService findStudentBookOrderForWXByCodeService;
    @Resource
    private FindStudentBookOrderListByOrderCodeService findStudentBookOrderListByOrderCodeService;
    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private FindOnceOrderTMListByOrderCodeService findOnceOrderTMListByOrderCodeService;
    @Resource
    private FindKuaidiPushByNuService findKuaidiPushByNuService;
    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;
    @Resource
    private FindWxPayLogForMaxCodeService findWxPayLogForMaxCodeService;
    @Resource
    private AddWxPayLogService addWxPayLogService;
    @Resource
    private WxNotifyService wxNotifyService;

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;

    /**
     * 查询学生账户明细
     * @param request
     */
    @RequestMapping(value = "finance")
    public String finance(HttpServletRequest request,
                          @RequestParam("code") String code,
                          @RequestParam("isSpotUser") int isSpotUser){
        try {
            //获取学生的入账记录
            List<StudentExpensePay> payList = findStudentExpensePayByCodeService.getStudentExpensePayByCode(code);
            //获取学生的消费记录
            Map<String, Map<Double, List<StudentExpenseBuy>>> buyMap = findStudentExpenseBuyByCodeService.getStudentExpenseBuyByCode(code);

            request.setAttribute("payList", payList);
            request.setAttribute("buyMap", buyMap);

            //学生
            if(0 == isSpotUser){
                return "weixin/stuEPDetail";
            }
            //中心管理员
            if(1 == isSpotUser){
                //查询该学生未确认的订单
                List<JSONObject> list = findStudentBookOrderForWXByCodeService.findNotConfirm(code);
                request.setAttribute("orderList", list);

                return "weixin/stuEPDetailForSpot";
            }

            return "";
        }catch (Exception e){
            super.outputException(request,e,log,"查询学生费用明细失败");
            return "error";
        }
    }

    /**
     * 查询学生所有有效订单
     * @param request
     */
    @RequestMapping(value = "order")
    public String order(HttpServletRequest request, @RequestParam("code") String code){
        try {
            //获取学生的入账记录
            List<JSONObject> list = findStudentBookOrderForWXByCodeService.findAll(code);

            request.setAttribute("orderList", list);

            return "weixin/orderList";
        }catch (Exception e){
            super.outputException(request,e,log,"查询学生订单失败");
            return "error";
        }
    }

    /**
     * 查询订单明细
     * @param request
     */
    @RequestMapping(value = "orderDetail")
    public String orderDetail(HttpServletRequest request,
                              @RequestParam("code") String code,
                              @RequestParam(value = "isSpotUser", defaultValue = "0", required = false) int isSpotUser){
        try {
            //查询订单明细
            JSONArray resultJson = findStudentBookOrderListByOrderCodeService.getStudentBookOrderListByOrderCode(code);
            if(null != resultJson && 0 < resultJson.size()){
                JSONObject jsonObject = (JSONObject)resultJson.get(0);
                if(null != jsonObject.get("packageId")) {
                    //查询订单大包信息
                    StudentBookOrderPackage studentBookOrderPackage = findStudentBookOrderPackagePageByWhereService.get(Long.parseLong(jsonObject.get("packageId").toString()));
                    if(!StringUtils.isEmpty(studentBookOrderPackage.getLogisticCode())){
                        String[] nus = studentBookOrderPackage.getLogisticCode().split(",");
                        if(null != nus && 0 < nus.length) {
                            //查询快递信息
                            Map<String, JSONArray> kuaidiMap = new HashMap<String, JSONArray>();
                            for(String nu : nus) {
                                JSONArray jsonArray = findKuaidiPushByNuService.find(nu);
                                kuaidiMap.put(nu, jsonArray);
                            }
                            request.setAttribute("kuaidiMap", kuaidiMap);
                        }
                    }
                    request.setAttribute("studentBookOrderPackage", studentBookOrderPackage);
                }
            }
            request.setAttribute("resultJson", resultJson);
            if(0 == isSpotUser){
                return "weixin/orderDetail";
            }
            if(1 == isSpotUser){
                return "weixin/orderDetailForSpot";
            }
            return "";
        }catch (Exception e){
            super.outputException(request,e,log,"查询学生订单明细失败");
            return "error";
        }
    }

    /**
     * 查询一次性订单明细
     * @param request
     */
    @RequestMapping(value = "onceOrderDetail")
    public String onceOrderDetail(HttpServletRequest request, @RequestParam("orderId") long orderId){
        try {
            //查询订单明细
            JSONArray resultJson = findOnceOrderTMListByOrderCodeService.find(orderId);
            if(null != resultJson && 0 < resultJson.size()){
                JSONObject jsonObject = (JSONObject)resultJson.get(0);
                if(null != jsonObject.get("packageId")) {
                    //查询订单大包信息
                    StudentBookOrderPackage studentBookOrderPackage = findStudentBookOrderPackagePageByWhereService.get(Long.parseLong(jsonObject.get("packageId").toString()));
                    if(!StringUtils.isEmpty(studentBookOrderPackage.getLogisticCode())){
                        String[] nus = studentBookOrderPackage.getLogisticCode().split(",");
                        if(null != nus && 0 < nus.length) {
                            //查询快递信息
                            Map<String, JSONArray> kuaidiMap = new HashMap<String, JSONArray>();
                            for(String nu : nus) {
                                JSONArray jsonArray = findKuaidiPushByNuService.find(nu);
                                kuaidiMap.put(nu, jsonArray);
                            }
                            request.setAttribute("kuaidiMap", kuaidiMap);
                        }
                    }
                    request.setAttribute("studentBookOrderPackage", studentBookOrderPackage);
                }
            }
            request.setAttribute("resultJson", resultJson);

            return "weixin/orderDetail";
        }catch (Exception e){
            super.outputException(request,e,log,"查询一次性订单明细失败");
            return "error";
        }
    }

    /**
     * 打开支付页面，测试用
     * @param request
     */
    @RequestMapping(value = "openPay")
    public String openPay(HttpServletRequest request, @RequestParam("code")String code){
        try {
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxSearch/openPay.htm");

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);


            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            Student student = findStudentByOpenIdService.find(openId);
            if(null != student){
                request.setAttribute("studentCode", student.getCode());
                request.setAttribute("name", student.getName());
                request.setAttribute("spec", SpecEnum.getDescn(student.getSpecCode()));
                request.setAttribute("level", LevelEnum.getDescn(student.getLevelCode()));
                request.setAttribute("openId", openId);
            }
            return "weixin/pay";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转支付页面失败");
            return "error";
        }
    }

    /**
     * 支付，测试用
     * @param request
     */
    @RequestMapping(value = "pay")
    @ResponseBody
    public JSONObject pay(HttpServletRequest request,
                      @RequestParam("code")String code,
                      @RequestParam("openId")String openId,
                      @RequestParam("money")double money){
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxSearch/openPay.htm");

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            config.setPartnerId("1356478102");
            config.setPartnerKey("xiWangWeiXinPay20160705161520LQS");
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);

            //获取时间戳
            String timeStamp = System.currentTimeMillis()+"";
            //生成订单号
            String orderCode = timeStamp + findWxPayLogForMaxCodeService.find(timeStamp);
            String body = "教材费用";
            String ip = HttpRequestTools.getIp(request);
            String tradeType = "JSAPI";
            WxMpPrepayIdResult wxMpPrepayIdResult = wxMpService.getPrepayId(openId, orderCode, money, body, tradeType, ip, "http://xiwang.attop.com/wxSearch/notify.htm");
            Map<String, String> params = new HashMap<String, String>();
            params.put("appId", config.getAppId());
            params.put("timeStamp", timeStamp);
            params.put("nonceStr", wxMpPrepayIdResult.getNonce_str());
            params.put("package", "prepay_id="+wxMpPrepayIdResult.getPrepay_id());
            params.put("signType", "MD5");
            String paySign = WxCryptUtil.createSign(params, config.getPartnerKey());

            jsonObject.put("state", 0);
            jsonObject.put("appId", config.getAppId());
            jsonObject.put("timeStamp", timeStamp);
            jsonObject.put("nonceStr", wxMpPrepayIdResult.getNonce_str());
            jsonObject.put("package", "prepay_id="+wxMpPrepayIdResult.getPrepay_id());
            jsonObject.put("paySign", paySign);
            jsonObject.put("returnMsg", wxMpPrepayIdResult.getReturn_msg());

            WxPayLog wxPayLog = new WxPayLog();
            wxPayLog.setStudentCode(code);
            wxPayLog.setOrderCode(orderCode);
            wxPayLog.setBody(body);
            wxPayLog.setIp(ip);
            wxPayLog.setMoney((int) (money * 100));
            wxPayLog.setNonceStr(wxMpPrepayIdResult.getNonce_str());
            wxPayLog.setOpenId(openId);
            wxPayLog.setPackages("prepay_id="+wxMpPrepayIdResult.getPrepay_id());
            wxPayLog.setPaySign(paySign);
            wxPayLog.setReturnMsg(wxMpPrepayIdResult.getReturn_msg());
            wxPayLog.setState(WxPayLog.STATE_NOT_NOTIFY);
            wxPayLog.setTimeStamp(timeStamp);
            wxPayLog.setTradeType(tradeType);
            addWxPayLogService.save(wxPayLog);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "微信支付");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 支付回调通知，测试用
     * @param request
     */
    @RequestMapping(value = "notify")
    @ResponseBody
    public String notify(HttpServletRequest request){
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
            }
            wxNotifyService.notify(map, 0);
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
