package com.zs.web.controller.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.DownTeachMaterialForNotStockService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/13.
 */
@Controller
@RequestMapping(value = "/downTeachMaterialForNotStock")
public class DownTeachMaterialForNotStockController extends LoggerController {
    private static Logger log = Logger.getLogger(DownTeachMaterialForNotStockController.class);

    @Resource
    private DownTeachMaterialForNotStockService downTeachMaterialForNotStockService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value = "semesterId", required = false, defaultValue = "") String semesterId,
                               HttpServletRequest request){
        try{
            //获取当前学期
            Semester semester = findNowSemesterService.getNowSemester();
            List<Semester> semesterList = findNowSemesterService.getAll();
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", StringUtils.isEmpty(semesterId) ? semester.getId()+"" : semesterId);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("tttt.stockD", false);
            String downUrl = "/excelDown/kcbzjc.xls";
            downTeachMaterialForNotStockService.down(pageInfo, params, sortMap, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载库存不足教材明细");
            return "error";
        }
    }
}
