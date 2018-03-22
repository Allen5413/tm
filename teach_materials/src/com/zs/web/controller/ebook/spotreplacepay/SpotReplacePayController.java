package com.zs.web.controller.ebook.spotreplacepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.service.sync.student.FindStudentListByWhereService;
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
 * Created by Allen on 2018/1/3.
 */
@Controller
@RequestMapping(value = "/spotReplacePay")
public class SpotReplacePayController extends LoggerController {
    private static Logger log = Logger.getLogger(SpotReplacePayController.class);

    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindStudentListByWhereService findStudentListByWhereService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        String returnPath = setSearchData(request, "");
        request.setAttribute("specList", findSpecService.getAll());
        request.setAttribute("levelList",findLevelService.getAll());
        return returnPath;
    }

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "spotCode", required = false, defaultValue = "")String spotCode,
                       @RequestParam(value = "year", required = false, defaultValue = "")String year,
                       @RequestParam(value = "quarter", required = false, defaultValue = "")String quarter,
                       @RequestParam(value = "specCode", required = false, defaultValue = "")String specCode,
                       @RequestParam(value = "levelCode", required = false, defaultValue = "")String levelCode,
                       @RequestParam(value = "stuCode", required = false, defaultValue = "")String stuCode,
                       @RequestParam(value = "name", required = false, defaultValue = "")String name)throws Exception{
        String returnPath = setSearchData(request, "");
        request.setAttribute("specList", findSpecService.getAll());
        request.setAttribute("levelList",findLevelService.getAll());

        Map<String,String> paramsMap = new HashMap<String,String>();
        paramsMap.put("toStuYear",year);
        paramsMap.put("toStuQuarter",quarter);
        paramsMap.put("spec",specCode);
        paramsMap.put("level",levelCode);
        paramsMap.put("stuCode",stuCode);
        paramsMap.put("stuName",name);
        paramsMap.put("spotCode",StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);

        PageInfo pagInfo = findStudentListByWhereService.findStudentList(paramsMap);
        request.setAttribute("pagInfo", pagInfo);
        return returnPath;
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
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            //查询登录学习中心用户
            Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("spotList", spotList);

        return returnPath+"ebook/spotreplacepay/findStudent";
    }
}
