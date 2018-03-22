package com.zs.web.controller.weixin;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.ebook.studentebookpay.FindStudentEBookPayByCodeDAO;
import com.zs.dao.wx.paylog.FindWxPayLogByCodeDAO;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.api.GetStudentFinanceService;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.service.wx.notify.WxNotifyService;
import com.zs.service.wx.paylog.AddWxPayLogService;
import com.zs.service.wx.paylog.FindWxPayLogForMaxCodeService;
import com.zs.tools.HttpRequestTools;
import com.zs.tools.QRCodeTools;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.common.util.crypto.WxCryptUtil;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import com.zs.weixin.mp.bean.result.WxMpPrepayIdResult;
import net.sf.json.JSONObject;
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
@RequestMapping(value = "/wxPay")
public class WxPayController extends LoggerController {
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

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;

    /**
     * 打开支付页面，正式用
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openPay.htm");

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);


            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();

            SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
            if(null != spotWx){
                throw new BusinessException("学习中心用户暂时不具备该功能");
            }
            Student student = findStudentByOpenIdService.find(openId);
            if(null != student){
                request.setAttribute("studentCode", student.getCode());
                request.setAttribute("name", student.getName());
                request.setAttribute("spec", SpecEnum.getDescn(student.getSpecCode()));
                request.setAttribute("level", LevelEnum.getDescn(student.getLevelCode()));
                request.setAttribute("openId", openId);
                request.setAttribute("price", getStudentFinanceService.get(student.getCode()).get("price"));
            }
            return "weixin/pay";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转支付页面失败");
            return "error";
        }
    }


    /**
     * 打开电子教材支付页面，正式用
     * @param request
     */
    @RequestMapping(value = "openEBookPay")
    public String openEBookPay(HttpServletRequest request, @RequestParam("code")String code){
        try {
            config = new WxMpInMemoryConfigStorage();
            /**
             * 测试账号
             config.setAppId("wx9670fc1f7d7fc941"); // 设置微信公众号的appid
             config.setSecret("2c4698cb1c075da218691476f0e5f482"); // 设置微信公众号的app corpSecret
             config.setOauth2redirectUri("http://xiwang.attop.com/wxSearch/openEBookPay.htm");*/

            /**
             * 正式账号*/
            config.setAppId("wx79ba7069388a101a"); // 设置微信公众号的appid
            config.setSecret("1bc0d069914b1f904168fe57c0e65102"); // 设置微信公众号的app corpSecret
            config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openEBookPay.htm");

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);


            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();

            SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
            if(null != spotWx){
                throw new BusinessException("学习中心用户暂时不具备该功能");
            }
            Student student = findStudentByOpenIdService.find(openId);
            if(null != student){
                //查询是否购买过电子教材
                StudentEBookPay studentEBookPay = findStudentEBookPayByCodeDAO.find(student.getCode());
                if(null == studentEBookPay){
                    request.setAttribute("isBuy", 0);
                }else{
                    request.setAttribute("isBuy", 1);
                }
                request.setAttribute("studentCode", student.getCode());
                request.setAttribute("name", student.getName());
                request.setAttribute("spec", SpecEnum.getDescn(student.getSpecCode()));
                request.setAttribute("level", LevelEnum.getDescn(student.getLevelCode()));
                request.setAttribute("openId", openId);
            }
            return "weixin/ebookPay";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转支付页面失败");
            return "error";
        }
    }

    /**
     * 支付，正式用
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openPay.htm");

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
            WxMpPrepayIdResult wxMpPrepayIdResult = wxMpService.getPrepayId(openId, orderCode, money, body, tradeType, ip, "http://xiwang.attop.com/wxPay/notify.htm");
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
     * 电子教材支付，正式用
     * @param request
     */
    @RequestMapping(value = "ebookPay")
    @ResponseBody
    public JSONObject ebookPay(HttpServletRequest request,
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openEBookPay.htm");

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            config.setPartnerId("1356478102");
            config.setPartnerKey("xiWangWeiXinPay20160705161520LQS");
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);

            StudentEBookPay studentEBookPay = findStudentEBookPayByCodeDAO.find(code);
            if(null != studentEBookPay){
                throw new BusinessException("您已经购买了电子教材，无需再次购买！");
            }

            //获取时间戳
            String timeStamp = System.currentTimeMillis()+"";
            //生成订单号
            String orderCode = timeStamp + findWxPayLogForMaxCodeService.find(timeStamp);
            String body = "电子教材费用";
            String ip = HttpRequestTools.getIp(request);
            String tradeType = "JSAPI";
            WxMpPrepayIdResult wxMpPrepayIdResult = wxMpService.getPrepayId(openId, orderCode, money, body, tradeType, ip, "http://xiwang.attop.com/wxPay/ebookNotify.htm");
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
     * PC端扫码支付，正式用
     * @param request
     */
    @RequestMapping(value = "paySM")
    public String paySM(HttpServletRequest request,
                          @RequestParam("code")String code,
                          @RequestParam("money")double money){
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
            config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openPay.htm");

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
            String tradeType = "NATIVE";
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("body", body);
            parameters.put("out_trade_no", orderCode);
            parameters.put("total_fee", ((int) (money * 100))+"");
            parameters.put("spbill_create_ip", ip);
            parameters.put("notify_url", "http://xiwang.attop.com/wxPay/notifySM.htm");
            parameters.put("trade_type", tradeType);
            parameters.put("product_id", System.currentTimeMillis()+"");
            WxMpPrepayIdResult wxMpPrepayIdResult = wxMpService.getPrepayId(parameters);
            Map<String, String> params = new HashMap<String, String>();
            params.put("appId", config.getAppId());
            params.put("timeStamp", timeStamp);
            params.put("nonceStr", wxMpPrepayIdResult.getNonce_str());
            params.put("package", "prepay_id="+wxMpPrepayIdResult.getPrepay_id());
            params.put("signType", "MD5");
            String paySign = WxCryptUtil.createSign(params, config.getPartnerKey());

            //生成二维码图片
            String imgFileName = QRCodeTools.encode(wxMpPrepayIdResult.getCode_url(), "", request.getRealPath("/")+"/qrcode", true);
            request.setAttribute("imgurl", "/qrcode/"+imgFileName);
            request.setAttribute("orderCode", orderCode);
            WxPayLog wxPayLog = new WxPayLog();
            wxPayLog.setStudentCode(code);
            wxPayLog.setOrderCode(orderCode);
            wxPayLog.setBody(body);
            wxPayLog.setIp(ip);
            wxPayLog.setMoney((int) (money * 100));
            wxPayLog.setNonceStr(wxMpPrepayIdResult.getNonce_str());
            wxPayLog.setPackages("prepay_id="+wxMpPrepayIdResult.getPrepay_id());
            wxPayLog.setPaySign(paySign);
            wxPayLog.setReturnMsg(wxMpPrepayIdResult.getReturn_msg());
            wxPayLog.setState(WxPayLog.STATE_NOT_NOTIFY);
            wxPayLog.setTimeStamp(timeStamp);
            wxPayLog.setTradeType(tradeType);
            addWxPayLogService.save(wxPayLog);
            return "studentPages/weixinSM";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转微信扫码支付页面失败");
            return "error";
        }
    }

    /**
     * 打开PC端扫码支付返回结果页面
     * @param request
     */
    @RequestMapping(value = "openPayResult")
    public String openPayResult(HttpServletRequest request,
                                @RequestParam("code")String code){
        try {
            Student student = findStudentByCodeService.getStudentByCode(code);
            request.setAttribute("student", student);
            return "studentPages/wxSMPayResult";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转支付结果页面失败");
            return "error";
        }
    }

    /**
     * 支付回调通知，正式用
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
            }
            wxNotifyService.notify(map, 1);
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

    /**
     * 扫码支付回调通知，正式用
     * @param request
     */
    @RequestMapping(value = "notifySM")
    @ResponseBody
    public String notifySM(HttpServletRequest request){
        System.out.println("-----------------------------------  notifySM  ---------------------------------------");
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
            wxNotifyService.notify(map, 1);
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

    /**
     * 电子教材支付回调通知，正式用
     * @param request
     */
    @RequestMapping(value = "ebookNotify")
    @ResponseBody
    public String ebookNotify(HttpServletRequest request){
        System.out.println("-----------------------------------  ebookNotify  ---------------------------------------");
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
            wxNotifyService.ebookNotify(map);
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
