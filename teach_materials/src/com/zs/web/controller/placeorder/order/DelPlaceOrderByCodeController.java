package com.zs.web.controller.placeorder.order;

import com.zs.service.placeorder.placeorder.DelPlaceOrderByCodeService;
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
 * Created by Allen on 2016/3/28.
 */
@Controller
@RequestMapping(value = "/delPlaceOrderByCode")
public class DelPlaceOrderByCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(EditPlaceOrderForAddressController.class);

    @Resource
    private DelPlaceOrderByCodeService delPlaceOrderByCodeService;

    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(@RequestParam("ids") Long[] ids, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            delPlaceOrderByCodeService.del(ids);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "删除预订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
