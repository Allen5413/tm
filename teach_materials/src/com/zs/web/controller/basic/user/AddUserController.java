package com.zs.web.controller.basic.user;

import com.zs.domain.basic.User;
import com.zs.service.basic.user.AddUserService;
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
 * 新增用户
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/addUser")
public class AddUserController extends
        LoggerController<User, AddUserService> {
    private static Logger log = Logger.getLogger(AddUserController.class);

    @Resource
    private AddUserService addUserService;

    /**
     * 打开新增用户页面
     * @return
     */
    @RequestMapping(value = "openAddUserPage")
    public String openAddUserPage(){
        return "user/tmAdminUserAdd";
    }

    /**
     * 新增用户
     * @param request
     * @return
     */
    @RequestMapping(value = "userAdd")
    @ResponseBody
    public JSONObject addUser(HttpServletRequest request, User user){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != user) {
                addUserService.addUser(user, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }
        catch(Exception e){
            String msg = super.outputException(request, e, log, "新增用户");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
