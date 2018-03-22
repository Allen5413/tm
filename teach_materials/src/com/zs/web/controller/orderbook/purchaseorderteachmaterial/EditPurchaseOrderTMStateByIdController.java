package com.zs.web.controller.orderbook.purchaseorderteachmaterial;

import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.EditPurchaseOrderTMStateByIdService;
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
 * Created by Allen on 2015/7/7.
 */
@Controller
@RequestMapping(value = "/editPurchaseOrderTMStateById")
public class EditPurchaseOrderTMStateByIdController extends
        LoggerController<PurchaseOrderTeachMaterial, EditPurchaseOrderTMStateByIdService> {
    private static Logger log = Logger.getLogger(EditPurchaseOrderTMStateByIdController.class);

    @Resource
    private EditPurchaseOrderTMStateByIdService editPurchaseOrderTMStateByIdService;

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject delMenu(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            editPurchaseOrderTMStateByIdService.editPurchaseOrderTMStateById(id, PurchaseOrderTeachMaterial.STATE_CANCEL, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "作废采购单明细");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
