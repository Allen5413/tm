package com.zs.web.controller.basic.setteachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.FindSetTeachMaterialByWhereService;
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
 * Created by Allen on 2015/4/30.
 */
@Controller
@RequestMapping(value = "/findSTMPage")
public class FindSTMPageByWhereController extends LoggerController {

    private static Logger log = Logger.getLogger(FindSTMPageByWhereController.class);

    @Resource
    public FindSetTeachMaterialByWhereService findSetTeachMaterialByWhereService;

    @RequestMapping(value = "findSTMPageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("stm.operate_time", false);
            pageInfo = findSetTeachMaterialByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询套教材信息");
            return "error";
        }
        return "teachMaterial/stmList";
    }
}
