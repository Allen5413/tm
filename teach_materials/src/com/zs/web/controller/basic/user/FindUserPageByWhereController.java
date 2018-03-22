package com.zs.web.controller.basic.user;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.FindUserPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询用户信息
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/findUserPage")
public class FindUserPageByWhereController extends
        LoggerController<User, FindUserPageByWhereService> {

    private static Logger log = Logger.getLogger(FindUserPageByWhereController.class);

    @Resource
    public FindUserPageByWhereService findUserPageByWhereService;

    @RequestMapping(value = "findUserPageByWhere")
    public String FindPageByWhere(@RequestParam(value="loginName", required=false, defaultValue="") String loginName,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="state", required=false, defaultValue="") String state,
                                  @RequestParam(value="type", required=false, defaultValue="") String type,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("loginName", loginName.trim());
            params.put("name", name.trim());
            params.put("state", state);
            params.put("type", type);
            PageInfo<User> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findUserPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询用户信息");
            return "error";
        }

        switch (Integer.parseInt(StringUtils.isEmpty(type) ? "0" : type)){
            case User.TYPE_ADMIN :{
                return "user/tmAdminUserList";
            }
            case User.TYPE_ISSUE :{
                return "user/issueUserList";
            }
            case User.TYPE_SUPPLY :{
                return "user/supplyUserList";
            }
            default:{
                return "user/tmAdminUserList";
            }
        }

    }
}
