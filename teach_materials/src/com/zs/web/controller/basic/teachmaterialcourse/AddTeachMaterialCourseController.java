package com.zs.web.controller.basic.teachmaterialcourse;

import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.service.basic.teachmaterialcourse.AddTeachMaterialCourseService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/19.
 */
@Controller
@RequestMapping(value = "/addTMCourse")
public class AddTeachMaterialCourseController extends
        LoggerController<TeachMaterialCourse, AddTeachMaterialCourseService> {
    private static Logger log = Logger.getLogger(AddTeachMaterialCourseController.class);

    @Resource
    private AddTeachMaterialCourseService addTeachMaterialCourseService;

    @RequestMapping(value = "tmCourseAdd")
    @ResponseBody
    public JSONObject addTeachMaterialCourse(@RequestParam(value="tmId") long tmId,
                                             @RequestParam(value="courseCodes", required=false, defaultValue="") String courseCodes,
                                    HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            addTeachMaterialCourseService.addTeachMaterialCourse(tmId, courseCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材课程关联");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
