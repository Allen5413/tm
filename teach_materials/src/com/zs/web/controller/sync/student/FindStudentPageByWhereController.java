package com.zs.web.controller.sync.student;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindStudentPageByWhereService;
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
 * Created by Allen on 2015/5/22.
 */
@Controller
@RequestMapping(value = "/findStudentPage")
public class FindStudentPageByWhereController extends
        LoggerController<Student, FindStudentPageByWhereService> {

    private static Logger log = Logger.getLogger(FindStudentPageByWhereController.class);

    @Resource
    public FindStudentPageByWhereService findStudentPageByWhereService;

    @RequestMapping(value = "findStudentPageByWhere")
    public String FindPageByWhere(@RequestParam(value="code", required=false, defaultValue="") String code,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("code", code);
            params.put("name", name);
            PageInfo<Student> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findStudentPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询学生信息");
            return "error";
        }
        return "user/studentUserList";
    }
}
