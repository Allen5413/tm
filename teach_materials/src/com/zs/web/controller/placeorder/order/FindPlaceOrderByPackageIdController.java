package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.placeorder.FindPlaceOrderPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/8/2.
 */
@Controller
@RequestMapping(value = "/findPlaceOrderByPackageId")
public class FindPlaceOrderByPackageIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPlaceOrderByPackageIdController.class);

    @Resource
    private FindPlaceOrderPageByWhereService findPlaceOrderPageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "find")
    public String purchaseOrderListSearch(@RequestParam("packageId") long packageId, HttpServletRequest request){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", findNowSemesterService.getNowSemester().getId()+"");
            params.put("packageId", packageId+"");
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(99999);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.order_code", true);
            pageInfo = findPlaceOrderPageByWhereService.findPage(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
            return "placeOrder/placeOrderByPackageIdList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询预订单包的预订单信息");
            return "error";
        }
    }
}
