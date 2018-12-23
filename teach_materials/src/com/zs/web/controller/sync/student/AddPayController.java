package com.zs.web.controller.sync.student;

import com.zs.domain.sync.Student;
import com.zs.service.bank.paylog.AddPayLogService;
import com.zs.service.sync.student.FindStudentByCodeService;
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
 * Created by Allen on 2015/10/27.
 */
@Controller
@RequestMapping(value = "/studentAddPay")
public class AddPayController extends
        LoggerController<Student, FindStudentByCodeService> {

    private static Logger log = Logger.getLogger(AddPayController.class);

    @Resource
    private AddPayLogService addPayLogService;

    @RequestMapping(value = "pay")
    @ResponseBody
    public JSONObject confirmPay(@RequestParam("money") String money,
                                 @RequestParam("payUserCode") String payUserCode,
                                 @RequestParam("payUserType") int payUserType,
                                 HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("state", 0);
            jsonObject.put("html", addPayLogService.add(money, payUserCode, payUserType, request));
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "记录支付操作");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
