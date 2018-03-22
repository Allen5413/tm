package com.zs.web.controller.sale.onceorder;

import com.zs.service.sale.onceorder.ConfirmOnceOrderService;
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
 * 确认学生订单
 * Created by Allen on 2015/7/8.
 */
@Controller
@RequestMapping(value = "/confirmOnceOrder")
public class ConfirmOnceOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(ConfirmOnceOrderController.class);

    @Resource
    private ConfirmOnceOrderService confirmOnceOrderService;

    @RequestMapping(value = "confirm")
    @ResponseBody
    public JSONObject confirm(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            confirmOnceOrderService.confirmOnceOrder(id, UserTools.getLoginUserForSpotCode(request), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "确认订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "batchConfirm")
    @ResponseBody
    public JSONObject batchConfirm(@RequestParam("ids") String ids, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            confirmOnceOrderService.confirmOnceOrderForMore(ids, UserTools.getLoginUserForSpotCode(request), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "确认订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
