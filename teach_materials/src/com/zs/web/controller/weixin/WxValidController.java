package com.zs.web.controller.weixin;

import com.zs.domain.finance.Invoice;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.service.finance.invoice.FindInvoiceByStudentCodeService;
import com.zs.service.finance.studentexpense.FindStudentExpenseByCodeService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForWXByCodeService;
import com.zs.service.sync.spotwx.EditSpotWxOpenIdByIdCardService;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import com.zs.service.sync.student.EditStudentOpenIdByIdCardNoService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.tools.DateTools;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.common.api.WxConsts;
import com.zs.weixin.common.bean.WxMenu;
import com.zs.weixin.common.util.StringUtils;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.WxMpCustomMessage;
import com.zs.weixin.mp.bean.WxMpTemplateData;
import com.zs.weixin.mp.bean.WxMpTemplateMessage;
import com.zs.weixin.mp.bean.WxMpXmlMessage;
import com.zs.weixin.mp.bean.result.WxMpUser;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信接口配置信息验证
 * Created by Allen on 2016/5/23.
 */
@Controller
@RequestMapping(value = "/wxValid")
public class WxValidController extends LoggerController {

    @Resource
    private EditStudentOpenIdByIdCardNoService editStudentOpenIdByIdCardNoService;
    @Resource
    private EditSpotWxOpenIdByIdCardService editSpotWxOpenIdByIdCardService;
    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;
    @Resource
    private FindStudentExpenseByCodeService findStudentExpenseByCodeService;
    @Resource
    private FindStudentBookOrderForWXByCodeService findStudentBookOrderForWXByCodeService;
    @Resource
    private FindInvoiceByStudentCodeService findInvoiceByStudentCodeService;
    @Resource
    private FindSpotWxByOpenIdService findSpotWxByOpenIdService;

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;

    @RequestMapping(value = "validWx")
    public void validWx(HttpServletRequest request, HttpServletResponse response){
        config = new WxMpInMemoryConfigStorage();
        /**
         * 测试账号
        config.setAppId("wx9670fc1f7d7fc941"); // 设置微信公众号的appid
        config.setSecret("2c4698cb1c075da218691476f0e5f482"); // 设置微信公众号的app corpSecret
        config.setOauth2redirectUri("http://allen.ittun.com/wxSearch/openPay.htm");*/
         /**
         * 正式账号*/
        config.setAppId("wx79ba7069388a101a"); // 设置微信公众号的appid
        config.setSecret("1bc0d069914b1f904168fe57c0e65102"); // 设置微信公众号的app corpSecret
        config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openPay.htm");

        config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
        config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        PrintWriter out = null;
        try {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            //请求方法
            String methodType = request.getMethod();

            //配置url请求验证
            if("GET".equals(methodType)){
                // 随机字符串
                String echostr = request.getParameter("echostr");
                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                out = response.getWriter();
                if(wxMpService.checkSignature(timestamp, nonce, signature)){
                    out.print(echostr);
                }

                //创建自定义菜单
                WxMenu wxMenu = new WxMenu();

                List<WxMenu.WxMenuButton> buttonList = new ArrayList<WxMenu.WxMenuButton>();
                List<WxMenu.WxMenuButton> buttonList2 = new ArrayList<WxMenu.WxMenuButton>();
                List<WxMenu.WxMenuButton> buttonList3 = new ArrayList<WxMenu.WxMenuButton>();

                WxMenu.WxMenuButton button = new WxMenu.WxMenuButton();
                button.setName("账户查询");
                button.setType("click");
                button.setKey("myMoney");
                buttonList.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("我要交费");
                button.setType("view");
                //首先构造网页授权url
                String url = wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_BASE, null);
                button.setUrl(url);
                buttonList.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("电子教材");
                button.setType("view");
                config.setOauth2redirectUri("http://xiwang.attop.com/wxPay/openEBookPay.htm");
                wxMpService.setWxMpConfigStorage(config);
                String url4 = wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_BASE, null);
                button.setUrl(url4);
                buttonList.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("我的账户");
                button.setSubButtons(buttonList);
                buttonList2.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("我的订单");
                button.setType("click");
                button.setKey("myOrder");

                buttonList2.add(button);


                button = new WxMenu.WxMenuButton();
                button.setName("我要开票");
                button.setType("view");
                config.setOauth2redirectUri("http://xiwang.attop.com/wxInvoice/openInvoice.htm");
                wxMpService.setWxMpConfigStorage(config);
                String url2 = wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_BASE, null);
                button.setUrl(url2);
                buttonList3.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("我的发票");
                button.setType("click");
                button.setKey("myInvoice");
                buttonList3.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("邮寄地址");
                button.setType("view");
                config.setOauth2redirectUri("http://xiwang.attop.com/wxAddress/setAddress.htm");
                wxMpService.setWxMpConfigStorage(config);
                String url3 = wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_BASE, null);
                button.setUrl(url3);
                buttonList3.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("解除绑定");
                button.setType("click");
                button.setKey("cancel");
                buttonList3.add(button);

                button = new WxMenu.WxMenuButton();
                button.setName("更多服务");
                button.setSubButtons(buttonList3);

                buttonList2.add(button);

                wxMenu.setButtons(buttonList2);
                wxMpService.menuCreate(wxMenu);

            }else {  // 微信消息验证
                String returnString = "";
                if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
                    // 消息签名不正确，说明不是公众平台发过来的消息
                    returnString = "非法请求";
                }
                String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");
                WxMpXmlMessage inMessage = null;
                //处理接收消息
                if ("raw".equals(encryptType)) {
                    // 明文传输的消息
                    inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
                } else if ("aes".equals(encryptType)) {
                    // 是aes加密的消息
                    String msgSignature = request.getParameter("msg_signature");
                    inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), config, timestamp, nonce, msgSignature);
                } else {
                    returnString = "不可识别的加密类型";
                }


                String openId = inMessage.getFromUserName();
                //通过openid查询中心信息
                SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
                //通过openid查询学生信息
                Student student = findStudentByOpenIdService.find(openId);
                boolean isSpotUser = true;
                if(null == spotWx){
                    isSpotUser = false;
                }
                //接收用户输入的消息
                if ("TEXT".equals(inMessage.getMsgType().toUpperCase())) {
                    Timestamp wxBoundTime = DateTools.getLongNowTime();
                    //通过证件号码，记录用户的openid
                    JSONObject json = null;
                    json = editSpotWxOpenIdByIdCardService.edit(inMessage.getContent().trim(), openId, wxBoundTime);
                    if("没有找到用户信息".equals(json.get("msg").toString())){
                        json = editStudentOpenIdByIdCardNoService.edit(inMessage.getContent().trim(), openId, wxBoundTime);
                    }else{
                        isSpotUser = true;
                    }
                    String msg = json.get("msg").toString();
                    //把绑定结果发送给用户
                    if (Integer.parseInt(json.get("flag").toString()) == 0) {
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(openId);
                        //正式id
                        templateMessage.setTemplateId("SO2TBpU-zFcqor0nOfuDfNKSFzdnxPmFu3JSVxEHtDI");
                        //测试id
                        //templateMessage.setTemplateId("_m2R29yIGBDzGANMt_iCQErOTZgrAy0KlVU_FfCb2b4");
                        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的用户您好，您已经绑定成功"));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", json.get("msg").toString()));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", isSpotUser ? "学习中心用户":"学生用户"));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", wxBoundTime.toString()));
                        wxMpService.templateSend(templateMessage);
                    }
                    if(Integer.parseInt(json.get("flag").toString()) == 1) {
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(openId);
                        //正式id
                        templateMessage.setTemplateId("ue4N3fkV4etusglU2he9Va49aRoDn86AQDEbiUg68Is");
                        //测试id
                        //templateMessage.setTemplateId("JrtwyhJKoSv3yas_ycyt5An9vktyDRsNnJVeCo842QA");
                        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的用户您好，您绑定失败"));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", inMessage.getContent().trim()));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", json.get("msg").toString()));
                        wxMpService.templateSend(templateMessage);
                    }
                } else {
                    //接收用户点击菜单的消息
                    if ("EVENT".equals(inMessage.getMsgType().toUpperCase())) {
                        //关注
                        if ("subscribe".equals(inMessage.getEvent())) {
                            //把结果发送给用户
                            WxMpCustomMessage message = WxMpCustomMessage
                                    .TEXT()
                                    .toUser(openId)
                                    .content("尊敬的用户您好，感谢您关注西网公众号。\n\n您可以直接回复证件号码，来查询个人账户、个人订单等相关信息")
                                    .build();
                            wxMpService.customMessageSend(message);
                        }
                        //取消关注
                        if ("unsubscribe".equals(inMessage.getEvent())) {
                            if(isSpotUser){
                                JSONObject json = editSpotWxOpenIdByIdCardService.cancelBound(openId);
                            }else{
                                JSONObject json = editStudentOpenIdByIdCardNoService.cancelBound(openId);
                            }
                        }
                        //查询我的账户
                        if ("myMoney".equals(inMessage.getEventKey())) {
                            if(null == student && null == spotWx){
                                this.validIsBound(openId, wxMpService);
                            }else {
                                if(isSpotUser){
                                    WxMpCustomMessage message = WxMpCustomMessage
                                            .TEXT()
                                            .toUser(openId)
                                            .content("尊敬的用户您好，感谢您关注西网公众号。\n\n学习中心用户暂时不具备该功能")
                                            .build();
                                    wxMpService.customMessageSend(message);
                                }else {
                                    JSONObject json = findStudentExpenseByCodeService.find(student.getCode());
                                    //把结果发送给用户
                                    WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                    templateMessage.setToUser(openId);
                                    //正式id
                                    templateMessage.setTemplateId("z2hstS4E6QUS4QzoA9x_4FbWaFC8g6Ep_M85l08D4oc");
                                    //测试id
                                    //templateMessage.setTemplateId("SEVDpUQVJzLn9B06O0OHOXyyi3pGQnk2ZL8tKFqqQ8c");
                                    templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + student.getName() + "您好，您的当前账户信息如下"));
                                    templateMessage.getDatas().add(new WxMpTemplateData("keyword1", json.get("sumPay").toString(), "#173177"));
                                    templateMessage.getDatas().add(new WxMpTemplateData("keyword2", json.get("sumBuy").toString(), "#173177"));
                                    templateMessage.getDatas().add(new WxMpTemplateData("keyword3", json.get("sumAcc").toString(), "#173177"));
                                    templateMessage.getDatas().add(new WxMpTemplateData("keyword4", json.get("sumOwn").toString(), "#173177"));
                                    templateMessage.getDatas().add(new WxMpTemplateData("remark", "以上信息截止" + DateTools.getLongNowTime().toString().substring(0, 19)));
                                    templateMessage.setUrl("http://xiwang.attop.com/wxSearch/finance.htm?code=" + student.getCode()+"&isSpotUser=0");
                                    wxMpService.templateSend(templateMessage);
                                }
                            }
                        }
                        //查询我的正在处理的订单
                        if ("myOrder".equals(inMessage.getEventKey())) {
                            if(null == student && null == spotWx){
                                this.validIsBound(openId, wxMpService);
                            }else {
                                if(isSpotUser){
                                    WxMpCustomMessage message = WxMpCustomMessage
                                            .TEXT()
                                            .toUser(openId)
                                            .content("尊敬的用户您好，感谢您关注西网公众号。\n\n学习中心用户暂时不具备该功能")
                                            .build();
                                    wxMpService.customMessageSend(message);
                                }else {
                                    List<JSONObject> list = findStudentBookOrderForWXByCodeService.find(student.getCode());
                                    if (null != list && 0 < list.size()) {
                                        JSONObject json = list.get(0);
                                        //把结果发送给用户
                                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                        templateMessage.setToUser(openId);
                                        //正式id
                                        templateMessage.setTemplateId("jfYK-S8a7G3897PSyy2PTfZv1Zp8pO4bAiXw-6th9EY");
                                        //测试id
                                        //templateMessage.setTemplateId("8bimC8PR_ZAGassSRJqHSarDgv0pMVVdMMHDM9dLdFI");
                                        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + student.getName() + "您好，您最新的订单信息如下"));
                                        templateMessage.getDatas().add(new WxMpTemplateData("OrderSn", json.get("orderCode").toString()));
                                        templateMessage.getDatas().add(new WxMpTemplateData("OrderStatus", json.get("state").toString()));
                                        String kuaidi = "暂时还有没快递信息";
                                        if (json.get("kuaidi") != null) {
                                            kuaidi = json.get("kuaidi").toString();
                                        }
                                        templateMessage.getDatas().add(new WxMpTemplateData("remark", "最新快递信息：" + kuaidi + "\n点击下面“详情”，可以查看更多订单信息"));
                                        templateMessage.setUrl("http://xiwang.attop.com/wxSearch/order.htm?code=" + student.getCode());
                                        wxMpService.templateSend(templateMessage);
                                    } else {
                                        //把结果发送给用户
                                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                        templateMessage.setToUser(openId);
                                        //正式id
                                        templateMessage.setTemplateId("jfYK-S8a7G3897PSyy2PTfZv1Zp8pO4bAiXw-6th9EY");
                                        //测试id
                                        //templateMessage.setTemplateId("8bimC8PR_ZAGassSRJqHSarDgv0pMVVdMMHDM9dLdFI");
                                        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + student.getName() + "您好，您最新的订单信息如下"));
                                        templateMessage.getDatas().add(new WxMpTemplateData("OrderSn", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("OrderStatus", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("remark", "当前没有找到您的订单信息\n点击下面“详情”，可以查看更多订单信息"));
                                        templateMessage.setUrl("http://xiwang.attop.com/wxSearch/order.htm?code=" + student.getCode());
                                        wxMpService.templateSend(templateMessage);
                                    }
                                }
                            }
                        }
                        //解除绑定
                        if ("cancel".equals(inMessage.getEventKey())) {
                            if(null == student && null == spotWx){
                                this.validIsBound(openId, wxMpService);
                            }else {
                                JSONObject json = null;
                                if(isSpotUser) {
                                    json = editSpotWxOpenIdByIdCardService.cancelBound(openId);
                                }else{
                                    json = editStudentOpenIdByIdCardNoService.cancelBound(openId);
                                }
                                //把绑定结果发送给用户
                                WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                templateMessage.setToUser(openId);
                                //正式id
                                templateMessage.setTemplateId("BCXS7QAilHgg5u4ilQ0Lxj12ZF3fwc6iHn5Srubl_28");
                                //测试id
                                //templateMessage.setTemplateId("88U0nPBUjdHlWJa3Qlp4GTb0c3dfO64dpq2ANWFuuso");
                                templateMessage.getDatas().add(new WxMpTemplateData("keyword1", null == json.get("name") ? "" : json.get("name").toString()));
                                templateMessage.getDatas().add(new WxMpTemplateData("keyword2", null == json.get("msg") ? "" : json.get("msg").toString()));
                                templateMessage.getDatas().add(new WxMpTemplateData("keyword3", null == json.get("time") ? "" : json.get("time").toString()));
                                if (Integer.parseInt(json.get("flag").toString()) == 0) {
                                    templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的用户您好，您已成功解除绑定"));
                                } else {
                                    templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的用户您好，您解除绑定失败"));
                                }
                                wxMpService.templateSend(templateMessage);
                            }
                        }

                        //我的发票
                        if ("myInvoice".equals(inMessage.getEventKey())) {
                            if(null == student && null == spotWx){
                                this.validIsBound(openId, wxMpService);
                            }else {
                                if(isSpotUser){
                                    WxMpCustomMessage message = WxMpCustomMessage
                                            .TEXT()
                                            .toUser(openId)
                                            .content("尊敬的用户您好，感谢您关注西网公众号。\n\n学习中心用户暂时不具备该功能")
                                            .build();
                                    wxMpService.customMessageSend(message);
                                }else{
                                    List<Invoice> invoiceList = findInvoiceByStudentCodeService.find(student.getCode());
                                    if(null != invoiceList && 0 < invoiceList.size()){
                                        for (Invoice invoice : invoiceList){
                                            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                            templateMessage.setToUser(openId);
                                            //正式id
                                            templateMessage.setTemplateId("yFYtuhUMbu5h70_wrIX8pIK1JXq8YVS_bQuhJstm8_0");
                                            //测试id
                                            //templateMessage.setTemplateId("_EU4K_kzKG9LLFnOiqUlfzD-gI6svduqvMnJp-5LIAw");
                                            templateMessage.getDatas().add(new WxMpTemplateData("keyword1", invoice.getCode()));
                                            templateMessage.getDatas().add(new WxMpTemplateData("keyword2", null == invoice.getOpenTime() ? "" : invoice.getOpenTime().toString().substring(0, 10)));
                                            templateMessage.getDatas().add(new WxMpTemplateData("keyword3", "重庆西网文化传播有限公司"));
                                            templateMessage.getDatas().add(new WxMpTemplateData("keyword4", invoice.getTitle()));
                                            templateMessage.getDatas().add(new WxMpTemplateData("keyword5", invoice.getMoney()+"元"));
                                            templateMessage.getDatas().add(new WxMpTemplateData("remark", "开票状态："+(invoice.getState() == Invoice.STATE_WAIT ? "待开票" : "已开票")));
                                            wxMpService.templateSend(templateMessage);
                                        }
                                    }else{
                                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                                        templateMessage.setToUser(openId);
                                        //正式id
                                        templateMessage.setTemplateId("yFYtuhUMbu5h70_wrIX8pIK1JXq8YVS_bQuhJstm8_0");
                                        //测试id
                                        //templateMessage.setTemplateId("_EU4K_kzKG9LLFnOiqUlfzD-gI6svduqvMnJp-5LIAw");
                                        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的" + student.getName() + "您好，您的发票信息如下"));
                                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("keyword4", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("keyword5", ""));
                                        templateMessage.getDatas().add(new WxMpTemplateData("remark", "当前没有找到您的发票信息"));
                                        wxMpService.templateSend(templateMessage);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != out) {
            out.close();
            out = null;
        }
    }

    protected void validIsBound(String openId, WxMpService wxMpService)throws Exception{
        //获取微信用户信息
        WxMpUser user = wxMpService.userInfo(openId, "zh_CN");
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(openId);
        //正式id
        templateMessage.setTemplateId("8qMpxt_tiPQT5cb7J3ICkkCYLheHFo_6t2iiN_iIm-Q");
        //测试id
        //templateMessage.setTemplateId("bJpSoIAxlJXSs5OjiPoLjjxB7TLDqWKqyBfWcRtko0o");
        templateMessage.getDatas().add(new WxMpTemplateData("first", "尊敬的用户您好，您还未绑定账号"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", user.getNickname()));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", "未绑定"));
        templateMessage.getDatas().add(new WxMpTemplateData("remark", "请直接回复证件号码来绑定公众号"));
        wxMpService.templateSend(templateMessage);
    }

}
