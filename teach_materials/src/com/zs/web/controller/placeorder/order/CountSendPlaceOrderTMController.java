package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.placeorder.CountSendPlaceOrderTMService;
import com.zs.web.controller.LoggerController;
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
 * Created by Allen on 2015/9/16.
 */
@Controller
@RequestMapping(value = "/countSendPlaceOrderTM")
public class CountSendPlaceOrderTMController extends LoggerController {
    private static Logger log = Logger.getLogger(CountSendPlaceOrderTMController.class);

    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private CountSendPlaceOrderTMService countSendPlaceOrderTMService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) {
        //获取学期数据
        List<Semester> semesterList = findSemesterPageByWhereService.getAll();
        request.setAttribute("semesterList", semesterList);
        return "placeOrder/countSendPlaceOrderTMList";
    }

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="spotCodes", required=false, defaultValue="") String spotCodes,
                       @RequestParam(value="tmName", required=false, defaultValue="") String tmName,
                       @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                       @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                       @RequestParam(value="orderCode", required=false, defaultValue="") String orderCode,
                       HttpServletRequest request){
        try{
            //获取学期数据
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCodes", spotCodes.trim());
            params.put("tmName", tmName.trim());
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            params.put("orderCode", orderCode);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo = countSendPlaceOrderTMService.findPageByWhere(pageInfo, params, null);

            request.setAttribute("method", "search");
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("semesterList", semesterList);
            return "placeOrder/countSendPlaceOrderTMList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计已发出的预订单教材信息");
            return "error";
        }
    }
}
