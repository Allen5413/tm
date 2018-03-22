package com.zs.web.controller.basic.user;

import com.zs.domain.basic.User;
import com.zs.service.basic.user.FindUserService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 通过ID得到用户信息
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/findUser")
public class FindUserByIdController extends
        LoggerController<User, FindUserService> {

    private static Logger log = Logger.getLogger(FindUserByIdController.class);

    @Resource
    private FindUserService findUserService;

    @RequestMapping(value = "findUserByID")
    public String findUserByID(@RequestParam("id") long id, HttpServletRequest request){
        try {
            User user = findUserService.get(id);
            request.setAttribute("user", user);
            return "user/userAdd";
        }catch(Exception e){
            super.outputException(request, e, log, "查询用户详情");
            return "error";
        }
    }
}
