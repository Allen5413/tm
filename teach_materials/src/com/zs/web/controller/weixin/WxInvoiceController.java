package com.zs.web.controller.weixin;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.domain.sync.SpotWx;
import com.zs.domain.sync.Student;
import com.zs.service.finance.invoice.AddInvoiceService;
import com.zs.service.finance.invoice.FindAddInvoiceStudentListService;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import com.zs.web.controller.LoggerController;
import com.zs.weixin.mp.api.WxMpInMemoryConfigStorage;
import com.zs.weixin.mp.api.WxMpService;
import com.zs.weixin.mp.api.WxMpServiceImpl;
import com.zs.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2016/7/13.
 */
@Controller
@RequestMapping(value = "/wxInvoice")
public class WxInvoiceController extends LoggerController {
    private static Logger log = Logger.getLogger(WxInvoiceController.class);

    @Resource
    private FindStudentByOpenIdService findStudentByOpenIdService;
    @Resource
    private FindSpotWxByOpenIdService findSpotWxByOpenIdService;
    @Resource
    private FindAddInvoiceStudentListService findAddInvoiceStudentListService;
    @Resource
    private AddInvoiceService addInvoiceService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;

    public WxMpInMemoryConfigStorage config;
    public WxMpService wxMpService;

    @RequestMapping(value = "openInvoice")
    public String openInvoice(HttpServletRequest request, @RequestParam("code")String code){
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

            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();

            SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
            if(null != spotWx){
                throw new BusinessException("学习中心用户暂时不具备该功能");
            }
            Student student = findStudentByOpenIdService.find(openId);
            if(null != student){
                Map<String, String> params = new HashMap<String, String>();
                params.put("spotCode", student.getSpotCode());
                params.put("studentCode", student.getCode());
                JSONArray jsonArray = findAddInvoiceStudentListService.find(params);
                if(null != jsonArray && 0 < jsonArray.size()){
                    JSONObject json = (JSONObject) jsonArray.get(0);
                    request.setAttribute("money", json.get("openMoney"));
                }
                request.setAttribute("studentCode", student.getCode());
                request.setAttribute("name", student.getName());
                request.setAttribute("spec", SpecEnum.getDescn(student.getSpecCode()));
                request.setAttribute("level", LevelEnum.getDescn(student.getLevelCode()));
                request.setAttribute("openId", openId);
            }
            return "weixin/invoice";
        }catch (Exception e){
            super.outputException(request,e,log,"跳转我要开票页面失败");
            return "error";
        }
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("studentCode") String studentCode,
                          @RequestParam("title") String title,
                          @RequestParam("money") String money){
        JSONObject jsonObject = new JSONObject();
        try{
            Student student = findStudentByCodeService.getStudentByCode(studentCode);
            if(null == student){
                throw new BusinessException("没有找到开票人的相关信息");
            }
            addInvoiceService.wxAdd(student.getCode(), title, money, student.getSpotCode(), student.getCode());
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增开学生明细发票名单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
