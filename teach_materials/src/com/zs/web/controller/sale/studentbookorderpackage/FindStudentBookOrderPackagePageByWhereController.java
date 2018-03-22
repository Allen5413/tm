package com.zs.web.controller.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sync.province.FindProvinceByCodeService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/25.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderPackagePageByWhere")
public class FindStudentBookOrderPackagePageByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderPackagePageByWhereController.class);

    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private FindProvinceByCodeService findProvinceByCodeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpotByProvCodeService findSpotByProvCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        //设置页面查询数据
        return setSearchData(request, "");
    }

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                      @RequestParam(value="provCode", required=false, defaultValue="") String provCode,
                      @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                      @RequestParam(value="code", required=false, defaultValue="") String code,
                      @RequestParam(value="isSign", required=false, defaultValue="") String isSign,
                      @RequestParam(value="startDate", required=false, defaultValue="") String startDate,
                      @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                      HttpServletRequest request){
        try{
            String returnPath = setSearchData(request, "");

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", StringUtils.isEmpty(semesterId) ? findNowSemesterService.getNowSemester().getId()+"" : semesterId);
            params.put("provCode", StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode);
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("code", code.trim());
            params.put("isSign", isSign);
            params.put("beginSendDate", startDate);
            params.put("endSendDate", endDate);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("sbop.spot_code", true);
            sortMap.put("sbop.sort", false);
            pageInfo = findStudentBookOrderPackagePageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            request.setAttribute("method", "search");
            request.setAttribute("pageInfo", pageInfo);
            return returnPath;
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询教材包");
            return "error";
        }
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
        //获取学期数据
        List<Semester> semesterList = findNowSemesterService.getAll();
        //获取省中心数据
        List<Province> provinceList = null;
        //获取学习中心数据
        List<Spot> spotList = null;

        //判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
        String loginType = UserTools.getLoginUserForLoginType(request);
        if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
            provinceList = findProvinceByCodeService.getAll();
            spotList = findSpotService.getAll();
        }
        if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
            provCode = StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode;
            Province province = findProvinceByCodeService.getProvinceByCode(provCode);
            if(null != province){
                provinceList = new ArrayList<Province>(1);
                provinceList.add(province);
            }
            spotList = findSpotByProvCodeService.getSpotForListByProvCode(provCode);
            returnPath = "provPages/";
        }
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            //查询登录学习中心用户
            Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("semesterList", semesterList);
        request.setAttribute("provinceList", provinceList);
        request.setAttribute("spotList", spotList);

        return returnPath+"studentOrder/studentOrderPackageList";
    }
}
