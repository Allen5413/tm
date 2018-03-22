package com.zs.web.controller.finance.invoice;

import com.zs.config.UserTypeEnum;
import com.zs.domain.finance.Refund;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.invoice.AddInvoiceService;
import com.zs.service.finance.invoice.FindAddInvoiceStudentListService;
import com.zs.service.finance.invoice.FindTotalPayAndTotalInvoiceBySpotCodeService;
import com.zs.service.finance.refund.AddRefundService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Controller
@RequestMapping(value = "/addInvoice")
public class AddInvoiceController extends LoggerController<Refund, AddRefundService> {
    private static Logger log = Logger.getLogger(AddInvoiceController.class);

    @Resource
    private AddInvoiceService addInvoiceService;
    @Resource
    private FindAddInvoiceStudentListService findAddInvoiceStudentListService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindTotalPayAndTotalInvoiceBySpotCodeService findTotalPayAndTotalInvoiceBySpotCodeService;


    @RequestMapping(value = "open")
    public String open(@RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="specCode", required=false, defaultValue="") String specCode,
                       @RequestParam(value="levelCode", required=false, defaultValue="") String levelCode,
                       @RequestParam(value="year", required=false, defaultValue="") String year,
                       @RequestParam(value="quarter", required=false, defaultValue="") String quarter,
                       @RequestParam(value="method", required=false, defaultValue="") String method,
                       HttpServletRequest request) {

        String returnPath = setSearchData(request, "");

        List<Spec> specList = findSpecService.getAll();
        List<Level> levelList = findLevelService.getAll();
        List<Spot> spotList = findSpotService.getAll();
        if("search".equals(method)) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("studentCode", studentCode.trim());
            params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("year", year);
            params.put("quarter", quarter);
            JSONArray jsonArray = findAddInvoiceStudentListService.find(params);
            request.setAttribute("jsonArray", jsonArray);
        }
        request.setAttribute("spotList", spotList);
        request.setAttribute("specList", specList);
        request.setAttribute("levelList", levelList);

        return returnPath;
    }

    @RequestMapping(value = "findStudent")
    @ResponseBody
    public JSONObject find(@RequestParam(value="add_studentCode", required=false, defaultValue="") String studentCode,
                       @RequestParam(value="add_spotCode", required=false, defaultValue="") String spotCode,
                       @RequestParam(value="add_specCode", required=false, defaultValue="") String specCode,
                       @RequestParam(value="add_levelCode", required=false, defaultValue="") String levelCode,
                       @RequestParam(value="add_year", required=false, defaultValue="") String year,
                       @RequestParam(value="add_quarter", required=false, defaultValue="") String quarter,
                       HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        Map<String, String> params = new HashMap<String, String>();
        params.put("studentCode", studentCode.trim());
        params.put("spotCode", StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode);
        params.put("specCode", specCode);
        params.put("levelCode", levelCode);
        params.put("year", year);
        params.put("quarter", quarter);
        JSONArray jsonArray = findAddInvoiceStudentListService.find(params);
        jsonObject.put("jsonData", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "openTotal")
    public String openTotal(@RequestParam("spotCode") String spotCode,
                       HttpServletRequest request) {
        //查询该中心还能交多少钱的发票
        JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(spotCode);
        double canMoney = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString()))
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        request.setAttribute("canMoney", canMoney);
        request.setAttribute("spot", findSpotByCodeService.getSpotByCode(spotCode));
        return "finance/invoiceAddTotal";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("codeMoneys") String codeMoneys,
                          @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            addInvoiceService.add(codeMoneys, spotCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增开学生明细发票名单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "addTotal")
    @ResponseBody
    public JSONObject addTotal(HttpServletRequest request,
                          @RequestParam("spotCode") String spotCode,
                          @RequestParam("money") double money){
        JSONObject jsonObject = new JSONObject();
        try{
            addInvoiceService.addTotal(spotCode, money, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增开总票名单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
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

        return returnPath+"finance/invoiceAdd";
    }
}
