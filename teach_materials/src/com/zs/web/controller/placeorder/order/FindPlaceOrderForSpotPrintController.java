package com.zs.web.controller.placeorder.order;

import com.zs.service.placeorder.FindPlaceOrderForSpotPrintService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
@Controller
@RequestMapping(value = "/findPlaceOrderForSpotPrint")
public class FindPlaceOrderForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPlaceOrderForSpotPrintController.class);

    @Resource
    private FindPlaceOrderForSpotPrintService findPlaceOrderForSpotPrintService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="state", required=false, defaultValue="1") String state,
                       HttpServletRequest request){
        try{

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("state", state);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("deptName", true);
            JSONArray jsonArray = findPlaceOrderForSpotPrintService.findPlaceOrderForSpotPrint(params, sortMap);
            request.setAttribute("resultJson", jsonArray);
            return "placeOrder/spotOrderPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学习中心预订单发书单");
            return "error";
        }
    }
}
