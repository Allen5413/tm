package com.zs.web.controller.sale.onceorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.sale.onceorder.CountSendOnceOrderTMService;
import com.zs.service.sale.studentbookorder.CountSendStudentBookOrderTMService;
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
 * Created by Allen on 2015/9/4.
 */
@Controller
@RequestMapping(value = "/countSendOnceOrderTM")
public class CountSendOnceOrderTMController extends LoggerController{
        private static Logger log = Logger.getLogger(CountSendOnceOrderTMController.class);

        @Resource
        private FindSemesterPageByWhereService findSemesterPageByWhereService;
        @Resource
        private CountSendOnceOrderTMService countSendOnceOrderTMService;

        @RequestMapping(value = "open")
        public String open(HttpServletRequest request) {
            //获取学期数据
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();
            request.setAttribute("semesterList", semesterList);
            return "onceOrder/countSendOnceOrderTMList";
        }

        @RequestMapping(value = "find")
        public String find(@RequestParam(value="semesterId") String semesterId,
            @RequestParam(value="spotCodes", required=false, defaultValue="") String spotCodes,
            @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
            @RequestParam(value="tmName", required=false, defaultValue="") String tmName,
            @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
            @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
            HttpServletRequest request){
        try{
            //获取学期数据
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCodes", spotCodes.trim());
            params.put("studentCode", studentCode.trim());
            params.put("tmName", tmName.trim());
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo = countSendOnceOrderTMService.findPageByWhere(pageInfo, params, null);

            request.setAttribute("method", "search");
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("semesterList", semesterList);
            return "onceOrder/countSendOnceOrderTMList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计已发出的学生订单教材信息");
            return "error";
        }
    }
}
