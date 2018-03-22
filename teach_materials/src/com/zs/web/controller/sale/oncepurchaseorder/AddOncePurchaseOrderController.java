package com.zs.web.controller.sale.oncepurchaseorder;

import com.zs.domain.sale.OncePurchaseOrder;
import com.zs.service.sale.oncepurchaseorder.AddOncePurchaseOrderService;
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
 * Created by Allen on 2016/6/21.
 */
@Controller
@RequestMapping(value = "/addOncePurchaseOrder")
public class AddOncePurchaseOrderController extends
        LoggerController<OncePurchaseOrder, AddOncePurchaseOrderService> {
    private static Logger log = Logger.getLogger(AddOncePurchaseOrderController.class);

    @Resource
    private AddOncePurchaseOrderService addOncePurchaseOrderService;


    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            addOncePurchaseOrderService.add(UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "创建购书单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
