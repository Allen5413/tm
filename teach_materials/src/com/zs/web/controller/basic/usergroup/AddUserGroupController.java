package com.zs.web.controller.basic.usergroup;

import com.alibaba.fastjson.JSONArray;
import com.zs.domain.basic.UserGroup;
import com.zs.service.basic.resource.FindResourceForMenuService;
import com.zs.service.basic.usergroup.AddUserGroupService;
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
 * 新增用户组
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/addUserGroup")
public class AddUserGroupController extends
        LoggerController<UserGroup, AddUserGroupService> {

    private static Logger log = Logger.getLogger(AddUserGroupController.class);

    @Resource
    private AddUserGroupService addUserGroupService;
    @Resource
    private FindResourceForMenuService findResourceForMenuService;

    /**
     * 打开新增用户组页面
     * @return
     */
    @RequestMapping(value = "openAddUserGroupPage")
    public String openAddUserGroupPage(HttpServletRequest request){
        //得到所有菜单信息
        JSONArray menuList = findResourceForMenuService.find();
        request.setAttribute("menuList", menuList);
        return "userGroup/userGroupAdd";
    }

    /**
     * 新增用户组
     * @param request
     * @return
     */
    @RequestMapping(value = "userGroupAdd")
    @ResponseBody
    public JSONObject addUserGroup(@RequestParam(value="resourceIds", required=false, defaultValue="") String resourceIds,
                                   HttpServletRequest request, UserGroup userGroup){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != userGroup) {
                addUserGroupService.addUserGroup(userGroup, resourceIds, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增用户组");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
