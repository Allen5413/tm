package com.zs.web.controller.statis;

import com.zs.service.statis.FindOrderConfirmService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/9/22.
 */
@Controller
@RequestMapping(value = "/findOrderConfirm")
public class FindOrderConfirmController extends LoggerController {
    private static Logger log = Logger.getLogger(FindOrderConfirmController.class);

    @Resource
    private FindOrderConfirmService findOrderConfirmService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request){
        try{
            JSONObject jsonObject = findOrderConfirmService.findListByWhere();
            request.setAttribute("result", jsonObject);
            return "statis/findOrderConfirm";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计订单确认情况");
            return "error";
        }
    }
}
