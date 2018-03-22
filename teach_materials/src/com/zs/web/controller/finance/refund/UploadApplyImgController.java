package com.zs.web.controller.finance.refund;

import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.AddRefundService;
import com.zs.service.finance.refund.UploadApplyImgService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/1/11.
 */
@Controller
@RequestMapping(value = "/uploadApplyImgRefund")
public class UploadApplyImgController extends LoggerController<Refund, AddRefundService> {
    private static Logger log = Logger.getLogger(UploadApplyImgController.class);

    @Resource
    private UploadApplyImgService uploadApplyImgService;


    @RequestMapping(value = "open")
    public String open() {
        return "finance/refundApplyUpload";
    }

    @RequestMapping(value = "applyUpload")
    @ResponseBody
    public JSONObject applyUpload(HttpServletRequest request,
                          @RequestParam("code") String code){
        JSONObject jsonObject = new JSONObject();
        try{
            uploadApplyImgService.upload(code, request);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "上传凭证");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
