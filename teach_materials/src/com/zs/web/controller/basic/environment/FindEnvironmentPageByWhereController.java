package com.zs.web.controller.basic.environment;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Environment;
import com.zs.service.basic.environment.FindEnvironmentPageByWhereService;
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
 * Created by LihongZhang on 2015/5/27.
 */
@Controller
@RequestMapping(value = "/findEPage")
public class FindEnvironmentPageByWhereController extends LoggerController<Environment,FindEnvironmentPageByWhereService> {
    private static Logger log = Logger.getLogger(FindEnvironmentPageByWhereController.class);

    @Resource
    private FindEnvironmentPageByWhereService findEnvironmentPageByWhereService;


    @RequestMapping(value = "findEPageByWhere")
    public String findPage(@RequestParam(value = "name",required = false,defaultValue = "") String name,@RequestParam(value = "code" ,required = false,defaultValue = "") String code
    ,HttpServletRequest request){
        try {
            Map<String,String> map = new HashMap<String, String>();
            map.put("name",name);
            map.put("code",code);
            PageInfo<Environment> pageInfo = getPageInfo(request);
            Map<String,Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime",false);
            pageInfo = findEnvironmentPageByWhereService.findPageByWhere(pageInfo,map,sortMap);
            request.setAttribute("pageInfo",pageInfo);
        }catch (Exception e){
            super.outputException(request,e,log,"查询环境变量");
            return "error";
        }
        return "environment/environmentList";
    }
}
