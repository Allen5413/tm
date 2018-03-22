package com.zs.web.controller.finance.studentexpensepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.service.finance.studentexpensepay.DownStuEPService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2016/5/5.
 */
@Controller
@RequestMapping(value = "/downStuEP")
public class DownStuEPController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStuEPController.class);

    @Resource
    private DownStuEPService downStuEPService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="name", required=false, defaultValue="") String name,
                       @RequestParam(value="payType", required=false, defaultValue="") String payType,
                       @RequestParam(value="method", required=false, defaultValue="") String method,
                       @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                       @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                       @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                       @RequestParam(value="enterQuarter", required=false, defaultValue="") String enterQuarter,
                       @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                       @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                       HttpServletRequest request){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("studentCode", studentCode);
            params.put("name", name);
            params.put("payType", payType);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("enterYear", enterYear);
            params.put("enterQuarter", enterQuarter);
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(99999999);
            String downUrl = "/excelDown/jfmx2.xls";
            downStuEPService.down(pageInfo, params, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学生缴费详情");
            return "error";
        }
    }
}
