package com.zs.web.controller.basic.teachmaterialtype;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypePageByWhereService;
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
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findTMTPage")
public class FindTeachMaterialTypePageByWhereController extends
        LoggerController<TeachMaterialType, FindTeachMaterialTypePageByWhereService> {
    private static Logger log = Logger.getLogger(FindTeachMaterialTypePageByWhereController.class);

    @Resource
    public FindTeachMaterialTypePageByWhereService findTeachMaterialTypePageByWhereService;

    @RequestMapping(value = "findTMTPageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="code", required=false, defaultValue="") String code,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            params.put("code", code.trim());
            PageInfo<TeachMaterialType> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findTeachMaterialTypePageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询教材类型");
            return "error";
        }
        return "teachMaterial/tmTypeList";
    }
}
