package com.zs.web.controller.basic.teachmaterialtype;

import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findTMT")
public class FindTeachMaterialTypeByIdController extends
        LoggerController<TeachMaterialType, FindTeachMaterialTypeService> {
    private static Logger log = Logger.getLogger(FindTeachMaterialTypeByIdController.class);

    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;

    @RequestMapping(value = "findTMTByID")
    public String findTMTByID(@RequestParam("id") long id, HttpServletRequest request){
        try{
            TeachMaterialType teachMaterialType = findTeachMaterialTypeService.get(id);
            request.setAttribute("teachMaterialType", teachMaterialType);
        }catch(Exception e){
            super.outputException(request, e, log, "查询教材类型详情");
        }
        return "user/userAdd";
    }
}
