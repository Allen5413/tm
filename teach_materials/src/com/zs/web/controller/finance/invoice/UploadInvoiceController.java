package com.zs.web.controller.finance.invoice;

import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.UploadInvoiceService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/5/6.
 */
@Controller
@RequestMapping(value = "/uploadInvoice")
public class UploadInvoiceController extends LoggerController<Invoice, UploadInvoiceService> {
    private static Logger log = Logger.getLogger(UploadInvoiceController.class);

    @Resource
    private UploadInvoiceService uploadInvoiceService;


    @RequestMapping(value = "open")
    public String open() {
        return "finance/invoiceUpload";
    }

    @RequestMapping(value = "invoicUpload")
    @ResponseBody
    public JSONObject applyUpload(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            uploadInvoiceService.upload(request);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "上传发票");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
