package com.zs.web.controller.basic.setteachmaterial;

import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.FindSetTeachMaterialService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/4/30.
 */
@Controller
@RequestMapping(value = "/findSTM")
public class FindSTMByIdController extends
        LoggerController<SetTeachMaterial, FindSetTeachMaterialService> {
    private static Logger log = Logger.getLogger(FindSTMByIdController.class);

    @Resource
    private FindSetTeachMaterialService findSetTeachMaterialService;

    @RequestMapping(value = "findSTMByID")
    public String findUserByID(@RequestParam("id") long id, HttpServletRequest request){
        try {
            SetTeachMaterial setTeachMaterial = findSetTeachMaterialService.get(id);
            request.setAttribute("setTeachMaterial", setTeachMaterial);
        }catch (Exception e){
            super.outputException(request, e, log, "查询套教材详细信息");
            return "error";
        }
        return "/setTeachMaterial/stm";
    }
}
