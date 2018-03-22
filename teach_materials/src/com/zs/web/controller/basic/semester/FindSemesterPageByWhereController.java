package com.zs.web.controller.basic.semester;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 分页查询学期信息的controller
 * Created by LihongZhang on 2015/5/11.
 */
@Controller
@RequestMapping(value = "/findSemesterPage")
public class FindSemesterPageByWhereController extends LoggerController<Semester,FindSemesterPageByWhereService> {

    private static Logger log = Logger.getLogger(FindSemesterPageByWhereController.class);

    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;

    @RequestMapping(value = "findSemesterPageByWhere")
    public String findSemesterPage(HttpServletRequest request){
        try {
            PageInfo<Semester> pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(100);
            pageInfo = findSemesterPageByWhereService.findPageBywhere(pageInfo);
            request.setAttribute("pageInfo", pageInfo);
        }catch (Exception e){
            super.outputException(request, e, log, "分页查询学期信息");
            return "error";
        }
        return "semester/semesterList";
    }
}
