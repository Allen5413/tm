package com.zs.web.controller.placeorder.order;

import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.placeorder.CountSpecAllOrderService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/countSpecAllOrder")
public class CountSpecAllOrderController extends LoggerController {

    private static Logger log = Logger.getLogger(CountSpecAllOrderController.class);

    @Resource
    private FindSpotService findSpotService;
    @Resource
    private CountSpecAllOrderService countSpecAllOrderService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                       @RequestParam(value="method", required=false, defaultValue="") String method,
                       HttpServletRequest request){
        try{
            List<Spot> spotList = findSpotService.getAll();
            List<Semester> semesterList = findNowSemesterService.getAll();
            if("search".equals(method)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("spotCode", spotCode.trim());
                params.put("semesterId", semesterId);
                JSONObject json = countSpecAllOrderService.find(params);

                request.setAttribute("resultData", json.get("result"));
                request.setAttribute("sumTotalPrice", json.get("sumTotalPrice"));
            }

            request.setAttribute("semesterList", semesterList);
            request.setAttribute("spotList", spotList);
            return "placeOrder/countSpecAllOrder";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计订单订购情况");
            return "error";
        }
    }
}
