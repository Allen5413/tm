package com.zs.web.controller.ebook.spotreplacepay;

import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.AddSpotReplacePayService;
import com.zs.service.ebook.spotreplacepay.AuditSpotReplacePayService;
import com.zs.tools.PropertiesTools;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
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
@RequestMapping(value = "/auditSpotReplacePay")
public class AuditSpotReplacePayController extends LoggerController<SpotReplacePay, AddSpotReplacePayService> {
    private static Logger log = Logger.getLogger(AuditSpotReplacePayController.class);

    @Resource
    private AuditSpotReplacePayService auditSpotReplacePayService;


    @RequestMapping(value = "audit")
    @ResponseBody
    public JSONObject audit(HttpServletRequest request,
                          @RequestParam("id")long id,
                          @RequestParam("state")int state,
                          @RequestParam("arrivalTime")String arrivalTime,
                          @RequestParam("payType")int payType,
                          @RequestParam("remark")String remark)throws Exception{
        JSONObject json = new JSONObject();
        try{
            auditSpotReplacePayService.audit(id, state, arrivalTime, payType, remark, UserTools.getLoginUserForName(request));
            json.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "审核学生交费");
            json.put("state", 1);
            json.put("msg",msg);
        }
        return json;
    }
}