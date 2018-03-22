package com.zs.web.controller.basic.resource;

import com.zs.domain.basic.Menu;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.menu.FindMenuService;
import com.zs.service.basic.resource.AddResourceService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新增资源
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/addResource")
public class AddResourceController extends
        LoggerController<Resource, AddResourceService> {

    private static Logger log = Logger.getLogger(AddResourceController.class);

    @javax.annotation.Resource
    private AddResourceService addResourceService;
    @javax.annotation.Resource
    private FindMenuService findMenuService;

    /**
     * 打开新增资源页面
     * @return
     */
    @RequestMapping(value = "openAddResource")
    public String openAddResource(HttpServletRequest request){
        //得到所有菜单
        List<Menu> menuList = findMenuService.getAll();
        request.setAttribute("menuList", menuList);
        return "resources/resourceAdd";
    }

    /**
     * 新增资源
     * @param request
     * @return
     */
    @RequestMapping(value = "resourceAdd")
    @ResponseBody
    public JSONObject addResource(HttpServletRequest request, Resource resource){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != resource) {
                addResourceService.addResource(resource, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增资源");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
