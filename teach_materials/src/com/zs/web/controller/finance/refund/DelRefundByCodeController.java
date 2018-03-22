package com.zs.web.controller.finance.refund;

import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.AddRefundService;
import com.zs.service.finance.refund.DelRefundByCodeService;
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
 * Created by Allen on 2016/12/12.
 */
@Controller
@RequestMapping(value = "/delRefundByCode")
public class DelRefundByCodeController extends LoggerController<Refund, AddRefundService> {
    private static Logger log = Logger.getLogger(AddRefundController.class);

    @Resource
    private DelRefundByCodeService delRefundByCodeService;


    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("code") String code){
        JSONObject jsonObject = new JSONObject();
        try{
            delRefundByCodeService.del(code);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "删除退款申请");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
