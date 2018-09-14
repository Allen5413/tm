package com.zs.web.controller.finance.studentexpensepay;

import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensepay.AddStudentExpensePayService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2018/9/12.
 */
@Controller
@RequestMapping(value = "/refundWxStuEByCode")
public class RefundWxStuEByCodeController extends LoggerController<StudentExpensePay,AddStudentExpensePayService> {
    private static Logger log = Logger.getLogger(AddStudentExpensePayController.class);

    /**
     * 打开退款页面
     * @return
     */
    @RequestMapping(value = "open")
    public String openPage(@RequestParam(value = "code", required = false, defaultValue = "") String code,
                           HttpServletRequest request){
        return "finance/refundWx";
    }
}
