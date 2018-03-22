package com.zs.web.controller.sync.course;

import com.zs.domain.sync.Course;
import com.zs.service.sync.course.FindCourseBystmIdService;
import com.zs.service.sync.course.FindCourseBytmIdService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/20.
 */
@Controller
@RequestMapping(value = "/findCourseBystmId")
public class FindCourseBystmIdController  extends
        LoggerController<Course, FindCourseBystmIdService> {
    private static Logger log = Logger.getLogger(FindCourseBystmIdController.class);

    @Resource
    private FindCourseBystmIdService findCourseBystmIdService;

    @RequestMapping(value = "doFindCourseBystmId")
    public String doFindCourseBystmId(@RequestParam(value = "stmId") Long stmId,
                                     @RequestParam(value="code", required=false, defaultValue="") String code,
                                     @RequestParam(value="name", required=false, defaultValue="") String name,
                                     HttpServletRequest request){
        try{
            //获得套教材关联的课程
            List<Course> stmCourseList = findCourseBystmIdService.getCourseBystmId(stmId);
            //获取所有课程
            List<Course> courseList = findCourseBystmIdService.getAll();

            request.setAttribute("stmCourseList", stmCourseList);
            request.setAttribute("courseList", courseList);
        }catch(Exception e){
            super.outputException(request, e, log, "查询套教材关联的课程信息");
        }
        return "teachMaterial/stmCourseList";
    }
}
