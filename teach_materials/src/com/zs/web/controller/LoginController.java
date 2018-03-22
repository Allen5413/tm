package com.zs.web.controller;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.UserTypeEnum;
import com.zs.dao.basic.usergroupresource.FindUserGroupResourceByGroupIdDAO;
import com.zs.domain.basic.Menu;
import com.zs.domain.basic.User;
import com.zs.domain.basic.UserGroup;
import com.zs.domain.basic.UserGroupResource;
import com.zs.domain.sync.EduwestUser;
import com.zs.domain.sync.Student;
import com.zs.service.basic.menu.FindMenuService;
import com.zs.service.basic.resource.FindResourceService;
import com.zs.service.basic.user.ValidateLoginService;
import com.zs.service.basic.usergroup.FindUserGroupByUserNameService;
import com.zs.service.sync.eduwestuser.FindEduwestUserByPinService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.DesTools;
import com.zs.tools.HttpRequestTools;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 登录Controller
 * Created by Allen on 2015/4/25.
 */
@Controller
@RequestMapping(value = "/loginUser")
public class LoginController extends LoggerController<User, ValidateLoginService> {
    private static Logger log = Logger.getLogger(LoginController.class);

    @Resource
    private ValidateLoginService validateLoginService;
    @Resource
    private FindEduwestUserByPinService findEduwestUserByPinService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindUserGroupByUserNameService findUserGroupByUserNameService;
    @Resource
    private FindMenuService findMenuService;
    @Resource
    private FindResourceService findResourceService;
    @Resource
    private FindUserGroupResourceByGroupIdDAO findUserGroupResourceByGroupIdDAO;

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public JSONObject login(@RequestParam(value="loginName") String loginName,
                        @RequestParam(value="pwd", required = false, defaultValue = "") String pwd,
                        @RequestParam(value="type", required = false, defaultValue = "0") String type,
            HttpServletRequest request, HttpServletResponse response){
        String msg = "";
        JSONObject jsonObject = new JSONObject();
        try{
            if(UserTypeEnum.EDUWESTADMIN.getValue().equals(type)){
                msg = loginEduwestUser(request, loginName);
            }
            else if(UserTypeEnum.STUDENT.getValue().equals(type)){
                msg = loginStudentUser(request, loginName);
            }
            else{
                msg = loginUser(request, loginName, pwd);
            }
        }catch(Exception e){
            msg = super.outputException(request, e, log, "用户登录");
        }finally {
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 网院用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "eduLogin")
    public String eduLogin(@RequestParam(value="loginName") String loginName,
                           @RequestParam(value="pwd") String pwd,
                           @RequestParam(value="isValid", required = false, defaultValue = "") String isValid,
                            HttpServletRequest request,
                            HttpServletResponse response){
        String msg = "";
        try{
            //验证是否通过
            if(!"NotpwD".equals(isValid) && !loginName.equals(DesTools.decrypt(pwd, "eduwest"))){
                throw new BusinessException("用户验证失败");
            }
            msg = loginEduwestUser(request, loginName);
            if("success".equals(msg)) {
                response.sendRedirect(request.getContextPath()+"/index/main.htm");
            }
        }catch(Exception e){
            super.outputException(request, e, log, "用户登录");
            return "error";
        }
        return "index";
    }

    /**
     * 网院学生登录
     * @param request
     * @return
     */
    @RequestMapping(value = "stuLogin")
    public String stuLogin(@RequestParam(value="loginName") String loginName,
                           @RequestParam(value="pwd") String pwd,
                           @RequestParam(value="isValid", required = false, defaultValue = "") String isValid,
                           HttpServletRequest request,
                           HttpServletResponse response){
        String msg = "";
        try{
            //验证是否通过
            if(!"NotpwD".equals(isValid) && !loginName.equals(DesTools.decrypt(pwd, "eduwest"))){
                throw new BusinessException("用户验证失败");
            }
            msg = loginStudentUser(request, loginName);
            if("success".equals(msg)) {
                response.sendRedirect(request.getContextPath()+"/findStudentInfo/find.htm");
            }
        }catch(Exception e){
            super.outputException(request, e, log, "用户登录");
            return "error";
        }
        return "index";
    }

    protected String loginUser(HttpServletRequest request, String loginName, String pwd)throws Exception{
        User user = validateLoginService.validateLogin(loginName, pwd);
        if(null != user && !StringUtils.isEmpty(user.getLoginName())){
            String loginType = "";
            if(user.getType() == User.TYPE_ADMIN){
                loginType = UserTypeEnum.ADMIN.getValue();
            }
            if(user.getType() == User.TYPE_ISSUE){
                loginType = UserTypeEnum.ISSUE.getValue();
            }
            if(user.getType() == User.TYPE_SUPPLY){
                loginType = UserTypeEnum.SUPPLY.getValue();
            }
            return setSession(request, user.getLoginName(), user.getName(), loginType, null, null);
        }else {
            return "用户名密码错误";
        }
    }

    protected String loginEduwestUser(HttpServletRequest request, String pin)throws Exception{
        EduwestUser eduwestUser = findEduwestUserByPinService.getEduwestUserByPin(pin);
        if(null != eduwestUser && !StringUtils.isEmpty(eduwestUser.getName())){
            String loginType = "";
            switch (eduwestUser.getType()){
                case EduwestUser.TYPE_PROVINCE:
                    loginType = UserTypeEnum.PROVINCE.getValue();
                    break;
                case EduwestUser.TYPE_SPOT:
                    loginType = UserTypeEnum.SPOT.getValue();
                    break;
                default:
                    loginType = UserTypeEnum.EDUWESTADMIN.getValue();
                    break;
            }
            return setSession(request, eduwestUser.getPin(), eduwestUser.getName(), loginType, eduwestUser.getProvCode(), eduwestUser.getSpotCode());
        }else {
            throw new BusinessException(pin+"，不存在！");
        }
    }

    protected String loginStudentUser(HttpServletRequest request, String code)throws Exception{
        Student student = findStudentByCodeService.getStudentByCode(code);
        if(null != student && !StringUtils.isEmpty(student.getName())){
            return setSession(request, student.getCode(), student.getName(), UserTypeEnum.STUDENT.getValue(), "", student.getSpotCode());
        }else{
            return code+",不存在";
        }
    }




    protected String setSession(HttpServletRequest request, String loginName, String name, String loginType, String provCode, String spotCode)throws Exception{
        request.getSession().setAttribute("loginName", loginName);
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("loginType", loginType);
        request.getSession().setAttribute("provCode", provCode);
        request.getSession().setAttribute("spotCode", spotCode);
        //得到用户拥有的菜单资源权限
        Map<String, List<com.zs.domain.basic.Resource>> menuMap = this.getUserMenu(loginName);
        request.getSession().setAttribute("menuMap", menuMap);
        return "success";
    }

    protected Map<String, List<com.zs.domain.basic.Resource>> getUserMenu(String userName){
        Map<String, List<com.zs.domain.basic.Resource>> menuResourceMap = new HashMap<String, List<com.zs.domain.basic.Resource>>();
        //获取用户所关联的用户组
        List<UserGroup> userGroupList = findUserGroupByUserNameService.getUserGroupByUserName(userName);
        if(null != userGroupList && 0 < userGroupList.size()) {
            for(UserGroup userGroup : userGroupList) {
                //得到用户组关联资源
                List<UserGroupResource> userGroupResourceSet = findUserGroupResourceByGroupIdDAO.getUserGroupResourceByGroupId(userGroup.getId());
                Iterator<UserGroupResource> userGroupResourceIterator = userGroupResourceSet.iterator();

                while (userGroupResourceIterator.hasNext()){
                    UserGroupResource userGroupResource = userGroupResourceIterator.next();
                    //得到资源
                    com.zs.domain.basic.Resource resource =  findResourceService.get(userGroupResource.getResourceId());
                    if(null != resource) {
                        //得到菜单
                        Menu menu = findMenuService.get(resource.getMenuId());
                        List<com.zs.domain.basic.Resource> resourceList = menuResourceMap.get(menu.getName());
                        if (null == resourceList) {
                            resourceList = new ArrayList<com.zs.domain.basic.Resource>();
                        }
                        boolean isExists = false;
                        for(com.zs.domain.basic.Resource resource1 : resourceList){
                            if(resource1.getId() == resource.getId()){
                                isExists = true;
                                break;
                            }
                        }
                        if(!isExists){
                            resourceList.add(resource);
                            menuResourceMap.put(menu.getName(), resourceList);
                        }
                    }
                }
            }
        }
        return menuResourceMap;
    }
}
