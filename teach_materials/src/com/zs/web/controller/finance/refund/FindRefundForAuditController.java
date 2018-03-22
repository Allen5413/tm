package com.zs.web.controller.finance.refund;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.finance.Refund;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.refund.FindRefundPageByWhereService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/4.
 */
@Controller
@RequestMapping(value = "/findRefundForAudit")
public class FindRefundForAuditController extends LoggerController {
    private static Logger log = Logger.getLogger(FindRefundForAuditController.class);

    @Resource
    private FindRefundPageByWhereService findRefundPageByWhereService;
    @Resource
    private FindSpotByProvCodeService findSpotByProvCodeService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;


    @RequestMapping(value = "find")
    public String find(@RequestParam(value="code", required=false, defaultValue="") String code,
                      @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                      @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                      @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                      HttpServletRequest request){
        try{

            Map<String, String> params = new HashMap<String, String>();
            params.put("code", code.trim());
            params.put("spotCode", spotCode);
            params.put("state", Refund.STATE_WAIT_AUDIT+"");
            params.put("beginDate", beginDate);
            params.put("endDate", endDate.trim());
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.spot_code", true);
            sortMap.put("t.create_time", false);
            pageInfo = findRefundPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            request.setAttribute("spotList", findSpotService.getAll());
            request.setAttribute("pageInfo", pageInfo);
            return "finance/refundAuditList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询需要审核的退款申请记录");
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

        return returnPath+"finance/refundList";
    }
}
