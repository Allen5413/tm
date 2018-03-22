package com.zs.web.controller.ebook.spotreplacepay;

import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.AddSpotReplacePayService;
import com.zs.tools.PropertiesTools;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2018/1/4.
 */
@Controller
@RequestMapping(value = "/addSpotReplacePay")
public class AddSpotReplacePayController extends LoggerController<SpotReplacePay, AddSpotReplacePayService> {
    private static Logger log = Logger.getLogger(AddSpotReplacePayController.class);

    @Resource
    private AddSpotReplacePayService addSpotReplacePayService;


    @RequestMapping(value = "open")
    public String open() {
        return "ebook/spotreplacepay/add";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("codes")String codes,
                          @RequestParam("moneyStr")String moneyStr,
                          SpotReplacePay spotReplacePay)throws Exception{
        JSONObject json = new JSONObject();
        try{
            MultipartRequest mulReu = (MultipartRequest)request;
            String uploadPath = new PropertiesTools("resource/commons.properties").getProperty("ebook_img_path");
            //处理上传图片
            String imagUrl = UpLoadFileTools.uploadImg(request, mulReu.getFiles("payImg"), "jpg|png|jpeg", 400, 7, uploadPath);
            spotReplacePay.setImagUrl(imagUrl);
            spotReplacePay.setCreator(UserTools.getLoginUserForLoginName(request));
            addSpotReplacePayService.add(codes.split(","), Float.parseFloat(moneyStr), spotReplacePay);
            json.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "学生交费");
            json.put("state", 1);
            json.put("msg",msg);
        }
        return json;
    }
}