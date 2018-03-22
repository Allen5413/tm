package com.zs.web.controller.basic.menu;

import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.FindMenuService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 通过ID查询菜单信息
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/findMenu")
public class FindMenuByIdController extends
        LoggerController<Menu, FindMenuService> {

    private static Logger log = Logger.getLogger(FindMenuByIdController.class);

    @Resource
    private FindMenuService findMenuService;

    @RequestMapping(value = "findMenuByID")
    public String findMenuByID(@RequestParam("id") long id, HttpServletRequest request){
        try{
            Menu menu = findMenuService.get(id);
            request.setAttribute("menu", menu);
        }catch(Exception e){
            super.outputException(request, e, log, "查询菜单详情");
        }
        return "user/userAdd";
    }
}
