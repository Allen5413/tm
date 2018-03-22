package com.zs.web.controller.sync.province;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.sync.Province;
import com.zs.service.sync.province.FindProvincePageByWhereService;
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
@RequestMapping(value = "/findProvincePage")
public class FindProvincePageByWhereController extends
        LoggerController<Province, FindProvincePageByWhereService> {

    private static Logger log = Logger.getLogger(FindProvincePageByWhereController.class);

    @Resource
    public FindProvincePageByWhereService findProvincePageByWhereService;

    @RequestMapping(value = "findProvincePageByWhere")
    public String FindPageByWhere(@RequestParam(value="code", required=false, defaultValue="") String code,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("code", code);
            params.put("name", name);
            PageInfo<Province> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findProvincePageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询省中心信息");
            return "error";
        }
        return "user/provinceUserList";
    }
}
