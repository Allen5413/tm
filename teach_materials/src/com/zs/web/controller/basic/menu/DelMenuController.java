package com.zs.web.controller.basic.menu;

import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.DelMenuService;
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
 * 删除菜单
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/delMenu")
public class DelMenuController extends
        LoggerController<Menu, DelMenuService> {
    private static Logger log = Logger.getLogger(DelMenuController.class);

    @Resource
    private DelMenuService delMenuService;

    @RequestMapping(value = "menuDel")
    @ResponseBody
    public JSONObject delMenu(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            delMenuService.delMenu(id);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "删除菜单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
