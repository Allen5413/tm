package com.zs.web.controller.basic.usergroup;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.UserGroup;
import com.zs.domain.sync.EduwestUser;
import com.zs.service.basic.usergroup.FindUserGroupByUserNameService;
import com.zs.service.sync.eduwestuser.FindEduwestUserBySpotCodeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
@Controller
@RequestMapping(value = "/findUserGroupByLoginName")
public class FindUserGroupByLoginNameController  extends
        LoggerController<UserGroup, FindUserGroupByUserNameService> {
    private static Logger log = Logger.getLogger(FindUserGroupByLoginNameController.class);

    @Resource
    private FindUserGroupByUserNameService findUserGroupByUserNameService;
    @Resource
    private FindEduwestUserBySpotCodeService findEduwestUserBySpotCodeService;

    @RequestMapping(value = "doFindUserGroupByLoginName")
    public String doFindUserGroupByLoginName(@RequestParam(value = "loginName") String loginName,
                                             @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                             HttpServletRequest request){
        try{
            String loginNames = "";
            //学习中心关联
            if("1".equals(type)){
                String code = loginName;
                //查询该中心的用户
                List<EduwestUser> eduwestUserList = findEduwestUserBySpotCodeService.getEduwestUserBySpotCode(code);
                if(null == eduwestUserList || 1 > eduwestUserList.size()){
                    throw new BusinessException("该中心下没有用户");
                }
                request.setAttribute("eduwestUserList", eduwestUserList);

                for(int i=0; i<eduwestUserList.size(); i++){
                    EduwestUser eduwestUser = eduwestUserList.get(i);
                    loginName = eduwestUser.getPin();
                    loginNames += eduwestUser.getPin() + (i==eduwestUserList.size()-1 ? "" : ",");
                }
            }else{
                loginNames = loginName;
            }

            //得到用户关联的用户组
            List<UserGroup> userGroupUserList = findUserGroupByUserNameService.getUserGroupByUserName(loginName);
            //得到所有用户组
            List<UserGroup> userGroupList = findUserGroupByUserNameService.getAll();

            request.setAttribute("userGroupUserList", userGroupUserList);
            request.setAttribute("userGroupList", userGroupList);
            request.setAttribute("loginNames", loginNames);
            request.setAttribute("type", "0".equals(type) ? "0" : "1");
        }catch(Exception e){
            super.outputException(request, e, log, "查询用户关联用户组信息");
        }
        return "user/userGroupUserList";
    }
}
