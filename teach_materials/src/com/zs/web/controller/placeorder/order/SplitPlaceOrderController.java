package com.zs.web.controller.placeorder.order;

import com.zs.service.placeorder.PlaceOrderService;
import com.zs.tools.UserTools;
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
 * 拆分预订单
 * Created by Allen on 2015/7/31.
 */
@Controller
@RequestMapping(value = "/splitPlaceOrder")
public class SplitPlaceOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(SplitPlaceOrderController.class);

    @Resource
    private PlaceOrderService placeOrderService;

    @RequestMapping(value = "split")
    @ResponseBody
    public JSONObject split(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            placeOrderService.splitPlaceOrder(id, UserTools.getLoginUserForName(request), true);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "拆分预订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "splitAll")
    @ResponseBody
    public JSONObject splitAll(HttpServletRequest request, @RequestParam(value = "spotCode", required = false, defaultValue = "") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            placeOrderService.splitPlaceOrderAll(spotCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "拆分所有预订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
