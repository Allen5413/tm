package com.zs.web.controller.basic.menu;

import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.EditMenuService;
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
 * 修改菜单
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/editMenu")
public class EditMenuController extends
        LoggerController<Menu, EditMenuService> {

    private static Logger log = Logger.getLogger(EditMenuController.class);

    @Resource
    private EditMenuService editMenuService;

    /**
     * 打开编辑菜单页面
     * @return
     */
    @RequestMapping(value = "openEditMenuPage")
    public String openEditMenuPage(@RequestParam("id") long id, HttpServletRequest request){
        Menu menu = editMenuService.get(id);
        request.setAttribute("menu", menu);
        return "menu/menuEdit";
    }

    /**
     * 编辑菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "menuEdit")
    @ResponseBody
    public JSONObject editMenu(HttpServletRequest request, Menu menu){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != menu) {
                editMenuService.editMenu(menu, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑菜单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
