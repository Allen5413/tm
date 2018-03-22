package com.zs.web.controller.sync.speccourse;

import com.zs.service.sync.spec.FindSpecCourseTMBySpecAndLevelService;
import com.zs.web.controller.LoggerController;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/findSpecCourseTMBySpecAndLevel")
public class FindSpecCourseTMBySpecAndLevelController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSpecCourseTMBySpecAndLevelController.class);

    @Resource
    private FindSpecCourseTMBySpecAndLevelService findSpecCourseTMBySpecAndLevelService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="specCode") String specCode,
                       @RequestParam(value="levelCode") String levelCode,
                       HttpServletRequest request){
        try{
            JSONObject resultJSON = findSpecCourseTMBySpecAndLevelService.find(specCode, levelCode);
            request.setAttribute("resultJSON", resultJSON);
            return "specCourse/specCourseTMList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询专业课程教材对照表明细");
            return "error";
        }
    }
}
