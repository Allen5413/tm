package com.zs.web.controller.basic.spotgroup;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.FindSpotGroupPageByWhereService;
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
 * Created by Allen on 2015/5/3.
 */
@Controller
@RequestMapping(value = "/findSpotGroupPage")
public class FindSpotGroupPageByWhereController extends
        LoggerController<SpotGroup, FindSpotGroupPageByWhereService> {
    private static Logger log = Logger.getLogger(FindSpotGroupPageByWhereController.class);

    @Resource
    public FindSpotGroupPageByWhereService findSpotGroupPageByWhereService;

    @RequestMapping(value = "findSpotGroupPageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            params.put("spotCode", spotCode.trim());
            PageInfo<SpotGroup> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findSpotGroupPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            if(null != pageInfo) {
                request.setAttribute("total", pageInfo.getTotalCount());
                request.setAttribute("userList", pageInfo.getPageResults());
            }
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询学习中心分组");
            return "error";
        }
        return "user";
    }
}
