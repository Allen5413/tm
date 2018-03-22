package com.zs.web.controller.sale.onceorder;

import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spot;
import com.zs.service.sale.onceorder.CountOnceOrderForConfirmService;
import com.zs.service.sale.onceorder.CountOnceOrderForConfirmStudentService;
import com.zs.service.sync.province.FindProvinceService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
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
 * Created by Allen on 2015/9/4.
 */
@Controller
@RequestMapping(value = "/countOnceOrderForConfirm")
public class CountOnceOrderForConfirmController extends LoggerController{

    private static Logger log = Logger.getLogger(CountOnceOrderForConfirmController.class);

    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindProvinceService findProvinceService;
    @Resource
    private CountOnceOrderForConfirmService countOnceOrderForConfirmService;
    @Resource
    private CountOnceOrderForConfirmStudentService countOnceOrderForConfirmStudentService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
        @RequestParam(value="provCode", required=false, defaultValue="") String provCode,
        @RequestParam(value="method", required=false, defaultValue="") String method,
        HttpServletRequest request){
        try{
            List<Province> provinceList = findProvinceService.getAll();
            List<Spot> spotList = findSpotService.getAll();

            if("search".equals(method)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("provCode", provCode);
                params.put("spotCode", spotCode.trim());
                JSONObject json = countOnceOrderForConfirmService.find(params);

                request.setAttribute("resultData", json.get("result"));
                request.setAttribute("sumTotalCount", json.get("sumTotalCount"));
                request.setAttribute("sumNotCount", json.get("sumNotCount"));
                request.setAttribute("sumCount", json.get("sumCount"));
                request.setAttribute("sumTotalPrice", json.get("sumTotalPrice"));
                request.setAttribute("sumPrice", json.get("sumPrice"));
                request.setAttribute("sumTotalCountPercent", json.get("sumTotalCountPercent"));
                request.setAttribute("sumTotalPricePercent", json.get("sumTotalPricePercent"));
            }

            request.setAttribute("provinceList", provinceList);
            request.setAttribute("spotList", spotList);
            return "onceOrder/countOnceOrderForConfirm";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计订单确认情况");
            return "error";
        }
    }

    @RequestMapping(value = "findDetail")
    public String findDetail(@RequestParam("spotCode") String spotCode,
                             @RequestParam("flag") int flag,
                       HttpServletRequest request){
        try{
            JSONObject json = countOnceOrderForConfirmStudentService.find(spotCode, flag);
            request.setAttribute("resultData", json.get("result"));
            request.setAttribute("sumTotalPrice", json.get("sumTotalPrice"));
            request.setAttribute("sumPrice", json.get("sumPrice"));
            request.setAttribute("sumTotalPricePercent", json.get("sumTotalPricePercent"));

            return "onceOrder/countOnceOrderForConfirmStudent";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计订单确认学生明细");
            return "error";
        }
    }
}
