package com.zs.web.controller.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialNotStockPageService;
import com.zs.service.placeorder.FindPlaceOrderPageByWhereService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderPageByWhereService;
import com.zs.web.controller.LoggerController;
import freemarker.template.utility.StringUtil;
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
 * 统计一个学期库存不足的教材
 * Created by Allen on 2015/8/21.
 */
@Controller
@RequestMapping(value = "/findTeachMaterialNotStockPage")
public class FindTeachMaterialNotStockPageController extends LoggerController {

    private static Logger log = Logger.getLogger(FindTeachMaterialPageByWhereController.class);

    @Resource
    private FindTeachMaterialNotStockPageService findTeachmaterialNotStockPageService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindStudentBookOrderPageByWhereService findStudentBookOrderPageByWhereService;
    @Resource
    private FindPlaceOrderPageByWhereService findPlaceOrderPageByWhereService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "semesterId", required = false, defaultValue = "") String semesterId,
                       HttpServletRequest request) {
        try {
            //获取当前学期
            Semester semester = findNowSemesterService.getNowSemester();
            List<Semester> semesterList = findNowSemesterService.getAll();
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("tttt.stockD", false);
            pageInfo = findTeachmaterialNotStockPageService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("semesterList", semesterList);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询库存不足教材信息");
            return "error";
        }
        return "teachMaterial/tmForNotStockList";
    }

    @RequestMapping(value = "findOrder")
    public String findOrder(@RequestParam(value = "semesterId", required = false, defaultValue = "") String semesterId,
                            @RequestParam("tmId") String tmId,
                            HttpServletRequest request) {
        try {
            //获取当前学期
            Semester semester = findNowSemesterService.getNowSemester();
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId);
            params.put("isStock", StudentBookOrder.ISSTOCK_NOT+"");
            params.put("tmCount", "0");
            params.put("tmId", tmId.trim());
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.code", true);
            pageInfo.setCountOfCurrentPage(999999);
            pageInfo = findStudentBookOrderPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            params = new HashMap<String, String>();
            params.put("semesterId", StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId);
            params.put("isStock", TeachMaterialPlaceOrder.ISSTOCK_NOT+"");
            params.put("tmCount", "0");
            params.put("tmId", tmId.trim());
            params.put("isSpecAll", TeachMaterialPlaceOrder.IS_SPEC_ALL_NOT+"");
            PageInfo placePageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap2 = new HashMap<String, Boolean>();
            sortMap2.put("t.order_code", true);
            placePageInfo.setCountOfCurrentPage(999999);
            placePageInfo = findPlaceOrderPageByWhereService.findPage(placePageInfo, params, sortMap2);


            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("placePageInfo", placePageInfo);
            return "teachMaterial/tmForNotStockOrderList";
        } catch (Exception e) {
            super.outputException(request, e, log, "查询教材库存不足的订单");
            return "error";
        }
    }
}
