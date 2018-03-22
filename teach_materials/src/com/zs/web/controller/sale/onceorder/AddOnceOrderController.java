package com.zs.web.controller.sale.onceorder;

import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.service.sale.onceorder.AddOnceOrderService;
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
 * 新增学生订单
 * Created by Allen on 2015/7/15.
 */
@Controller
@RequestMapping(value = "/addOnceOrder")
public class AddOnceOrderController extends LoggerController<StudentBookOnceOrder, AddOnceOrderService> {
    private static Logger log = Logger.getLogger(AddOnceOrderController.class);

    @Resource
    private AddOnceOrderService addOnceOrderService;


    @RequestMapping(value = "open")
    public String open() {
        return "onceOrder/confirmOrderTMAdd";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("studentCode") String studentCode,
                          @RequestParam("tmIds") String[] tmIds,
                          @RequestParam("tmCounts") String[] tmCounts,
                          @RequestParam("courseCodes") String[] courseCodes){
        JSONObject jsonObject = new JSONObject();
        try{
            StringBuilder idAndCounts = new StringBuilder();
            for(int i=0; i<tmIds.length; i++){
                String tmId = tmIds[i];
                String count = tmCounts[i];
                String courseCode = courseCodes[i];
                idAndCounts.append(tmId).append("_").append(count).append("_").append(courseCode).append(",");
            }
            JSONObject returnJSON = addOnceOrderService.add(studentCode, idAndCounts.toString().substring(0, idAndCounts.length() - 1), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
            jsonObject.put("orderInfo", returnJSON);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增学生订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}

