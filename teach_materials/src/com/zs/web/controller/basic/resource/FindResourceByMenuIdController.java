package com.zs.web.controller.basic.resource;

import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceByMenuIdService;
import com.zs.service.basic.resource.FindResourceService;
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
 * Created by Allen on 2015/5/18.
 */
@Controller
@RequestMapping(value = "/findResourceByMenuId")
public class FindResourceByMenuIdController extends
        LoggerController<Resource, FindResourceByMenuIdService> {

    private static Logger log = Logger.getLogger(FindResourceByMenuIdController.class);

    @javax.annotation.Resource
    private FindResourceByMenuIdService findResourceByMenuIdService;

    @RequestMapping(value = "doFindResourceByMenuId")
    @ResponseBody
    public JSONObject doFindResourceByMenuId(@RequestParam("menuId") long menuId, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            List<Resource> resourceList = findResourceByMenuIdService.getResourceByMenuIdService(menuId);
            jsonObject.put("resourceList", resourceList);
        }catch(Exception e){
            super.outputException(request, e, log, "查询菜单下的资源");
        }
        return jsonObject;
    }
}
