package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.placeorder.FindPlaceOrderPageByWhereService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
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
 * Created by Allen on 2015/7/31.
 */
@Controller
@RequestMapping(value = "/findPlaceOrderForSpotCount")
public class FindPlaceOrderForSpotCountController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPlaceOrderForSpotCountController.class);

    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindPlaceOrderPageByWhereService findPlaceOrderForSpotCountService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                          @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                          @RequestParam(value="state", required=false, defaultValue="1") String state,
                                          HttpServletRequest request){
        try{
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();
            Semester semester = findNowSemesterService.getNowSemester();

            semesterId = StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId;

            List<Spot> spotList = findSpotService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("state", state);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.code", true);
            pageInfo = findPlaceOrderForSpotCountService.findPage(pageInfo, params, sortMap);

            request.setAttribute("semesterId", semesterId);
            request.setAttribute("semesterList", semesterList);
            request.setAttribute("spotList", spotList);
            request.setAttribute("pageInfo", pageInfo);
            return "placeOrder/spotCountOrderList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询学习中心预订单统计");
            return "error";
        }
    }
}
