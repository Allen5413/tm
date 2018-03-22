package com.zs.web.controller.finance.spotexpense;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.AddSpotEService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 添加学习中心费用记录的controller
 * Created by LihongZhang on 2015/5/18.
 */
@Controller
@RequestMapping(value = "/addSpotExpense")
public class AddSpotEController extends LoggerController<SpotExpense,AddSpotEService> {
    private static Logger log = Logger.getLogger(AddSpotEController.class);

    @Resource
    private AddSpotEService addSpotEService;


    @RequestMapping(value = "openAddSpotEPage")
    public String openPage(){
        return "finance/spotexpense/spotEAdd";
    }

    @RequestMapping(value = "spotEAdd")
    @ResponseBody
    public JSONObject addSpotE(SpotExpense spotExpense,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != spotExpense){
                addSpotEService.addSpotE(spotExpense, UserTools.getLoginUserForName(request));
            }else {
               throw new BusinessException("添加的数据为空");
            }
            jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"添加学习中心费用记录失败");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
