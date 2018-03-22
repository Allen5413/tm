package com.zs.web.controller.statis;

import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.statis.DownSendTeachMaterialService;
import com.zs.service.statis.FindSendTeachMaterialService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/12/1.
 */
@Controller
@RequestMapping(value = "/findSendTeachMaterial")
public class FindSendTeachMaterialController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSendTeachMaterialController.class);

    @Resource
    private FindSendTeachMaterialService findSendTeachMaterialService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private DownSendTeachMaterialService downSendTeachMaterialService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        request.setAttribute("semesterList", findNowSemesterService.getAll());
        return "statis/findSendTeachMaterial";
    }

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "semesterId", required = false, defaultValue = "") String semesterId,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       @RequestParam(value = "beginDate", required = false, defaultValue = "") String beginDate,
                       @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                       @RequestParam(value = "tmTypeId", required = false, defaultValue = "") String tmTypeId){
        try{
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("semesterId", semesterId);
            paramMap.put("name", name);
            paramMap.put("beginDate", beginDate);
            paramMap.put("endDate", endDate);
            paramMap.put("tmTypeId", tmTypeId);
            List<JSONObject> list = findSendTeachMaterialService.findListByWhere(paramMap);
            request.setAttribute("list", list);
            request.setAttribute("semesterList", findNowSemesterService.getAll());
            return "statis/findSendTeachMaterial";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计教材发出情况");
            return "error";
        }
    }

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "semesterId", required = false, defaultValue = "") String semesterId,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                             @RequestParam(value = "beginDate", required = false, defaultValue = "") String beginDate,
                             @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                             @RequestParam(value = "tmTypeId", required = false, defaultValue = "") String tmTypeId){
        try {
            String downUrl = "/excelDown/jcfcqk.xls";
            downSendTeachMaterialService.down(semesterId, name, beginDate, endDate, tmTypeId, request.getRealPath("") + downUrl);
            return downUrl;
        }catch(Exception e){
            super.outputException(request, e, log, "下载统计教材发出情况");
            return "error";
        }
    }
}
