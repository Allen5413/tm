package com.zs.web.controller.placeorder.ordertm;

import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.PlaceOrderTMService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 新增预订单教材明细
 * Created by Allen on 2015/7/30.
 */
@Controller
@RequestMapping(value = "/addPlaceOrderTM")
public class AddPlaceOrderTMController extends LoggerController {
    private static Logger log = Logger.getLogger(AddPlaceOrderTMController.class);

    @Resource
    private PlaceOrderTMService placeOrderTMService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "spotCode", required = false, defaultValue = "") String spotCode) {
        if(StringUtils.isEmpty(spotCode)){
            spotCode = UserTools.getLoginUserForSpotCode(request);
        }
        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
        request.setAttribute("spot", spot);
        return "placeOrder/confirmOrderTMAdd";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("tmIds") String[] tmIds,
                          @RequestParam("tmCounts") String[] tmCounts,
                          @RequestParam("spotCode") String spotCode,
                          @RequestParam("address") String address,
                          @RequestParam("adminName") String adminName,
                          @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                          @RequestParam(value = "tel", required = false, defaultValue = "") String tel,
                          @RequestParam("postalCode") String postalCode){
        JSONObject jsonObject = new JSONObject();
        try{
            StringBuilder idAndCounts = new StringBuilder();
            for(int i=0; i<tmIds.length; i++){
                String tmId = tmIds[i];
                String count = tmCounts[i];
                idAndCounts.append(tmId).append("_").append(count).append(",");
            }
            placeOrderTMService.add(idAndCounts.toString().substring(0, idAndCounts.length() - 1), UserTools.getLoginUserForName(request), spotCode, address, adminName, phone, tel, postalCode);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
