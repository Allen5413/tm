package com.zs.web.controller.finance.studentexpense;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.studentexpense.DownStuEService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/10/14.
 */
@Controller
@RequestMapping(value = "/downStuE")
public class DownStuEController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStuEController.class);

    @Resource
    private DownStuEService downStuEService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="provCode", required=false, defaultValue="") String provCode,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="name", required=false, defaultValue="") String name,
                       @RequestParam(value="state", required=false, defaultValue="") String state,
                       @RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                       @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                       @RequestParam(value="enterQuarter", required=false, defaultValue="") String enterQuarter,
                       HttpServletRequest request,
                       HttpServletResponse response){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("provCode", StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode);
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("studentCode", studentCode);
            params.put("name", name);
            params.put("state", state);
            params.put("semesterId", semesterId);
            params.put("enterYear", enterYear);
            params.put("enterQuarter", enterQuarter);
            PageInfo<StudentExpense> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("s.code", false);
            pageInfo.setCountOfCurrentPage(99999999);
            String downUrl = "/excelDown/student.xls";
            downStuEService.down(pageInfo, params, sortMap, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学生费用明细");
            return "error";
        }
    }
}
