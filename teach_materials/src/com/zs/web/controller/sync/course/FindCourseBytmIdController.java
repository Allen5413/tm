package com.zs.web.controller.sync.course;

import com.zs.domain.sync.Course;
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
 * Created by Allen on 2015/5/19.
 */
@Controller
@RequestMapping(value = "/findCourseBytmId")
public class FindCourseBytmIdController extends
        LoggerController<Course, FindCourseBytmIdService> {
    private static Logger log = Logger.getLogger(FindCourseBytmIdController.class);

    @Resource
    private FindCourseBytmIdService findCourseBytmIdService;

    @RequestMapping(value = "doFindCourseBytmId")
    public String doFindCourseBytmId(@RequestParam(value = "tmId") Long tmId,
                                     @RequestParam(value="code", required=false, defaultValue="") String code,
                                     @RequestParam(value="name", required=false, defaultValue="") String name,
                                     HttpServletRequest request){
        try{
            //获得教材关联的课程
            List<Course> tmCourseList = findCourseBytmIdService.getCourseBytmId(tmId);
            //获取所有课程
            List<Course> courseList = findCourseBytmIdService.getAll();

            request.setAttribute("tmCourseList", tmCourseList);
            request.setAttribute("courseList", courseList);
        }catch(Exception e){
            super.outputException(request, e, log, "查询教材关联的课程信息");
        }
        return "teachMaterial/tmCourseList";
    }
}
