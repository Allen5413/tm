package com.zs.web.controller.sale.studentbookorderpackage;

import com.zs.service.sale.studentbookorderpackage.EditStudentBookOrderPackageForLogisticCodeService;
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
 * Created by Allen on 2016/3/23.
 */
@Controller
@RequestMapping(value = "/editStudentBookOrderPackageForLogisticCode")
public class EditStudentBookOrderPackageForLogisticCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(EditStudentBookOrderPackageForLogisticCodeController.class);

    @Resource
    private EditStudentBookOrderPackageForLogisticCodeService editStudentBookOrderPackageForLogisticCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam("id") long id){
        try {

            request.setAttribute("studentBookOrderPackage", editStudentBookOrderPackageForLogisticCodeService.get(id));
            return "studentOrder/editStudentOrderPackageForLogisticCode";
        }catch (Exception e){
            super.outputException(request, e, log, "打开修改快递单号页面");
            return "error";
        }
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(@RequestParam(value="id") Long id,
                             @RequestParam(value="logisticCodes") String logisticCodes,
                             HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            editStudentBookOrderPackageForLogisticCodeService.edit(id, logisticCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改快递单号");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
