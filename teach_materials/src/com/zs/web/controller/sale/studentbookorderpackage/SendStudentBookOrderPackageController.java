package com.zs.web.controller.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sale.studentbookorderpackage.SendStudentBookOrderPackageService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/7/27.
 */
@Controller
@RequestMapping(value = "/sendStudentBookOrderPackage")
public class SendStudentBookOrderPackageController extends LoggerController {
    private static Logger log = Logger.getLogger(SendStudentBookOrderPackageController.class);

    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private SendStudentBookOrderPackageService sendStudentBookOrderPackageService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam("semesterId") String semesterId,
                       @RequestParam("spotCode") String spotCode){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("isSend", "0");
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(999999);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("sbop.sort", true);
            pageInfo = findStudentBookOrderPackagePageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                JSONArray jsonArray = (JSONArray) pageInfo.getPageResults();
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                request.setAttribute("spotName", jsonObject.get("spotName"));
            }
            request.setAttribute("pageInfo", pageInfo);
            return "studentOrder/studentOrderPackageSend";
        }catch (Exception e){
            super.outputException(request, e, log, "分页查询教材包");
            return "error";
        }
    }

    @RequestMapping(value = "send")
    @ResponseBody
    public JSONObject purchaseOrderListSearch(@RequestParam(value="ids") Long[] ids,
                                          @RequestParam(value="logisticCodes") String[] logisticCodes,
                                          HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            sendStudentBookOrderPackageService.sendStudentBookOrderPackage(ids, logisticCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "录入物流单号");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
