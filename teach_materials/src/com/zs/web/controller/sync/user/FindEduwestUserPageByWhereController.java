package com.zs.web.controller.sync.user;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.sync.EduwestUser;
import com.zs.service.sync.eduwestuser.FindEduwestUserPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Controller
@RequestMapping(value = "/findEduwestUserPage")
public class FindEduwestUserPageByWhereController extends
        LoggerController<EduwestUser, FindEduwestUserPageByWhereService> {

    private static Logger log = Logger.getLogger(FindEduwestUserPageByWhereController.class);

    @Resource
    public FindEduwestUserPageByWhereService findEduwestUserPageByWhereService;

    @RequestMapping(value = "findEduwestUserPageByWhere")
    public String FindPageByWhere(@RequestParam(value="pin", required=false, defaultValue="") String pin,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("pin", pin);
            params.put("name", name);
            PageInfo<EduwestUser> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findEduwestUserPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询网院管理员用户信息");
            return "error";
        }
        return "user/eduwestUserList";
    }
}
