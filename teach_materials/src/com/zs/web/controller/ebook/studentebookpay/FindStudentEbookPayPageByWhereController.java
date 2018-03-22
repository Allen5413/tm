package com.zs.web.controller.ebook.studentebookpay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.config.UserTypeEnum;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.ebook.studentebookpay.FindStudentEBookPayPageByWhereService;
import com.zs.service.finance.studentexpensepay.FindStuEPPageByWhereService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/7/15.
 */
@Controller
@RequestMapping(value = "/findStudentEbookPayPageByWhere")
public class FindStudentEbookPayPageByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentEbookPayPageByWhereController.class);

    @Resource
    private FindStudentEBookPayPageByWhereService findStudentEBookPayPageByWhereService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                          @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                          @RequestParam(value="name", required=false, defaultValue="") String name,
                          @RequestParam(value="payType", required=false, defaultValue="") String payType,
                          @RequestParam(value="method", required=false, defaultValue="") String method,
                          @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                          @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                          @RequestParam(value="enterYear", required=false, defaultValue="") String enterYear,
                          @RequestParam(value="enterQuarter", required=false, defaultValue="") String enterQuarter,
                          HttpServletRequest request){
        try {
            if("search".equals(method)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
                params.put("studentCode", studentCode);
                params.put("name", name);
                params.put("type", payType);
                params.put("specCode", specCode);
                params.put("levelCode", levelCode);
                params.put("year", enterYear);
                params.put("quarter", enterQuarter);
                PageInfo pageInfo = getPageInfo(request);
                JSONObject returnJSON = findStudentEBookPayPageByWhereService.findPageByWhere(pageInfo, params, null);
                if(null != returnJSON) {
                    request.setAttribute("pageInfo", returnJSON.get("pageInfo"));
                    request.setAttribute("totalPayPrice", returnJSON.get("totalPayPrice"));
                }
            }
            return this.setSearchData(request, spotCode);
        }catch (Exception e){
            super.outputException(request,e,log,"分页查询学生电子教材交费记录失败");
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
    private String setSearchData(HttpServletRequest request, String spotCode){
        String returnPath = "";

        List<Spot> spotList = null;
        List<Spec> specList = null;
        List<Level> levelList = null;

        specList = findSpecService.getAll();
        levelList = findLevelService.getAll();

        //判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
        String loginType = UserTools.getLoginUserForLoginType(request);
        if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
            spotList = findSpotService.getAll();
        }
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            spotCode = StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode;
            Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
            if(null != spot) {
                spotList = new ArrayList<Spot>(1);
                spotList.add(spot);
            }
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("spotList", spotList);
        request.setAttribute("specList", specList);
        request.setAttribute("levelList", levelList);

        return returnPath+"ebook/studentebookpay/studentEbookPayList";
    }
}
