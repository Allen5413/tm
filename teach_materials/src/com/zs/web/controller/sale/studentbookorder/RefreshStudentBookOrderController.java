package com.zs.web.controller.sale.studentbookorder;

import com.zs.service.sale.studentbookorder.RefreshStudentBookOrderService;
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
 * 刷新订单
 * Created by Allen on 2015/7/8.
 */
@Controller
@RequestMapping(value = "/refreshStudentBookOrder")
public class RefreshStudentBookOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(RefreshStudentBookOrderController.class);

    @Resource
    private RefreshStudentBookOrderService refreshStudentBookOrderService;

    @RequestMapping(value = "refresh")
    @ResponseBody
    public JSONObject refresh(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            refreshStudentBookOrderService.refreshStudentBookOrder(id, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "刷新订单");
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
            refreshStudentBookOrderService.refreshStudentBookOrderForAll(UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "刷新所有订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
