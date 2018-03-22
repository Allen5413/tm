package com.zs.web.controller.orderbook.purchaseorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderListByWhereService;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderPageByWhereService;
import com.zs.tools.StringTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
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
 * 采购单管理查询
 * Created by Allen on 2015/5/5.
 */
@Controller
@RequestMapping(value = "/findPurchaseOrderPageByWhere")
public class FindPurchaseOrderPageByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPurchaseOrderPageByWhereController.class);

    @Resource
    private FindPurchaseOrderPageByWhereService findPurchaseOrderPageByWhereService;
    @Resource
    private FindIssueChannelService findIssueChannelService;
    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;
    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;


    @RequestMapping(value = "purchaseOrderPageSearch")
    public String purchaseOrderListSearch(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                         @RequestParam(value="channelId", required=false, defaultValue="") String channelId,
                                         @RequestParam(value="tmTypeId", required=false, defaultValue="") String tmTypeId,
            HttpServletRequest request){
        try{
            //当前学期
            Semester semester = findNowSemesterService.getNowSemester();
            if(StringUtils.isEmpty(semesterId)){
                semesterId = semester.getId()+"";
            }

            //获取学期数据
            List<Semester> semesterList = findSemesterPageByWhereService.getAll();
            //获取发行渠道数据
            List<IssueChannel> channelList = findIssueChannelService.getAll();
            //获取教材类别数据
            List<TeachMaterialType> tmTypeList = findTeachMaterialTypeService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("channelId", channelId);
            params.put("tmTypeId", tmTypeId);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo = findPurchaseOrderPageByWhereService.findPageByWhere(pageInfo, params, null);

            double totalPrice = 0;
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                JSONArray jsonArray = (JSONArray)pageInfo.getPageResults();
                for(int i=0; i<jsonArray.size(); i++){
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    double price = Double.parseDouble(jsonObject.get("price").toString());
                    totalPrice += price;
                }
            }

            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("semesterId", semesterId);
            request.setAttribute("semesterList", semesterList);
            request.setAttribute("channelList", channelList);
            request.setAttribute("tmTypeList", tmTypeList);
            request.setAttribute("totalPrice", StringTools.getFinancePrice(StringTools.formatNumber(totalPrice)));
            return "orderBook/purchaseOrderList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "采购单管理查询");
            return "error";
        }
    }
}
