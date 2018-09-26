package com.zs.web.controller.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.service.basic.teachmaterial.DownTeachMaterialService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/8/13.
 */
@Controller
@RequestMapping(value = "/downTeachMaterial")
public class DownTeachMaterialController extends LoggerController {
    private static Logger log = Logger.getLogger(DownTeachMaterialController.class);

    @Resource
    private DownTeachMaterialService downTeachMaterialService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="isbn", required=false, defaultValue="") String isbn,
                       @RequestParam(value="name", required=false, defaultValue="") String name,
                       @RequestParam(value="typeId", required=false, defaultValue="") String typeId,
                       @RequestParam(value="pressId", required=false, defaultValue="") String pressId,
                       @RequestParam(value="state", required=false, defaultValue="") String state,
                       @RequestParam(value="courseCode", required=false, defaultValue="") String courseCode,
                       @RequestParam(value = "isGlCourse", required = false, defaultValue = "") String isGlCourse,
                               HttpServletRequest request){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("isbn", isbn.trim());
            params.put("name", name.trim());
            params.put("typeId", typeId);
            params.put("pressId", pressId);
            params.put("state", state);
            params.put("courseCode", courseCode);
            params.put("isGlCourse", isGlCourse);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("t.operate_time", false);
            String downUrl = "/excelDown/jc.xls";
            downTeachMaterialService.down(pageInfo, params, sortMap, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载教材明细");
            return "error";
        }
    }
}
