package com.zs.web.controller.finance.studentexpense;

import com.zs.config.UserTypeEnum;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensebuy.FindStudentExpenseBuyByCodeService;
import com.zs.service.finance.studentexpensepay.FindStudentExpensePayByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/25.
 */
@Controller
@RequestMapping(value = "/findStudentExpenseDetail")
public class FindStudentExpenseDetailByCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentExpenseDetailByCodeController.class);

    @Resource
    private FindStudentExpensePayByCodeService findStudentExpensePayByCodeService;
    @Resource
    private FindStudentExpenseBuyByCodeService findStudentExpenseBuyByCodeService;

    @RequestMapping(value = "doFindStudentExpenseDetail")
    public String doFindStudentExpenseDetail(@RequestParam(value="code") String code, HttpServletRequest request){
        try {
            //获取学生的入账记录
            List<StudentExpensePay> payList = findStudentExpensePayByCodeService.getStudentExpensePayByCode(code);
            //获取学生的消费记录
            Map<String, Map<Double, List<StudentExpenseBuy>>> buyMap = findStudentExpenseBuyByCodeService.getStudentExpenseBuyByCode(code);

            request.setAttribute("payList", payList);
            request.setAttribute("buyMap", buyMap);
            request.setAttribute("isAdmin", UserTools.getLoginUserForLoginType(request) == UserTypeEnum.ADMIN.getValue() ? 0 : 1);

            String loginType = UserTools.getLoginUserForLoginType(request);
            if(loginType.equals(UserTypeEnum.STUDENT.getValue())){
                return "studentPages/stuEPDetail";
            }else{
                return "finance/stuEPDetail";
            }
        }catch (Exception e){
            super.outputException(request,e,log,"查询学生费用明细失败");
            return "error";
        }
    }
}
