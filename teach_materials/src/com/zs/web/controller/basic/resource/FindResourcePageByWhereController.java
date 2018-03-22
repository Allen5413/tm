package com.zs.web.controller.basic.resource;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.web.AbstractJQueryEntityController;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourcePageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分页查询资源信息
 * Created by Allen on 2015/4/27.
 */
@Controller
@RequestMapping(value = "/findResourcePage")
public class FindResourcePageByWhereController extends
        LoggerController<Resource, FindResourcePageByWhereService> {

    private static Logger log = Logger.getLogger(FindResourcePageByWhereController.class);

    @javax.annotation.Resource
    public FindResourcePageByWhereService findResourcePageByWhereService;

    @RequestMapping(value = "findResourcePageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="menuId", required=false, defaultValue = "") String menuId,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            params.put("menuId", menuId.trim());
            PageInfo<Resource> pageInfo = super.getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findResourcePageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询资源信息");
            return "error";
        }
        return "/resources/resourceList";
    }
}


