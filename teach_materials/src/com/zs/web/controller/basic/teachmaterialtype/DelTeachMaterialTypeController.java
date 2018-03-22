package com.zs.web.controller.basic.teachmaterialtype;

import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.DelTeachMaterialTypeService;
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
@RequestMapping(value = "/delTMT")
public class DelTeachMaterialTypeController extends
        LoggerController<TeachMaterialType, DelTeachMaterialTypeService> {
    private static Logger log = Logger.getLogger(DelTeachMaterialTypeController.class);

    @Resource
    private DelTeachMaterialTypeService delTeachMaterialTypeService;

    @RequestMapping(value = "tmtDel")
    @ResponseBody
    public JSONObject delTeachMaterialType(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            delTeachMaterialTypeService.delTeachMaterialType(id);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "删除教材类型");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
