package com.zs.web.controller.placeorder.ordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import com.zs.service.placeorder.FindPlaceOrderTMForSpotPrintService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
@Controller
@RequestMapping(value = "/findPlaceOrderTMForSpotPrint")
public class FindPlaceOrderTMForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPlaceOrderTMForSpotPrintController.class);

    @Resource
    private FindPlaceOrderTMForSpotPrintService findPlaceOrderTMForSpotPrintService;
    @Resource
    private FindPreviousSemesterService findPreviousSemesterService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="state", required=false, defaultValue="1") String state,
                       @RequestParam(value="flag") String flag,
                       @RequestParam(value = "operateTime", required = false, defaultValue = "") String operateTime,
                       HttpServletRequest request){
        try{

            //查询学期
            Semester semester = findPreviousSemesterService.get(Long.parseLong(semesterId));
            if(null == semester){
                throw new BusinessException("没有找到学期信息");
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("state", state);
            params.put("operateTime", operateTime);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.address", true);
            sortMap.put("t.admin_name", true);
            sortMap.put("t.order_code", true);
            sortMap.put("t.course_code", true);
            JSONObject result = findPlaceOrderTMForSpotPrintService.findPlaceOrderTMForSpotPrint("0".equals(flag) ? null : request, params, sortMap);
            request.setAttribute("resultJson", result);
            request.setAttribute("semester", semester);
            return "placeOrder/spotOrderTMPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学习中心预订单发书单");
            return "error";
        }
    }
}
