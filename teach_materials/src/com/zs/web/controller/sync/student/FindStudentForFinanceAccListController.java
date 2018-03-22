package com.zs.web.controller.sync.student;

import com.alibaba.fastjson.JSONObject;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.FindStudentForFinanceAccListService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
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
 * Created by Allen on 2016/1/5 0005.
 */
@Controller
@RequestMapping(value = "/findStudentForFinanceAccList")
public class FindStudentForFinanceAccListController extends LoggerController{

    private static Logger log = Logger.getLogger(FindStudentForFinanceAccListController.class);

    @Resource
    private FindStudentForFinanceAccListService findStudentForFinanceAccListService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;

    @RequestMapping(value = "find")
    @ResponseBody
    public JSONObject find(@RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                  @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                                  @RequestParam(value="enterQuarter", required=false, defaultValue="") String enterQuarter,
                                  @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                                  @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                                  @RequestParam(value="studentCodes", required=false, defaultValue="") String studentCodes,
                                  @RequestParam(value="state", required=false, defaultValue="") String state,
                                  HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("spotCode", spotCode);
            params.put("enterYear", enterYear);
            params.put("enterQuarter", enterQuarter);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("studentCodes", studentCodes);
            params.put("state", state);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("t.code", false);
            JSONArray jsonArray = findStudentForFinanceAccListService.find(params, sortMap);
            jsonObject.put("jsonData", jsonArray);
        } catch (Exception e) {
            super.outputException(request, e, log, "查询有余额的学生信息");
        }
        return jsonObject;
    }
}
