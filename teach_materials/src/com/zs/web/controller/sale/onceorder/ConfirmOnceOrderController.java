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
import java.util.HashMap;
import java.util.Map;

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

    static Map<String, Integer> map = new HashMap<String, Integer>();
    static Map<String, Integer> errorMap = new HashMap<String, Integer>();

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
    public String batchConfirm(@RequestParam("ids") String ids, HttpServletRequest request){
        try{
            String loginName = UserTools.getLoginUserForName(request);
            String spotCode = UserTools.getLoginUserForSpotCode(request);
            int num = null == map.get(spotCode) ? 0 : map.get(spotCode);
            int errorNum = null == errorMap.get(spotCode) ? 0 : errorMap.get(spotCode);
            if(0 < num){
                num = 0;
                map.put(spotCode, num);
            }
            if(0 < errorNum){
                errorNum = 0;
                errorMap.put(spotCode, num);
            }
            String[] idArray = ids.split(",");
            new Thread(new BatchConfirm(loginName, spotCode, idArray, confirmOnceOrderService)).start();
            request.setAttribute("num", num);
            request.setAttribute("errorNum", errorNum);
            request.setAttribute("totalNum", idArray.length);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "确认订单");
        }
        return "spotPages/onceOrder/batchConfirmOrderNum";
    }

    @RequestMapping(value = "findConfirmNum")
    @ResponseBody
    public JSONObject findConfirmNum(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            int num = map.get(UserTools.getLoginUserForSpotCode(request));
            int errorNum = errorMap.get(UserTools.getLoginUserForSpotCode(request));
            jsonObject.put("num", num);
            jsonObject.put("errorNum", errorNum);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "查询已确认订单数");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}

class BatchConfirm implements Runnable{
    String loginName = "";
    String spotCode = "";
    String[] idArray;
    ConfirmOnceOrderService confirmOnceOrderService;

    public BatchConfirm(String loginName, String spotCode, String[] idArray, ConfirmOnceOrderService confirmOnceOrderService){
        this.loginName = loginName;
        this.spotCode = spotCode;
        this.idArray = idArray;
        this.confirmOnceOrderService = confirmOnceOrderService;
    }

    @Override
    public void run(){
        int num = 0;
        int errorNum = 0;
        if(null != idArray && 0 < idArray.length){
            for(String id : idArray) {
                try {
                    confirmOnceOrderService.confirmOnceOrder(Long.parseLong(id), spotCode, loginName);
                } catch (Exception e) {
                    errorNum++;
                    e.printStackTrace();
                }
                num++;
                ConfirmOnceOrderController.map.put(spotCode, num);
                ConfirmOnceOrderController.errorMap.put(spotCode, errorNum);
            }
        }
    }
}
