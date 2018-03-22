package com.zs.web.controller.statis;

import com.zs.service.statis.FindHistoryOrderSendService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/9/25.
 */
@Controller
@RequestMapping(value = "/findHistoryOrderSend")
public class FindHistoryOrderSendController extends LoggerController {
    private static Logger log = Logger.getLogger(FindHistoryOrderSendController.class);

    @Resource
    private FindHistoryOrderSendService findHistoryOrderSendService;

    @RequestMapping(value = "open")
    public String open(){
        return "statis/findHistoryOrderSend";
    }

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, @RequestParam(value = "isSpot", required = false, defaultValue = "") String isSpot ){
        try{
            JSONArray jsonArray = findHistoryOrderSendService.findHistoryOrderSend(isSpot);
            request.setAttribute("result", jsonArray);
            return "statis/findHistoryOrderSend";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计历史学期订单发出情况");
            return "error";
        }
    }

    @RequestMapping(value = "findBySemesterId")
    public String findBySemesterId(@RequestParam("semesterId") long semesterId,  HttpServletRequest request){
        try{
            JSONArray jsonArray = findHistoryOrderSendService.findSpotOrderSendBySemesterId(semesterId);
            request.setAttribute("result", jsonArray);
            return "statis/findOrderSendBySemesterId";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计历史学期订单发出情况");
            return "error";
        }
    }
}
