package com.zs.web.controller.basic.user;

import com.zs.domain.basic.User;
import com.zs.service.basic.user.EditUserPasswordService;
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
 * Created by Allen on 2015/5/25.
 */
@Controller
@RequestMapping(value = "/editPwd")
public class EditUserPasswordController extends LoggerController<User, EditUserPasswordService> {

    private static Logger log = Logger.getLogger(EditUserPasswordController.class);

    @Resource
    private EditUserPasswordService editUserPasswordService;

    /**
     * 打开修改页面
     * @return
     */
    @RequestMapping(value = "openEditUserPwdPage")
    public String openEditUserPwdPage(){
        return "user/tmAdminUserEditPwd";
    }

    /**
     * 编辑用户
     * @param request
     * @return
     */
    @RequestMapping(value = "pwdEdit")
    @ResponseBody
    public JSONObject pwdEdit(@RequestParam(value = "newPwd") String newPwd,
                              @RequestParam(value = "oldPwd") String oldPwd,
                                HttpServletRequest request, User user){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != user) {
                editUserPasswordService.editUserPassword(newPwd, UserTools.getLoginUserForLoginName(request), oldPwd);
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改密码");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}