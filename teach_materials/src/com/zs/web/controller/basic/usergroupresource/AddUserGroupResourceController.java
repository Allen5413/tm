package com.zs.web.controller.basic.usergroupresource;

import com.zs.domain.basic.UserGroupResource;
import com.zs.service.basic.usergroupresource.AddUserGroupResourceService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 新增用户组组关联资源
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/addUserGroupResource")
public class AddUserGroupResourceController extends
        LoggerController<UserGroupResource, AddUserGroupResourceService> {

    private static Logger log = Logger.getLogger(AddUserGroupResourceController.class);

    @Resource
    private AddUserGroupResourceService addUserGroupResourceService;

    /**
     * 打开新增页面
     * @return
     */
    @RequestMapping(value = "openAddUserGroupResourcePage")
    public String openAddUserGroupResourcePage(){
        return "userGroup/userGroupAdd";
    }

    /**
     * 新增用户组关联资源
     * @param request
     * @return
     */
    @RequestMapping(value = "userGroupResourceAdd")
    public String addUserGroupResource(HttpServletRequest request, UserGroupResource userGroupResource){
        try{
            if(null != userGroupResource) {
                addUserGroupResourceService.addUserGroupResource(userGroupResource, UserTools.getLoginUserForName(request));
            }
            return "welcome";
        }catch(Exception e){
            super.outputException(request, e, log, "新增用户组关联资源");
            return "error";
        }
    }
}
