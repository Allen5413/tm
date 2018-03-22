package com.zs.web.controller.basic.courseteachmaterial;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.service.basic.courseteachmaterial.AddCourseTeachMaterialService;
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
 * Created by Allen on 2015/12/14 0014.
 */
@Controller
@RequestMapping(value = "/addCourseTeachMaterial")
public class AddCourseTeachMaterialController extends LoggerController{

    private static Logger log = Logger.getLogger(FindPageByWhereController.class);

    @Resource
    private AddCourseTeachMaterialService addCourseTeachMaterialService;

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject purchaseOrderListSearch(@RequestParam("semesterId") long semesterId,
                                          HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            addCourseTeachMaterialService.add(semesterId, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }
        catch(Exception e){
            String msg = super.outputException(request, e, log, "设置课程教材对照表");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}