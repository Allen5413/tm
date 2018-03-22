package com.zs.web.controller.finance.spotexpense;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.InsureSpotMoneyService;
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

/**
 * Created by LihongZhang on 2015/5/18.
 */
@Controller
@RequestMapping(value = "/insureSpotMoney")
public class InsureSpotMoneyController extends LoggerController<SpotExpense,InsureSpotMoneyService> {
    private static Logger log = Logger.getLogger(InsureSpotMoneyController.class);


    @Resource
    private InsureSpotMoneyService insureSpotMoneyService;


    @RequestMapping(value = "openInsureMPage")
    public String openInsurePage(@RequestParam("spotCode") String spotCode,HttpServletRequest request){
        request.setAttribute("spotCode",spotCode);
        return "finance/spotexpense/insureSpotMoney";
    }


    @RequestMapping(value = "insureMoney")
    @ResponseBody
    public JSONObject insureSpotMoney(@RequestParam("spotCode") String spotCode, @RequestParam("money") Float money,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            if (spotCode.equals("")){
                throw new BusinessException("学习中心编号为空");
            }else if (0 > money){
                throw new BusinessException("操作金额不能为负");
            }else {
                insureSpotMoneyService.insureMoney(money,spotCode, UserTools.getLoginUserForName(request));
                jsonObject.put("state",0);
            }
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"确认学习中心金额");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }

}
