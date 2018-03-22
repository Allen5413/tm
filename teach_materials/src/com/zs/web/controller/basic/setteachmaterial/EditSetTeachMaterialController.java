package com.zs.web.controller.basic.setteachmaterial;

import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.sync.Course;
import com.zs.service.basic.setteachmaterial.AddSetTeachMaterialService;
import com.zs.service.basic.setteachmaterial.EditSetTeachMaterialService;
import com.zs.service.basic.setteachmaterial.FindSetTeachMaterialService;
import com.zs.service.sync.course.FindCourseService;
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
import java.util.List;

/**
 * Created by Allen on 2015/5/20.
 */
@Controller
@RequestMapping(value = "/editSTM")
public class EditSetTeachMaterialController extends LoggerController<SetTeachMaterial, EditSetTeachMaterialService> {
    private static Logger log = Logger.getLogger(EditSetTeachMaterialController.class);
    @Resource
    private FindSetTeachMaterialService findSetTeachMaterialService;
    @Resource
    private EditSetTeachMaterialService editSetTeachMaterialService;
    @Resource
    private FindCourseService findCourseService;

    @RequestMapping(value = "openEditSTM")
    public String openEditSetTeachMaterial(@RequestParam(value="id") long id, HttpServletRequest request){
        //获取套教材信息
        SetTeachMaterial setTeachMaterial = findSetTeachMaterialService.get(id);
        //获取课程信息
        List<Course> courseList = findCourseService.getAll();
        request.setAttribute("setTeachMaterial", setTeachMaterial);
        request.setAttribute("courseList", courseList);
        return "teachMaterial/stmEdit";
    }


    @RequestMapping(value = "stmEdit")
    @ResponseBody
    public JSONObject editSetTeachMaterial(HttpServletRequest request, SetTeachMaterial setTeachMaterial){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != setTeachMaterial) {
                editSetTeachMaterialService.editSetTeachMaterial(setTeachMaterial, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑套教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
