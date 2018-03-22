package com.zs.web.controller.orderbook.purchaseorderteachmaterial;

import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单入库，查询订单下的教材明细
 * Created by Allen on 2015/5/12.
 */
@Controller
@RequestMapping(value = "/findPurchaseOrderTeachMaterialListByOrderCode")
public class FindPurchaseOrderTeachMaterialListByOrderCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(FindPurchaseOrderTeachMaterialListByOrderCodeController.class);

    @Resource
    private FindPurchaseOrderTeachMaterialListByOrderCodeService findPurchaseOrderTeachMaterialListByOrderCodeService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    /**
     * 查询订单明细
     * @param orderCode
     * @param channelId
     * @param semesterId
     * @param request
     * @return
     */
    @RequestMapping(value = "purchaseOrderTMByOrderCodeSearch")
    public String purchaseOrderTMByOrderCodeSearch(@RequestParam(value="orderCode") String orderCode,
                                                   @RequestParam(value="channelId") Long channelId,
                                                   @RequestParam(value="semesterId") Long semesterId,
                                                   HttpServletRequest request){
        try{
            //获取渠道信息
            IssueChannel issueChannel = findIssueChannelService.get(channelId);
            JSONArray resultJson = findPurchaseOrderTeachMaterialListByOrderCodeService.getPurchaseOrderTeachMaterialListByOrderCode(orderCode, semesterId);
            request.setAttribute("resultJson", resultJson);
            request.setAttribute("orderCode", orderCode);
            request.setAttribute("channelId", channelId);
            request.setAttribute("issueChannel", issueChannel);
            return "orderBook/purchaseOrderTMList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "订单入库，查询订单下的教材明细");
            return "error";
        }
    }
}
