package com.zs.web.controller.basic.user;

import com.zs.domain.basic.User;
import com.zs.service.basic.user.EditUserService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 修改用户信息
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/editUser")
public class EditUserController extends LoggerController<User, EditUserService> {

    private static Logger log = Logger.getLogger(EditUserController.class);

    @Resource
    private EditUserService editUserService;

    /**
     * 打开编辑用户页面
     * @return
     */
    @RequestMapping(value = "openEditUserPage")
    public String openEditUserPage(@RequestParam("id") long id, HttpServletRequest request){
        User user = editUserService.get(id);
        request.setAttribute("user", user);
        return "user/tmAdminUserEdit";
    }

    /**
     * 编辑用户
     * @param request
     * @return
     */
    @RequestMapping(value = "userEdit")
    @ResponseBody
    public JSONObject editUser(HttpServletRequest request, User user){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != user) {
                editUserService.editUser(user, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑用户");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
