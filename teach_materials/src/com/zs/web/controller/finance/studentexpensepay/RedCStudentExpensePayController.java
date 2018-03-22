package com.zs.web.controller.finance.studentexpensepay;

import com.zs.domain.finance.StudentExpense;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensepay.FindStudentExpensePayByCodeService;
import com.zs.service.finance.studentexpensepay.RedCStudentExpensePayService;
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
 * Created by Allen on 2016/6/27.
 */
@Controller
@RequestMapping(value = "/redCStuEP")
public class RedCStudentExpensePayController extends LoggerController<StudentExpensePay,RedCStudentExpensePayService> {
    private static Logger log = Logger.getLogger(RedCStudentExpensePayController.class);


    @Resource
    private RedCStudentExpensePayService redCStudentExpensePayService;
    @Resource
    private FindStudentExpensePayByCodeService findStudentExpensePayByCodeService;

    /**
     * 打开新增页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam("id") long id){
        StudentExpensePay studentExpensePay = findStudentExpensePayByCodeService.get(id);
        request.setAttribute("studentExpensePay", studentExpensePay);
        return "finance/redCStuEP";
    }

    /**
     * 执行添加的方法
     * @param request
     * @return
     */
    @RequestMapping(value = "redC")
    @ResponseBody
    public JSONObject redC(HttpServletRequest request,
                           @RequestParam("id") long id,
                           @RequestParam("remark") String remark,
                           @RequestParam("money") String money){
        JSONObject jsonObject = new JSONObject();
        try {
            redCStudentExpensePayService.redC(id, remark, money, UserTools.getLoginUserForName(request));
            jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"红冲学生入账信息");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
