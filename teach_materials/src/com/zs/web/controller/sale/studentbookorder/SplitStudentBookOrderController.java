package com.zs.web.controller.sale.studentbookorder;

import com.zs.service.sale.studentbookorder.SplitStudentBookOrderService;
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
 * 拆分订单
 * Created by Allen on 2015/7/8.
 */
@Controller
@RequestMapping(value = "/splitStudentBookOrder")
public class SplitStudentBookOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(SplitStudentBookOrderController.class);

    @Resource
    private SplitStudentBookOrderService splitStudentBookOrderService;

    @RequestMapping(value = "split")
    @ResponseBody
    public JSONObject split(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            splitStudentBookOrderService.splitStudentBookOrder(id, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "拆分订单");
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
            splitStudentBookOrderService.splitStudentBookOrderForAll(spotCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "拆分订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
