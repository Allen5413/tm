package com.zs.web.controller.sale.oncepurchaseorder;

import com.zs.domain.sale.OncePurchaseOrder;
import com.zs.service.sale.oncepurchaseorder.EditOncePurchaseOrderStateByCodeService;
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
 * Created by Allen on 2016/6/22.
 */
@Controller
@RequestMapping(value = "/editOncePurchaseOrderStateByCode")
public class EditOncePurchaseOrderStateByCodeController extends
        LoggerController<OncePurchaseOrder, EditOncePurchaseOrderStateByCodeService> {
    private static Logger log = Logger.getLogger(EditOncePurchaseOrderStateByCodeController.class);

    @Resource
    private EditOncePurchaseOrderStateByCodeService editOncePurchaseOrderStateByCodeService;


    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                             @RequestParam("code") String code){
        JSONObject jsonObject = new JSONObject();
        try{
            editOncePurchaseOrderStateByCodeService.editor(code, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "设置为已采购");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
