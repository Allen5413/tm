package com.zs.web.controller.orderbook.purchaseorder;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.orderbook.purchaseorder.ManualAddPurchaseOrderService;
import com.zs.service.orderbook.purchaseorder.PurchaseOrderService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 手动添加订购单
 * Created by Allen on 2015/7/7.
 */
@Controller
@RequestMapping(value = "/addPurchaseOrder")
public class AddPurchaseOrderController extends
        LoggerController<PurchaseOrder, PurchaseOrderService> {
    private static Logger log = Logger.getLogger(AddPurchaseOrderController.class);

    @Resource
    private ManualAddPurchaseOrderService manualAddPurchaseOrderService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) {
        //获取所有的发行渠道
        List<IssueChannel> issueChannelList = findIssueChannelService.getAll();

        request.setAttribute("issueChannelList", issueChannelList);
        return "orderBook/purchaseOrderAdd";
    }

        @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("issueChannelId") Long issueChannelId,
                          @RequestParam("tmId") String[] tmIds,
                          @RequestParam("tmCount") String[] tmCounts){
        JSONObject jsonObject = new JSONObject();
        try{
            StringBuilder idAndCounts = new StringBuilder();
            for(int i=0; i<tmIds.length; i++){
                String tmId = tmIds[i];
                String count = tmCounts[i];
                idAndCounts.append(tmId).append("_").append(count).append(",");
            }
            manualAddPurchaseOrderService.addPurchaseOrderService(issueChannelId, idAndCounts.toString().substring(0, idAndCounts.length()-1), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增采购单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
