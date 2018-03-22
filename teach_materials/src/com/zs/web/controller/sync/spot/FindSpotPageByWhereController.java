package com.zs.web.controller.sync.spot;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotPageByWhereService;
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
 * Created by Allen on 2015/5/22.
 */
@Controller
@RequestMapping(value = "/findSpotPage")
public class FindSpotPageByWhereController extends
        LoggerController<Spot, FindSpotPageByWhereService> {

    private static Logger log = Logger.getLogger(FindSpotPageByWhereController.class);

    @Resource
    public FindSpotPageByWhereService findSpotPageByWhereService;

    @RequestMapping(value = "findSpotPageByWhere")
    public String FindPageByWhere(@RequestParam(value="code", required=false, defaultValue="") String code,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  HttpServletRequest request) {
        try {
            String loginType = UserTools.getLoginUserForLoginType(request);
            String returnPath = setSearchData(loginType);
            if(loginType.equals(UserTypeEnum.SPOT.getValue())){
                code = UserTools.getLoginUserForSpotCode(request);
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("code", code);
            params.put("name", name);
            PageInfo<Spot> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("code", true);
            pageInfo = findSpotPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
            return returnPath;
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询学习中心信息");
            return "error";
        }
    }

    /**
     * 根据不同的用户类型，设置页面上的查询数据。
     * 如果是省中心用户，只能查看自己下面的学习中心的学生订单，
     * 如果是学习中心用户，只能查看自己中心的学生订单，
     * 管理员可以查所有的
     */
    private String setSearchData(String loginType){
        String returnPath = "";

        //判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
        if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
            returnPath = "provPages/";
        }
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            returnPath = "spotPages/";
        }

        return returnPath+"user/spotUserList";
    }
}
