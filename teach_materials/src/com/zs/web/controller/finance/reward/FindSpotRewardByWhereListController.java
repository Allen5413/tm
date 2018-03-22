package com.zs.web.controller.finance.reward;

import com.zs.domain.sync.Spot;
import com.zs.service.finance.SpotFinanceTotalService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 查询中心教材奖励
 * Created by Allen on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/findSpotRewardByWhereList")
public class FindSpotRewardByWhereListController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSpotRewardByWhereListController.class);

    @Resource
    private SpotFinanceTotalService spotFinanceTotalService;
    @Resource
    private FindSpotService findSpotService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "year", required = false) Integer year,
                       @RequestParam(value = "spotCode", required = false) String spotCode,
                       @RequestParam(value = "method", required = false) String method){
        try {
            List<Spot> spotList = findSpotService.getAll();
            request.setAttribute("spotList", spotList);

            if("search".equals(method)){
                List<JSONObject> list = spotFinanceTotalService.countReward(year, spotCode);
                request.setAttribute("list", list);
            }
        }catch (Exception e){
            super.outputException(request, e, log, "奖励");
            return "error";
        }
        return "finance/countReward";
    }

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value = "year", required = false) Integer year,
                       @RequestParam(value = "spotCode", required = false) String spotCode,
                       HttpServletRequest request){
        try{

            String downUrl = "/excelDown/jcjl.xls";
            spotFinanceTotalService.downReward(year, spotCode, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载教材奖励明细");
            return "error";
        }
    }
}
