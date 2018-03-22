package com.zs.web.controller.basic.teachmaterial;

import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/4/29.
 */
@Controller
@RequestMapping(value = "/findTeachMaterial")
public class FindTeachMaterialByIdController extends
        LoggerController<TeachMaterial, FindTeachMaterialService> {

    private static Logger log = Logger.getLogger(FindTeachMaterialByIdController.class);

    @Resource
    private FindTeachMaterialService findTeachMaterialService;

    @RequestMapping(value = "findTeachMaterialByID")
    public String findTeachMaterialByID(@RequestParam("id") long id, HttpServletRequest request){
        try {
            TeachMaterial teachMaterial = findTeachMaterialService.get(id);
            request.setAttribute("teachMaterial", teachMaterial);
            return "user/userAdd";
        }catch(Exception e){
            super.outputException(request, e, log, "查询教材详情");
            return "error";
        }
    }
}
