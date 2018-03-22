package com.zs.web.controller.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackageForSpotCountPageService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/27.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderPackageForSpotCountPage")
public class FindStudentBookOrderPackageForSpotCountPageController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderPackageForSpotCountPageController.class);

    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindStudentBookOrderPackageForSpotCountPageService findStudentBookOrderPackageForSpotCountPageService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                          @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                          HttpServletRequest request){
        try{
            //查询所有学习中心
            List<Spot> spotList = findSpotService.getAll();
            //查询学期
            List<Semester> semesterList = findNowSemesterService.getAll();
            if(StringUtils.isEmpty(semesterId)){
                semesterId = findNowSemesterService.getNowSemester().getId()+"";
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("isSend", "0");
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("sp.code", true);
            pageInfo = findStudentBookOrderPackageForSpotCountPageService.findStudentBookOrderPackageByNotSend(pageInfo, params, sortMap);

            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("semesterId", semesterId);
            request.setAttribute("spotList", spotList);
            request.setAttribute("semesterList", semesterList);
            return "studentOrder/studentOrderPackageSendList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询教材邮寄");
            return "error";
        }
    }
}
