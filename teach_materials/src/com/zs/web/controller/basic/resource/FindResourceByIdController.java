package com.zs.web.controller.basic.resource;

import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过ID查询资源信息
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/findResource")
public class FindResourceByIdController extends
        LoggerController<Resource, FindResourceService> {

    private static Logger log = Logger.getLogger(FindResourceByIdController.class);

    @javax.annotation.Resource
    private FindResourceService findResourceService;

    @RequestMapping(value = "findResourceByID")
    public String findResourceByID(@RequestParam("id") long id, HttpServletRequest request){
        try {
            Resource resource = findResourceService.get(id);
            request.setAttribute("resource", resource);
            return "user/userAdd";
        }catch(Exception e){
            super.outputException(request, e, log, "查询资源详情");
            return "error";
        }
    }
}
