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
 * 刷新预订单
 * Created by Allen on 2015/7/31.
 */
@Controller
@RequestMapping(value = "/refreshPlaceOrder")
public class RefreshPlaceOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(RefreshPlaceOrderController.class);

    @Resource
    private PlaceOrderService placeOrderService;

    @RequestMapping(value = "refresh")
    @ResponseBody
    public JSONObject refresh(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            placeOrderService.refreshPlaceOrder(id, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "刷新预订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "refreshAll")
    @ResponseBody
    public JSONObject refreshAll(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            placeOrderService.refreshPlaceOrderAll(UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "刷新所有预订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
