package com.zs.web.controller.basic.courseteachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.courseteachmaterial.DownCourseTeachMaterialService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/12/16.
 */
@Controller
@RequestMapping(value = "/downCourseTeachMaterial")
public class DownCourseTeachMaterialController extends LoggerController {
    private static Logger log = Logger.getLogger(DownCourseTeachMaterialController.class);

    @Resource
    private DownCourseTeachMaterialService downCourseTeachMaterialService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                          @RequestParam(value="code", required=false, defaultValue="") String code,
                                          @RequestParam(value="name", required=false, defaultValue="") String name,
                                          @RequestParam(value="isNotTM", required=false, defaultValue="") String isNotTM,
                                          HttpServletRequest request){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("code", code.trim());
            params.put("name", name.trim());
            params.put("isNotTM", isNotTM.trim());

            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("course_code", true);

            String downUrl = "/excelDown/kcdzb.xls";
            downCourseTeachMaterialService.down(pageInfo, params, sortMap, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询课程教材对应表");
            return "error";
        }
    }
}
