package com.zs.web.controller.sync.speccourse;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.service.sync.spec.FindSpecCoursePageService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/findSpecCoursePage")
public class FindSpecCoursePageController extends LoggerController {

    private static Logger log = Logger.getLogger(FindSpecCoursePageController.class);

    @Resource
    public FindSpecCoursePageService findSpecCoursePageService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request) {
        try {

            PageInfo pageInfo = getPageInfo(request);
            pageInfo = findSpecCoursePageService.findPageByWhere(pageInfo);
            request.setAttribute("pageInfo", pageInfo);
            return "specCourse/specCourseList";
        } catch (Exception e) {
            super.outputException(request, e, log, "查询专业课程教材对照表");
            return "error";
        }
    }
}
