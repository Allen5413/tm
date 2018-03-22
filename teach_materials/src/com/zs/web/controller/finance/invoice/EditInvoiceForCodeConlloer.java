package com.zs.web.controller.finance.invoice;

import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.EditInvoiceForCodeService;
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
 * Created by Allen on 2016/5/6.
 */
@Controller
@RequestMapping(value = "/editInvoiceForCode")
public class EditInvoiceForCodeConlloer extends LoggerController<Invoice, EditInvoiceForCodeService> {
    private static Logger log = Logger.getLogger(EditInvoiceForCodeConlloer.class);

    @Resource
    private EditInvoiceForCodeService editInvoiceForCodeService;


    @RequestMapping(value = "open")
    public String open(@RequestParam("id") long id,
                       HttpServletRequest request) {
        try {
            Invoice invoice = editInvoiceForCodeService.get(id);
            if(null != invoice){
                request.setAttribute("code", invoice.getCode());
                request.setAttribute("openTime", invoice.getOpenTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "finance/invoiceForCodeEdit";
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                             @RequestParam("id") long id,
                             @RequestParam(required = false, defaultValue = "", value = "code") String code,
                             @RequestParam(required = false, defaultValue = "", value = "openTime") String openTime){
        JSONObject jsonObject = new JSONObject();
        try{
            editInvoiceForCodeService.edit(id, code, openTime, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改发票号");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
