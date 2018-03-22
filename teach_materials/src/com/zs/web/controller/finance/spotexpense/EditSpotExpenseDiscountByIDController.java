package com.zs.web.controller.finance.spotexpense;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.EditSpotExpenseDiscountByIDService;
import com.zs.service.finance.spotexpense.ResetSpotExpenseForPayBySpotCodeService;
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
 * Created by Allen on 2016/1/26.
 */
@Controller
@RequestMapping(value = "/editSpotExpenseDiscountByID")
public class EditSpotExpenseDiscountByIDController extends LoggerController<SpotExpense,EditSpotExpenseDiscountByIDService> {
    private static Logger log = Logger.getLogger(EditSpotExpenseDiscountByIDController.class);

    @Resource
    private EditSpotExpenseDiscountByIDService editSpotExpenseDiscountByIDService;
    @Resource
    private ResetSpotExpenseForPayBySpotCodeService resetSpotExpenseForPayBySpotCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam("id") long id){
        SpotExpense spotExpense = editSpotExpenseDiscountByIDService.get(id);
        if(null == spotExpense){
            throw new BusinessException("没有找到相关信息");
        }
        request.setAttribute("spotExpense", spotExpense);
        return "finance/spotexpense/editDiscount";
    }

    @RequestMapping(value = "editDiscount")
    @ResponseBody
    public JSONObject editDiscount(HttpServletRequest request,
                           @RequestParam("id") long id,
                           @RequestParam( value = "discount", required = true, defaultValue = "") String discount){
        JSONObject jsonObject = new JSONObject();
        try {
            //修改当前折扣
            String spotCode = editSpotExpenseDiscountByIDService.edit(id, StringUtils.isEmpty(discount) ? 100 : Integer.parseInt(discount), UserTools.getLoginUserForName(request));
            //重新计算中心交费
            resetSpotExpenseForPayBySpotCodeService.reset(spotCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"修改折扣信息");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
