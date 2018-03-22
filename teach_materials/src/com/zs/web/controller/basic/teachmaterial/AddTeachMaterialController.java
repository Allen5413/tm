package com.zs.web.controller.basic.teachmaterial;

import com.zs.domain.basic.Press;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.press.FindPressService;
import com.zs.service.basic.teachmaterial.AddTeachMaterialService;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
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
@RequestMapping(value = "/addTeachMaterial")
public class AddTeachMaterialController extends
        LoggerController<TeachMaterial, AddTeachMaterialService> {

    private static Logger log = Logger.getLogger(AddTeachMaterialController.class);

    @Resource
    private AddTeachMaterialService addTeachMaterialService;
    @Resource
    private FindPressService findPressService;
    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;


    @RequestMapping(value = "openAddTeachMaterial")
    public String openAddTeachMaterial(HttpServletRequest request){
        //获取出版社信息
        List<Press> pressList = findPressService.getAll();
        //获取教材类型
        List<TeachMaterialType> teachMaterialTypeList = findTeachMaterialTypeService.getAll();

        request.setAttribute("teachMaterialTypeList", teachMaterialTypeList);
        request.setAttribute("pressList", pressList);
        return "teachMaterial/tmAdd";
    }


    @RequestMapping(value = "teachMaterialAdd")
    @ResponseBody
    public JSONObject addTeachMaterial(HttpServletRequest request, TeachMaterial teachMaterial,
                                       @RequestParam(value = "courseCode", required = false, defaultValue = "") String courseCode){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterial) {
                addTeachMaterialService.addTeachMaterial(teachMaterial, courseCode, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
