package com.zs.web.controller.basic.resource;

import com.zs.domain.basic.Menu;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.menu.FindMenuService;
import com.zs.service.basic.resource.EditResourceService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 修改资源
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/editResource")
public class EditResourceController extends
        LoggerController<Resource, EditResourceService> {

    private static Logger log = Logger.getLogger(EditResourceController.class);

    @javax.annotation.Resource
    private EditResourceService editResourceService;
    @javax.annotation.Resource
    private FindMenuService findMenuService;

    /**
     * 打开编辑资源页面
     * @return
     */
    @RequestMapping(value = "openEditResourcePage")
    public String openEditResourcePage(@RequestParam("id") long id, HttpServletRequest request){
        Resource resource = editResourceService.get(id);
        //得到所有菜单
        List<Menu> menuList = findMenuService.getAll();

        request.setAttribute("menuList", menuList);
        request.setAttribute("resource", resource);
        return "resources/resourceEdit";
    }

    /**
     * 编辑资源
     * @param request
     * @return
     */
    @RequestMapping(value = "resourceEdit")
    @ResponseBody
    public JSONObject editResource(HttpServletRequest request, Resource resource){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != resource) {
                editResourceService.editResource(resource, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑资源");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
