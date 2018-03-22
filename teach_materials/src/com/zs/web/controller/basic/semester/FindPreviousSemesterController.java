package com.zs.web.controller.basic.semester;

import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 查询上一学期信息的controller
 * Created by LihongZhang on 2015/5/12.
 */
@Controller
@RequestMapping(value = "/findPreviousSemester")
public class FindPreviousSemesterController extends LoggerController<Semester,FindPreviousSemesterService> {

    private static Logger log = Logger.getLogger(FindPreviousSemesterController.class);

    @Resource
    private FindPreviousSemesterService findPreviousSemesterService;

    @RequestMapping(value = "findPrevious")
    public String getPreviousSemester(HttpServletRequest request){
        try {
            Semester semester = findPreviousSemesterService.getPreviousSemester();
            if (null!=semester){
                request.setAttribute("semester",semester);
            }
        }catch (Exception e){
            super.outputException(request,e,log, "查询上学期信息");
            return "error";
        }
        return "/semester/semesterPrevious";
    }
}
