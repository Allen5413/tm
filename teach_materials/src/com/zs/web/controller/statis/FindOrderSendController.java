package com.zs.web.controller.statis;

import com.zs.service.statis.FindOrderSendService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/9/23.
 */
@Controller
@RequestMapping(value = "/findOrderSend")
public class FindOrderSendController extends LoggerController {
    private static Logger log = Logger.getLogger(FindOrderConfirmController.class);

    @Resource
    private FindOrderSendService findOrderSendService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request){
        try{
            JSONObject jsonObject = findOrderSendService.findListByWhere();
            request.setAttribute("result", jsonObject);
            return "statis/findOrderSend";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计订单发出情况");
            return "error";
        }
    }
}
