package com.zs.web.controller.sale.studentbookorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/7/25.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderByPackageId")
public class FindStudentBookOrderByPackageIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderByPackageIdController.class);

    @Resource
    private FindStudentBookOrderPageByWhereService findStudentBookOrderPageByWhereService;
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
            sortMap.put("t.code", true);
            pageInfo = findStudentBookOrderPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
            return "studentOrder/studentOrderByPackageIdList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询学生订单");
            return "error";
        }
    }
}
