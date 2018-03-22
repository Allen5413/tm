package com.zs.web.controller.basic.teachmaterialrevision;

import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.EditTeachMaterialRevisionService;
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
 * Created by Allen on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/editTMRevisionForRevision")
public class EditTeachMaterialRevisionForRevisionController extends
        LoggerController<TeachMaterialRevision, EditTeachMaterialRevisionService> {
    private static Logger log = Logger.getLogger(EditTeachMaterialRevisionForRevisionController.class);

    @Resource
    private EditTeachMaterialRevisionService editTeachMaterialRevisionForRevisionService;

    @RequestMapping(value = "openTMRevisionForRevisionPage")
    public String openTMRevisionForRevisionPage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterialRevision teachMaterialRevision = editTeachMaterialRevisionForRevisionService.get(id);
        request.setAttribute("tmr", teachMaterialRevision);
        return "teachMaterial/tmRevisionNameEdit";
    }

    @RequestMapping(value = "tmRevisionForRevisionEdit")
    @ResponseBody
    public JSONObject editTMRevisionForRevision(HttpServletRequest request, TeachMaterialRevision teachMaterialRevision){
        JSONObject jsonObject = new JSONObject();
        try{
            editTeachMaterialRevisionForRevisionService.editTeachMaterialRevision(teachMaterialRevision, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑教材版次信息");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
