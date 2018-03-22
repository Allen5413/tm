package com.zs.web.controller.basic.press;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Press;
import com.zs.service.basic.press.FindPressPageByWhereService;
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
 * 分页查询出版社
 * Created by LihongZhang on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findPressPage")
public class FindPressPageByWhereController extends LoggerController<Press,FindPressPageByWhereService> {

    private static Logger log = Logger.getLogger(FindPressPageByWhereController.class);

    @Resource
    private FindPressPageByWhereService findPressPageByWhereService;

    @RequestMapping(value = "findPressPageByWhere")
    public String findPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="code", required=false, defaultValue="") String code,
                                  HttpServletRequest request){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            params.put("code", code.trim());
            PageInfo<Press> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findPressPageByWhereService.findPageByWhere(pageInfo,params,sortMap);
            request.setAttribute("pageInfo",pageInfo);
            return "press/pressList";
        }catch (Exception e){
           super.outputException(request,e,log,"分页查询出版社");
            return "error";
        }
    }
}
