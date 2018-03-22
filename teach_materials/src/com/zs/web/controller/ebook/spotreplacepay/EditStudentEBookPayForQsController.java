package com.zs.web.controller.ebook.spotreplacepay;

import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.AddSpotReplacePayService;
import com.zs.service.ebook.studentebookpay.EditStudentEbookPayForQsService;
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
 * Created by Allen on 2018/1/4.
 */
@Controller
@RequestMapping(value = "/editStudentEBookPayForQs")
public class EditStudentEBookPayForQsController extends LoggerController<SpotReplacePay, AddSpotReplacePayService> {
    private static Logger log = Logger.getLogger(EditStudentEBookPayForQsController.class);

    @Resource
    private EditStudentEbookPayForQsService editStudentEbookPayForQsService;


    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                          @RequestParam("code")String code,
                          @RequestParam(value = "hr", required = false)String hr,
                          @RequestParam(value = "errorMsg", required = false)String errorMsg,
                          @RequestParam(value = "remark", required = false)String remark,
                          @RequestParam(value = "orderCode", required = false)String orderCode)throws Exception{
        JSONObject json = new JSONObject();
        try{
            editStudentEbookPayForQsService.edit(code, hr, errorMsg, remark, orderCode);
            json.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "记录青书请求结果");
            json.put("state", 1);
            json.put("msg",msg);
        }
        return json;
    }
}