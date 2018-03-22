package com.zs.web.controller.finance.studentexpense;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.StudentExpense;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.finance.studentexpense.FindStuEPageByWhereService;
import com.zs.service.sync.province.FindProvinceByCodeService;
import com.zs.service.sync.province.FindProvinceBySpotCodeService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
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
 * 分页查询学生费用的controller
 * Created by LihongZhang on 2015/5/15.
 */
@Controller
@RequestMapping(value = "/findStuEPage")
public class FindStuEPageByWhereController extends LoggerController{
    private static Logger log = Logger.getLogger(FindStuEPageByWhereController.class);

    @Resource
    private FindStuEPageByWhereService findStuEPageByWhereService;
    @Resource
    private FindProvinceByCodeService findProvinceByCodeService;
    @Resource
    private FindProvinceBySpotCodeService findProvinceBySpotCodeService;
    @Resource
    private FindSpotByProvCodeService findSpotByProvCodeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "findStuEPageByWhere")
    public String getPage(@RequestParam(value="provCode", required=false, defaultValue="") String provCode,
                          @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                          @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                          @RequestParam(value="name", required=false, defaultValue="") String name,
                          @RequestParam(value="state", required=false, defaultValue="") String state,
                          @RequestParam(value="method", required=false, defaultValue="") String method,
                          @RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                          @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                          @RequestParam(value="enterQuarter", required=false, defaultValue="") String enterQuarter,
                          HttpServletRequest request){
        try {
            String returnPath = setSearchData(request, provCode, spotCode);
            if("search".equals(method)) {
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
                JSONObject returnJSON = findStuEPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
                if(null != returnJSON) {
                    request.setAttribute("pageInfo", returnJSON.get("pageInfo"));
                    request.setAttribute("totalPayPrice", returnJSON.get("totalPayPrice"));
                    request.setAttribute("totalBuyPrice", returnJSON.get("totalBuyPrice"));
                    request.setAttribute("totalAccPrice", returnJSON.get("totalAccPrice"));
                }
            }
            return returnPath;
        }catch (Exception e){
            super.outputException(request,e,log,"分页查询学生费用记录失败");
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
    private String setSearchData(HttpServletRequest request, String provCode, String spotCode){
        String returnPath = "";
        //获取省中心数据
        List<Province> provinceList = null;
        //获取学习中心数据
        List<Spot> spotList = null;
        //查询学期数据
        List<Semester> semesterList = findNowSemesterService.getAll();


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
            spotCode = StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode;
            Province province = findProvinceBySpotCodeService.getProvinceBySpotCode(spotCode);
            if(null != province){
                provinceList = new ArrayList<Province>(1);
                provinceList.add(province);
            }
            Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
            if(null != spot) {
                spotList = new ArrayList<Spot>(1);
                spotList.add(spot);
            }
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("provinceList", provinceList);
        request.setAttribute("spotList", spotList);
        request.setAttribute("semesterList", semesterList);

        return returnPath+"finance/studentExpenseList";
    }
}
