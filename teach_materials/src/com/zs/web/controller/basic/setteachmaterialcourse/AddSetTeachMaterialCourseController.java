package com.zs.web.controller.basic.setteachmaterialcourse;

import com.zs.domain.basic.SetTeachMaterialCourse;
import com.zs.service.basic.setteachmaterialcourse.AddSetTeachMaterialCourseService;
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
 * Created by Allen on 2015/5/20.
 */
@Controller
@RequestMapping(value = "/addSTMCourse")
public class AddSetTeachMaterialCourseController  extends
        LoggerController<SetTeachMaterialCourse, AddSetTeachMaterialCourseService> {
    private static Logger log = Logger.getLogger(AddSetTeachMaterialCourseController.class);

    @Resource
    private AddSetTeachMaterialCourseService addSetTeachMaterialCourseService;

    @RequestMapping(value = "stmCourseAdd")
    @ResponseBody
    public JSONObject addSetTeachMaterialCourse(@RequestParam(value = "stmId") long stmId,
                                                @RequestParam(value = "courseCodes", required = false, defaultValue = "") String courseCodes,
                                                HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            addSetTeachMaterialCourseService.addSetTeachMaterialCourse(stmId, courseCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "新增套教材课程关联");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
