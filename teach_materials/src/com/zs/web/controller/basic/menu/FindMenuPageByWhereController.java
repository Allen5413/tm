package com.zs.web.controller.basic.menu;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.FindMenuPageByWhereService;
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
 * 分页查询菜单信息
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/findMenuPage")
public class FindMenuPageByWhereController extends
        LoggerController<Menu, FindMenuPageByWhereService> {

    private static Logger log = Logger.getLogger(FindMenuPageByWhereController.class);

    @Resource
    public FindMenuPageByWhereService findMenuPageByWhereService;

    @RequestMapping(value = "findMenuPageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            PageInfo<Menu> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findMenuPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询菜单信息");
            return "error";
        }
        return "/menu/menuList";
    }
}


