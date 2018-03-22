package com.zs.web.controller.finance.invoice;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.invoice.FindInvoiceByWhereService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Controller
@RequestMapping(value = "/findInvoiceByWhere")
public class FindInvoiceByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindInvoiceByWhereController.class);

    @Resource
    private FindInvoiceByWhereService findInvoiceByWhereService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;


    @RequestMapping(value = "find")
    public String find(@RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="state", required=false, defaultValue="") String state,
                       @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                       @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                       @RequestParam(value="year", required=false, defaultValue="") String year,
                       @RequestParam(value="quarter", required=false, defaultValue="") String quarter,
                       @RequestParam(value="isTotal", required=false, defaultValue="") String isTotal,
                       @RequestParam(value="openDateBegin", required=false, defaultValue="") String openDateBegin,
                       @RequestParam(value="openDateEnd", required=false, defaultValue="") String openDateEnd,
                       HttpServletRequest request){
        try{

            String returnPath = setSearchData(request, "");

            Map<String, String> params = new HashMap<String, String>();
            params.put("studentCode", studentCode.trim());
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("state", state);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("year", year);
            params.put("quarter", quarter);
            params.put("isTotal", isTotal);
            params.put("openDateBegin", openDateBegin);
            params.put("openDateEnd", openDateEnd);
            PageInfo pageInfo = getPageInfo(request);
            JSONObject resultJSON = findInvoiceByWhereService.findPageByWhere(pageInfo, params);

            request.setAttribute("pageInfo", resultJSON.get("pageInfo"));
            request.setAttribute("totalMoney", resultJSON.get("totalMoney"));
            return returnPath;
        }
        catch(Exception e){
            super.outputException(request, e, log, "分页查询退款申请记录");
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
        //获取专业数据
        List<Spec> specList = findSpecService.getAll();
        //获取层次数据
        List<Level> levelList = findLevelService.getAll();

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
        request.setAttribute("specList", specList);
        request.setAttribute("levelList", levelList);

        return returnPath+"finance/invoiceList";
    }
}
