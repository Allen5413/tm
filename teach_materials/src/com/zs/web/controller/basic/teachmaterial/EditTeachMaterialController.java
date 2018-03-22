package com.zs.web.controller.basic.teachmaterial;

import com.zs.domain.basic.Press;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.domain.sync.Course;
import com.zs.service.basic.press.FindPressService;
import com.zs.service.basic.teachmaterial.EditTeachMaterialService;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
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
 * Created by Allen on 2015/4/29.
 */
@Controller
@RequestMapping(value = "/editTeachMaterial")
public class EditTeachMaterialController extends
        LoggerController<TeachMaterial, EditTeachMaterialService> {
    private static Logger log = Logger.getLogger(EditTeachMaterialController.class);

    @Resource
    private EditTeachMaterialService editTeachMaterialService;
    @Resource
    private FindPressService findPressService;
    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;

    @RequestMapping(value = "openEditTeachMaterialPage")
    public String openEditTeachMaterialPage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterial teachMaterial = editTeachMaterialService.get(id);
        //获取出版社信息
        List<Press> pressList = findPressService.getAll();
        //获取教材类型
        List<TeachMaterialType> teachMaterialTypeList = findTeachMaterialTypeService.getAll();

        request.setAttribute("teachMaterial", teachMaterial);
        request.setAttribute("pressList", pressList);
        request.setAttribute("teachMaterialTypeList", teachMaterialTypeList);
        return "teachMaterial/tmEdit";
    }

    @RequestMapping(value = "teachMaterialEdit")
    @ResponseBody
    public JSONObject editTeachMaterial(HttpServletRequest request, TeachMaterial teachMaterial){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterial) {
                editTeachMaterialService.editTeachMaterial(teachMaterial, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑教材信息");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
