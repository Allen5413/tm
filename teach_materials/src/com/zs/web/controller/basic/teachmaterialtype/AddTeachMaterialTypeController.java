package com.zs.web.controller.basic.teachmaterialtype;

import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.AddTeachMaterialTypeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/addTMT")
public class AddTeachMaterialTypeController extends
        LoggerController<TeachMaterialType, AddTeachMaterialTypeService> {
    private static Logger log = Logger.getLogger(AddTeachMaterialTypeController.class);

    @Resource
    private AddTeachMaterialTypeService addTeachMaterialTypeService;

    @RequestMapping(value = "openAddTMT")
    public String openAddTeachMaterialType(){
        return "teachMaterial/tmTypeAdd";
    }

    @RequestMapping(value = "tmtAdd")
    @ResponseBody
    public JSONObject addTeachMaterialType(HttpServletRequest request, TeachMaterialType teachMaterialType){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterialType) {
                addTeachMaterialTypeService.addTeachMaterialType(teachMaterialType, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材类型");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
