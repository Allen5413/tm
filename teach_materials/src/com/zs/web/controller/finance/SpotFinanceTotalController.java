package com.zs.web.controller.finance;

import com.zs.config.UserTypeEnum;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.finance.SpotFinanceTotalService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Allen on 2015/12/7.
 */
@Controller
@RequestMapping(value = "/spotFinanceTotal")
public class SpotFinanceTotalController extends LoggerController {
    private static Logger log = Logger.getLogger(SpotFinanceTotalController.class);

    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByProvCodeService findSpotByProvCodeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private SpotFinanceTotalService spotFinanceTotalService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, HttpServletResponse response){
        try {
            if(UserTools.getLoginUserForLoginType(request).equals(UserTypeEnum.SPOT.getValue())) {
                String spotCode = UserTools.getLoginUserForSpotCode(request);
                response.sendRedirect(request.getContextPath()+"/spotFinanceTotal/find.htm?spotCode="+spotCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return setSearchData(request, "");
    }

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "spotCode", required = false, defaultValue = "") String spotCode){
        try {
            if(UserTools.getLoginUserForLoginType(request).equals(UserTypeEnum.SPOT.getValue())) {
                spotCode = UserTools.getLoginUserForSpotCode(request);
            }
            if(!StringUtils.isEmpty(spotCode)) {
                JSONObject returnJSON = spotFinanceTotalService.findSpotFinanceTotal(spotCode);
                request.setAttribute("data", returnJSON);
            }
            request.setAttribute("admin", UserTools.getLoginUserForLoginName(request));
        }catch (Exception e){
            super.outputException(request, e, log, "学习中心财务统计");
            return "error";
        }
        return setSearchData(request, "");
    }

    @RequestMapping(value = "findSpotOrderTM")
     public String findSpotOrderTM(HttpServletRequest request,
                                   @RequestParam("spotCode") String spotCode,
                                   @RequestParam("semesterId") long semesterId){
        try {
            JSONObject jsonObject = spotFinanceTotalService.findSpotOrderTMInfo(spotCode, semesterId);
            request.setAttribute("data", jsonObject.get("data"));
            request.setAttribute("sumTotalPrice", jsonObject.get("sumTotalPrice"));
        }catch (Exception e){
            super.outputException(request, e, log, "查看学习中心学期订购教材明细");
            return "error";
        }
        return "finance/findSpotOrderTM";
    }

    @RequestMapping(value = "downSpotOrderTM")
    @ResponseBody
    public String downSpotOrderTM(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("spotCode") String spotCode,
                                  @RequestParam("semesterId") long semesterId){
        try{
            Semester semester = findNowSemesterService.get(semesterId);
            String downUrl = "/excelDown/"+semester.getYear()+semester.getQuarter()+".xls";
            spotFinanceTotalService.downSpotOrderTMInfo(spotCode, semesterId, request.getRealPath("")+downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载教材订购情况");
            return "error";
        }
    }

    @RequestMapping(value = "findStudentOwnInfo")
    public String findStudentOwnInfo(HttpServletRequest request,
                                     @RequestParam("spotCode") String spotCode){
        try {
            JSONArray jsonArray = spotFinanceTotalService.findStudentOwnInfo(spotCode);
            request.setAttribute("data", jsonArray);
        }catch (Exception e){
            super.outputException(request, e, log, "查看有欠款的学生信息");
            return "error";
        }
        return "finance/studentOwnList";
    }

    @RequestMapping(value = "findStudentAccInfo")
    public String findStudentAccInfo(HttpServletRequest request,
                                  @RequestParam("spotCode") String spotCode){
        try {
            JSONArray jsonArray = spotFinanceTotalService.findStudentAccInfo(spotCode);
            request.setAttribute("data", jsonArray);
        }catch (Exception e){
            super.outputException(request, e, log, "查看有余额的学生信息");
            return "error";
        }
        return "finance/studentAccList";
    }

    /**
     * 根据不同的用户类型，设置页面上的查询数据。
     * 如果是省中心用户，只能查看自己下面的学习中心的学生订单，
     * 如果是学习中心用户，只能查看自己中心的学生订单，
     * 管理员可以查所有的
     * @param request
     */
    private String setSearchData(HttpServletRequest request, String provCode){
        String returnPath = "";
        //获取学习中心数据
        List<Spot> spotList = null;

        //判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
        String loginType = UserTools.getLoginUserForLoginType(request);
        if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
            spotList = findSpotService.getAll();
        }
        if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
            provCode = StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode;
            spotList = findSpotByProvCodeService.getSpotForListByProvCode(provCode);
            returnPath = "provPages/";
        }
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            //查询登录学习中心用户
            Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("spotList", spotList);

        return returnPath+"finance/spotFinanceTotal";
    }
}
