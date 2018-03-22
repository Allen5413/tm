package com.zs.web.controller.basic.setteachmaterial;

import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.sync.Course;
import com.zs.service.basic.setteachmaterial.AddSetTeachMaterialService;
import com.zs.service.sync.course.FindCourseService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/addSTM")
public class AddSetTeachMaterialController extends LoggerController<SetTeachMaterial, AddSetTeachMaterialService>{
        private static Logger log = Logger.getLogger(AddSetTeachMaterialController.class);

        @Resource
        private AddSetTeachMaterialService addSetTeachMaterialService;
        @Resource
        private FindCourseService findCourseService;


        @RequestMapping(value = "openAddSTM")
        public String openAddSetTeachMaterial(HttpServletRequest request){
                //获取课程信息
                List<Course> courseList = findCourseService.getAll();
                request.setAttribute("courseList", courseList);
                return "teachMaterial/stmAdd";
        }


        @RequestMapping(value = "stmAdd")
        @ResponseBody
        public JSONObject addSetTeachMaterial(HttpServletRequest request, SetTeachMaterial setTeachMaterial){
                JSONObject jsonObject = new JSONObject();
                try{
                        if(null != setTeachMaterial) {
                                addSetTeachMaterialService.addSetTeachMaterial(setTeachMaterial, UserTools.getLoginUserForName(request));
                        }
                        jsonObject.put("state", 0);
                }catch(Exception e){
                        String msg = super.outputException(request, e, log, "新增套教材");
                        jsonObject.put("state", 1);
                        jsonObject.put("msg", msg);
                }
                return jsonObject;
        }
}
