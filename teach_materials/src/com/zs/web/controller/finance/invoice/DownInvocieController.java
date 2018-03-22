package com.zs.web.controller.finance.invoice;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.invoice.DownInvocieService;
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
@RequestMapping(value = "/downInvocie")
public class DownInvocieController extends LoggerController {
    private static Logger log = Logger.getLogger(DownInvocieController.class);

    @Resource
    private DownInvocieService downInvocieService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="state", required=false, defaultValue="") String state,
                       @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                       @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                       @RequestParam(value="year", required=false, defaultValue="") String year,
                       @RequestParam(value="quarter", required=false, defaultValue="") String quarter,
                       HttpServletRequest request){
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("studentCode", studentCode.trim());
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("state", state);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("year", year);
            params.put("quarter", quarter);
            PageInfo<StudentExpense> pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(99999999);
            String downUrl = "/excelDown/fpmx.xls";
            downInvocieService.down(pageInfo, params, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载发票明细");
            return "error";
        }
    }
}
