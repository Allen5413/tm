package com.zs.web.controller.weixin;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import com.zs.service.sync.student.EditStudentForIsSendStudentByCodeService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/7/13.
 */
@Controller
@RequestMapping(value = "/wxAddress")
public class WxAddressController extends LoggerController {
    private static Logger log = Logger.getLogger(WxAddressController.class);

    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;
    @Resource
    private FindSpotWxByOpenIdService findSpotWxByOpenIdService;
    @Resource
    private EditStudentForIsSendStudentByCodeService editStudentForIsSendStudentByCodeService;

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;

    @RequestMapping(value = "setAddress")
    public String openInvoice(HttpServletRequest request, @RequestParam("code")String code, @RequestParam(value = "openId", required = false, defaultValue = "")String openId){
        try {
            config = new WxMpInMemoryConfigStorage();
            /**
             * 测试账号
             config.setAppId("wx9670fc1f7d7fc941"); // 设置微信公众号的appid
             config.setSecret("2c4698cb1c075da218691476f0e5f482"); // 设置微信公众号的app corpSecret*/

            /**
             * 正式账号*/
            config.setAppId("wx79ba7069388a101a"); // 设置微信公众号的appid
            config.setSecret("1bc0d069914b1f904168fe57c0e65102"); // 设置微信公众号的app corpSecret

            config.setToken("XIWANG_TOKEN"); // 设置微信公众号的token
            config.setAesKey("XIWANG_KEY"); // 设置微信公众号的EncodingAESKey
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);

            if(StringUtils.isEmpty(openId)) {
                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
                openId = wxMpOAuth2AccessToken.getOpenId();
            }

            SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
            if(null != spotWx){
                throw new BusinessException("学习中心用户暂时不具备该功能");
            }
            Student student = findStudentByOpenIdService.find(openId);
            if(null != student){
                request.setAttribute("student", student);
                request.setAttribute("openId", openId);
            }
            return "weixin/setAddress";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转邮寄地址页面失败");
            return "error";
        }
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public com.alibaba.fastjson.JSONObject editor(@RequestParam("code") String code,
                                                  @RequestParam("province") String province,
                                                  @RequestParam("city") String city,
                                                  @RequestParam("sendAddress") String sendAddress,
                                                  @RequestParam("phone") String phone,
                                                  @RequestParam("postalCode") String postalCode,
                                                  HttpServletRequest request) {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        try{
            if (StringUtils.isEmpty(province)) {
                throw new BusinessException("请输入省");
            }
            if (StringUtils.isEmpty(city)) {
                throw new BusinessException("请输入市");
            }
            if (StringUtils.isEmpty(sendAddress)) {
                throw new BusinessException("请输入详细地址");
            }
            if (StringUtils.isEmpty(phone)) {
                throw new BusinessException("请输入手机号码");
            }
            editStudentForIsSendStudentByCodeService.edit(code, Student.IS_SEND_STUDENT_YES, province+"|"+city+"|"+sendAddress, phone, postalCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改订单邮寄方式");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
