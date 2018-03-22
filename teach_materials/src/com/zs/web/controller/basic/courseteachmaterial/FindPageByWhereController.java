package com.zs.web.controller.basic.courseteachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.courseteachmaterial.FindPageByWhereService;
import com.zs.service.basic.semester.FindNowSemesterService;
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
 * 查询课程教材对应表
 * Created by Allen on 2015/12/12 0012.
 */
@Controller
@RequestMapping(value = "/findCourseTeachMaterialPageByWhere")
public class FindPageByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPageByWhereController.class);

    @Resource
    private FindPageByWhereService findCourseTeachMaterialPageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "find")
    public String purchaseOrderListSearch(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                          @RequestParam(value="code", required=false, defaultValue="") String code,
                                          @RequestParam(value="name", required=false, defaultValue="") String name,
                                          @RequestParam(value="isNotTM", required=false, defaultValue="") String isNotTM,
                                          HttpServletRequest request){
        try{
            Semester semester = null;
            if(StringUtils.isEmpty(semesterId)){
                semester = findNowSemesterService.getNowSemester();
                semesterId = semester.getId()+"";
            }else{
                semester = findNowSemesterService.get(Long.parseLong(semesterId));
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("code", code.trim());
            params.put("name", name.trim());
            params.put("isNotTM", isNotTM.trim());

            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("course_code", true);
            pageInfo = findCourseTeachMaterialPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            request.setAttribute("semester", semester);
            request.setAttribute("pageInfo", pageInfo);
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                request.setAttribute("resultJSON", pageInfo.getPageResults().get(0));
            }
            request.setAttribute("semesterList", findNowSemesterService.getAll());
            return "course/courseTeachMaterialList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询课程教材对应表");
            return "error";
        }
    }
}
