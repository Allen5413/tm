package com.zs.web.controller.basic.teachmaterial;

import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.EditTeachMaterialPriceService;
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
 * Created by Allen on 2015/5/20.
 */
@Controller
@RequestMapping(value = "/editTeachMaterialPrice")
public class EditTeachMaterialPriceController extends
        LoggerController<TeachMaterial, EditTeachMaterialPriceService> {
    private static Logger log = Logger.getLogger(EditTeachMaterialPriceController.class);

    @Resource
    private EditTeachMaterialPriceService editTeachMaterialPriceService;

    @RequestMapping(value = "openEditTeachMaterialPricePage")
    public String openEditTeachMaterialPricePage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterial teachMaterial = editTeachMaterialPriceService.get(id);
        request.setAttribute("teachMaterial", teachMaterial);
        return "teachMaterial/tmPriceEdit";
    }

    @RequestMapping(value = "teachMaterialPriceEdit")
    @ResponseBody
    public JSONObject editTeachMaterialPrice(HttpServletRequest request, TeachMaterial teachMaterial){
        JSONObject jsonObject = new JSONObject();
        try{
            editTeachMaterialPriceService.editTeachMaterialPrice(teachMaterial, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑教材价格信息");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
