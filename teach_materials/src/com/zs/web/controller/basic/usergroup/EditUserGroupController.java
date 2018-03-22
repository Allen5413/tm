package com.zs.web.controller.basic.usergroup;

import com.alibaba.fastjson.JSONArray;
import com.zs.domain.basic.UserGroup;
import com.zs.service.basic.resource.FindResourceByGroupIdService;
import com.zs.service.basic.resource.FindResourceForMenuByUserGroupIdService;
import com.zs.service.basic.usergroup.EditUserGroupService;
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
import java.util.List;

/**
 * 编辑用户组
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/editUserGroup")
public class EditUserGroupController extends
        LoggerController<UserGroup, EditUserGroupService> {

    private static Logger log = Logger.getLogger(EditUserGroupController.class);

    @Resource
    private EditUserGroupService editUserGroupService;
    @Resource
    private FindResourceByGroupIdService findResourceByGroupIdService;
    @Resource
    private FindResourceForMenuByUserGroupIdService findResourceForMenuByUserGroupIdService;

    /**
     * 打开编辑用户组页面
     * @return
     */
    @RequestMapping(value = "openEditUserGroupPage")
    public String openEditUserGroupPage(@RequestParam("id") long id, HttpServletRequest request){
        UserGroup userGroup = editUserGroupService.get(id);
        //得到所有菜单信息
        JSONArray jsonArray = findResourceForMenuByUserGroupIdService.find(id);
        //得到该用户组关联的资源信息
        List<com.zs.domain.basic.Resource> resourceList = findResourceByGroupIdService.getResourceByGroupId(userGroup.getId());

        request.setAttribute("userGroup", userGroup);
        request.setAttribute("menuList", jsonArray);
        request.setAttribute("resourceList", resourceList);
        return "userGroup/userGroupEdit";
    }

    /**
     * 编辑用户组
     * @param request
     * @return
     */
    @RequestMapping(value = "userGroupEdit")
    @ResponseBody
    public JSONObject editUserGroup(@RequestParam(value="resourceIds", required=false, defaultValue="") String resourceIds,
                                    HttpServletRequest request, UserGroup userGroup){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != userGroup) {
                editUserGroupService.editUserGroup(userGroup, resourceIds, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑用户组");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
