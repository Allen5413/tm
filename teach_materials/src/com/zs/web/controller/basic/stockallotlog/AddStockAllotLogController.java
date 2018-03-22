package com.zs.web.controller.basic.stockallotlog;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.StockAllotLog;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.stockallotlog.AddStockAllotLogService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/29.
 */
@Controller
@RequestMapping(value = "/addStockAllotLog")
public class AddStockAllotLogController extends
        LoggerController<StockAllotLog, AddStockAllotLogService> {
    private static Logger log = Logger.getLogger(AddStockAllotLogController.class);

    @Resource
    private AddStockAllotLogService addStockAllotLogService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    /**
     * 打开教材库存调拨页面
     * @return
     */
    @RequestMapping(value = "openAddStockAllotLog")
    public String openAddMenu(HttpServletRequest request){
        //获取所有渠道
        List<IssueChannel> issueChannelList = findIssueChannelService.getAll();
        request.setAttribute("issueChannelList", issueChannelList);
        return "teachMaterial/stockAllotLogAdd";
    }

    /**
     * 新增菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "atockAllotLogAdd")
    @ResponseBody
    public JSONObject addMenu(HttpServletRequest request, StockAllotLog stockAllotLog){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != stockAllotLog) {
                addStockAllotLogService.addStockAllotLog(stockAllotLog, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材库存调拨记录");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
