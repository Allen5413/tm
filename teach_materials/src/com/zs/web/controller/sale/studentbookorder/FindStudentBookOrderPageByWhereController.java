package com.zs.web.controller.sale.studentbookorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.dao.sync.spot.FindSpotByProvCodeDAO;
import com.zs.domain.basic.Menu;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderPageByWhereService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.province.FindProvinceByCodeService;
import com.zs.service.sync.province.FindProvinceBySpotCodeService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
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
 * Created by Allen on 2015/5/26.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderPageByWhere")
public class FindStudentBookOrderPageByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderPageByWhereController.class);

    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private FindProvinceByCodeService findProvinceByCodeService;
    @Resource
    private FindSpotByProvCodeService findSpotByProvCodeService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindStudentBookOrderPageByWhereService findStudentBookOrderPageByWhereService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "openFindStudentBookOrderPageList")
    public String openFindStudentBookOrderPageList(HttpServletRequest request){
        //设置页面查询数据
        return setSearchData(request, "");
    }


    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                                          @RequestParam(value="orderCode", required=false, defaultValue="") String orderCode,
                                          @RequestParam(value="provCode", required=false, defaultValue="") String provCode,
                                          @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                          @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                                          @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                                          @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                                          @RequestParam(value="studentName", required=false, defaultValue="") String studentName,
                                          @RequestParam(value="state", required=false, defaultValue="") String state,
                                          @RequestParam(value="studentSign", required=false, defaultValue="") String studentSign,
                                          @RequestParam(value="notSearchIsStock", required=false, defaultValue="") String notSearchIsStock,
                                          @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                                          @RequestParam(value="quarter", required=false, defaultValue="") String quarter,
                                          @RequestParam(value="tmCount") String tmCount,
                                          @RequestParam(value="isSendStudent", required=false, defaultValue="") String isSendStudent,
                                          HttpServletRequest request){
        try{

            String returnPath = setSearchData(request, "");

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("orderCode", orderCode.trim());
            params.put("provCode", StringUtils.isEmpty(provCode) ? UserTools.getLoginUserForProvCode(request) : provCode);
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("studentCode", studentCode.trim());
            params.put("studentName", studentName.trim());
            params.put("enterYear", enterYear.trim());
            params.put("quarter", quarter.trim());
            params.put("state", state);
            params.put("studentSign", studentSign);
            params.put("tmCount", tmCount);
            params.put("isSendStudent", isSendStudent);
            if(StringUtils.isEmpty(notSearchIsStock)) {
                params.put("isStock", StudentBookOrder.ISSTOCK_YES + "");
            }
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.code", true);
            pageInfo = findStudentBookOrderPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            //查询登录学习中心用户
            Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));

            request.setAttribute("method", "search");
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("spot", spot);
            return returnPath;
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询学生订单");
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
        List<Semester> semesterList = findSemesterPageByWhereService.getAll();
        //获取省中心数据
        List<Province> provinceList = null;
        //获取学习中心数据
        List<Spot> spotList = null;
        //获取专业数据
        List<Spec> specList = findSpecService.getAll();
        //获取层次数据
        List<Level> levelList = findLevelService.getAll();

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
        request.setAttribute("specList", specList);
        request.setAttribute("levelList", levelList);

        return returnPath+"studentOrder/studentOrderList";
    }
}
