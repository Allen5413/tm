package com.zs.web.controller.orderbook.purchaseorder;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderListByWhereService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 采购单管理查询
 * Created by Allen on 2015/5/5.
 */
@Controller
@RequestMapping(value = "/findPurchaseOrderListByWhere")
public class FindPurchaseOrderListByWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPurchaseOrderListByWhereController.class);

    @Resource
    private FindPurchaseOrderListByWhereService findPurchaseOrderListByWhereService;
    @Resource
    private FindIssueChannelService findIssueChannelService;
    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;
    @Resource
    private FindSemesterPageByWhereService findSemesterPageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "openFindPurchaseOrderList")
    public String openFindPurchaseOrderList(HttpServletRequest request){
        //获取学期数据
        List<Semester> semesterList = findSemesterPageByWhereService.getAll();
        //获取发行渠道数据
        List<IssueChannel> channelList = findIssueChannelService.getAll();
        //获取教材类别数据
        List<TeachMaterialType> tmTypeList = findTeachMaterialTypeService.getAll();
        request.setAttribute("semesterList", semesterList);
        request.setAttribute("channelList", channelList);
        request.setAttribute("tmTypeList", tmTypeList);
        return "orderBook/purchaseOrderList";
    }


    @RequestMapping(value = "purchaseOrderListSearch")
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

            JSONArray resultJson = findPurchaseOrderListByWhereService.getPurchaseOrderListByWhere(semesterId, channelId, tmTypeId);
            request.setAttribute("resultJson", resultJson);
            request.setAttribute("method", "search");
            request.setAttribute("semesterList", semesterList);
            request.setAttribute("channelList", channelList);
            request.setAttribute("tmTypeList", tmTypeList);
            return "orderBook/purchaseOrderList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "采购单管理查询");
            return "error";
        }
    }
}
