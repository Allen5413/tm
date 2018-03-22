package com.zs.web.controller.finance.spotexpense;

import com.alibaba.fastjson.JSONObject;
import com.zs.domain.finance.SpotExpenseBuy;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.service.finance.spotexpensebuy.FindSpotExpenseBuyByCodeService;
import com.zs.service.finance.spotexpensepay.FindSpotExpensePayByCodeService;
import com.zs.web.controller.LoggerController;

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
 * Created by Allen on 2015/5/25.
 */
@Controller
@RequestMapping(value = "/findSpotExpenseDetail")
public class FindSpotExpenseDetailByCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSpotExpenseDetailByCodeController.class);

    @Resource
    private FindSpotExpensePayByCodeService findSpotExpensePayByCodeService;
    @Resource
    private FindSpotExpenseBuyByCodeService findSpotExpenseBuyByCodeService;

    @RequestMapping(value = "doFindSpotExpenseDetail")
    public String doFindStudentExpenseDetail(@RequestParam(value="code") String code, HttpServletRequest request){
        try {
            //获取学习中心的入账记录
            List<SpotExpensePay> payList = findSpotExpensePayByCodeService.getSpotExpensePayByCode(code);
            //获取学习中心的消费记录
            Map<String, Map<Double, List<SpotExpenseBuy>>> buyMap = findSpotExpenseBuyByCodeService.getSpotExpenseBuyByCode(code);

            request.setAttribute("payList", payList);
            request.setAttribute("buyMap", buyMap);
        }catch (Exception e){
            super.outputException(request,e,log,"查询学习中心费用明细失败");
            return "error";
        }
        return "finance/spotEPDetail";
    }
}
