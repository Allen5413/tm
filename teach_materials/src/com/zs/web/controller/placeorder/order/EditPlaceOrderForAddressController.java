package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.PlaceOrderService;
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
 * Created by Allen on 2015/8/19.
 */
@Controller
@RequestMapping(value = "/editPlaceOrderForAddress")
public class EditPlaceOrderForAddressController extends LoggerController {
    private static Logger log = Logger.getLogger(EditPlaceOrderForAddressController.class);

    @Resource
    private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;

    @RequestMapping(value = "open")
    public String open(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.get(id);
        request.setAttribute("teachMaterialPlaceOrder", teachMaterialPlaceOrder);
        return "placeOrder/placeOrderAddressEdit";
    }

    @RequestMapping(value = "editAddress")
    @ResponseBody
    public JSONObject editAddress(@RequestParam("id") long id,
                                  @RequestParam("address") String address,
                                  @RequestParam("adminName") String adminName,
                                  @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                  @RequestParam(value = "tel", required = false, defaultValue = "") String tel,
                                  @RequestParam("postalCode") String postalCode,
                                  HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.get(id);
            if(null == teachMaterialPlaceOrder){
                throw new BusinessException("没有找到订单信息");
            }
            teachMaterialPlaceOrder.setAddress(address);
            teachMaterialPlaceOrder.setAdminName(adminName);
            teachMaterialPlaceOrder.setPhone(phone);
            teachMaterialPlaceOrder.setTel(tel);
            teachMaterialPlaceOrder.setPostalCode(postalCode);
            teachMaterialPlaceOrder.setOperator(UserTools.getLoginUserForName(request));
            teachMaterialPlaceOrder.setOperator(UserTools.getLoginUserForName(request));
            teachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
            teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "修改订单邮寄地址");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
