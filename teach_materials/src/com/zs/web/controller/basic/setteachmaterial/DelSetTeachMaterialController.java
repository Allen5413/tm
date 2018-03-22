package com.zs.web.controller.basic.setteachmaterial;

import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.DelSetTeachMaterialService;
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
@RequestMapping(value = "/delSetTeachMaterial")
public class DelSetTeachMaterialController extends
        LoggerController<SetTeachMaterial, DelSetTeachMaterialService> {
    private static Logger log = Logger.getLogger(DelSetTeachMaterialController.class);

    @Resource
    private DelSetTeachMaterialService delSetTeachMaterialService;

    @RequestMapping(value = "stmDel")
    @ResponseBody
    public JSONObject delSetTeachMaterial(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            delSetTeachMaterialService.delSetTeachMaterial(id);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "删除套教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
