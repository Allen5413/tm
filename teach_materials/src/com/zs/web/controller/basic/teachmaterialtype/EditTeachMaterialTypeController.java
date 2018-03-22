package com.zs.web.controller.basic.teachmaterialtype;

import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.EditTeachMaterialTypeService;
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
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/editTMT")
public class EditTeachMaterialTypeController extends
        LoggerController<TeachMaterialType, EditTeachMaterialTypeService> {
    private static Logger log = Logger.getLogger(EditTeachMaterialTypeController.class);

    @Resource
    private EditTeachMaterialTypeService editTeachMaterialTypeService;

    @RequestMapping(value = "openEditTMTPage")
    public String openEditTeachMaterialTypePage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterialType teachMaterialType = editTeachMaterialTypeService.get(id);
        request.setAttribute("teachMaterialType", teachMaterialType);
        return "teachMaterial/tmTypeEdit";
    }

    @RequestMapping(value = "tmtEdit")
    @ResponseBody
    public JSONObject editTeachMaterialType(HttpServletRequest request, TeachMaterialType teachMaterialType){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterialType) {
                editTeachMaterialTypeService.editTeachMaterialType(teachMaterialType, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑教材类型");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
