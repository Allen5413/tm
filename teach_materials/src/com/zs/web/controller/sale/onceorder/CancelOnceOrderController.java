package com.zs.web.controller.sale.onceorder;

import com.zs.service.sale.onceorder.CancelOnceOrderService;
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
 * 取消确认订单操作
 * Created by Allen on 2015/7/16.
 */
@Controller
@RequestMapping(value = "/cancelOnceOrder")
public class CancelOnceOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(CancelOnceOrderController.class);

    @Resource
    private CancelOnceOrderService cancelOnceOrderService;

    @RequestMapping(value = "cancel")
    @ResponseBody
    public JSONObject cancel(@RequestParam("id") Long[] ids, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            cancelOnceOrderService.cancelOnceOrder(ids, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "取消确认订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
