package com.zs.web.controller.basic.menu;

import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.AddMenuService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 新增菜单
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/addMenu")
public class AddMenuController extends
        LoggerController<Menu, AddMenuService> {

    private static Logger log = Logger.getLogger(AddMenuController.class);

    @Resource
    private AddMenuService addMenuService;

    /**
     * 打开新增菜单页面
     * @return
     */
    @RequestMapping(value = "openAddMenu")
    public String openAddMenu(){
        return "menu/menuAdd";
    }

    /**
     * 新增菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "menuAdd")
    @ResponseBody
    public JSONObject addMenu(HttpServletRequest request, Menu menu){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != menu) {
                addMenuService.addMenu(menu, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增菜单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
