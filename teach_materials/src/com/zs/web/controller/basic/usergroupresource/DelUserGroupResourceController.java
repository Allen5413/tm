package com.zs.web.controller.basic.usergroupresource;

import com.zs.domain.basic.UserGroupResource;
import com.zs.service.basic.usergroupresource.DelUserGroupResourceService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 删除用户组
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/delUserGroupResource")
public class DelUserGroupResourceController extends
        LoggerController<UserGroupResource, DelUserGroupResourceService> {
    private static Logger log = Logger.getLogger(DelUserGroupResourceController.class);

    @Resource
    private DelUserGroupResourceService delUserGroupResourceService;

    @RequestMapping(value = "userGroupResourceDel")
    public String delUserGroupResource(@RequestParam("id") long id, HttpServletRequest request){
        try{
            delUserGroupResourceService.delUserGroupResource(id);
            return "welcome";
        } catch (Exception e){
            super.outputException(request, e, log, "删除用户组关联资源");
            return "error";
        }
    }
}
