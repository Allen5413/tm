package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.SetPlaceOrderStateSignByIdService;
import com.zs.tools.DateTools;
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
 * 把还没有发出的预订单，设置成已发出，并扣除其费用
 * 主要用于奥鹏中心，没有账号登录系统的，由邸老师操作的中心
 * Created by Allen on 2016/1/14 0014.
 */
@Controller
@RequestMapping(value = "/setPlaceOrderStateSignById")
public class SetPlaceOrderStateSignByIdController extends LoggerController {
    private static Logger log = Logger.getLogger(SetPlaceOrderStateSignByIdController.class);

    @Resource
    private SetPlaceOrderStateSignByIdService setPlaceOrderStateSignByIdService;

    @RequestMapping(value = "setSign")
    @ResponseBody
    public JSONObject setSign(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            setPlaceOrderStateSignByIdService.set(id, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "操作");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
