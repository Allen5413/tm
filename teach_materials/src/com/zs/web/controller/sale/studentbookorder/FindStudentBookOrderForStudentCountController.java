package com.zs.web.controller.sale.studentbookorder;

import com.zs.service.sync.spot.FindSpotService;
import  net.sf.json.JSONObject;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForStudentCountService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderForStudentCount")
public class FindStudentBookOrderForStudentCountController  extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderForStudentCountController.class);

    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private FindStudentBookOrderForStudentCountService findStudentBookOrderForStudentCountService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindSpotService findSpotService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="countNum", required=false, defaultValue="1000") String countNum,
                       @RequestParam(value="state", required=false, defaultValue="1") String state,
                       HttpServletRequest request){
        try{
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();
            Semester semester = findNowSemesterService.getNowSemester();
            semesterId = StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId;

            List<JSONObject> list = findStudentBookOrderForStudentCountService.find(Long.parseLong(semesterId), spotCode, Integer.parseInt(state), Integer.parseInt(countNum));

            request.setAttribute("semesterId", semesterId);
            request.setAttribute("spotList", findSpotService.getAll());
            request.setAttribute("semesterList", semesterList);
            request.setAttribute("countNum", countNum);
            request.setAttribute("list", list);
            return "studentOrder/studentCountOrderList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询发学生的订单统计");
            return "error";
        }
    }
}
