package com.zs.web.controller.finance.studentexpensepay;

import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sync.Student;
import com.zs.service.finance.studentexpensepay.AddStudentExpensePayService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 学生添加入账信息的controller
 * Created by LihongZhang on 2015/5/15.
 */
@Controller
@RequestMapping(value = "/addStuEP")
public class AddStudentExpensePayController extends LoggerController<StudentExpensePay,AddStudentExpensePayService> {
    private static Logger log = Logger.getLogger(AddStudentExpensePayController.class);

    
    @Resource
    private AddStudentExpensePayService addStudentExpensePayService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;

    /**
     * 打开新增页面
     * @return
     */
    @RequestMapping(value = "openAddStuEPPage")
    public String openPage(@RequestParam(value = "code", required = false, defaultValue = "") String code,
                           HttpServletRequest request){
        if(!StringUtils.isEmpty(code)){
            Student student = findStudentByCodeService.getStudentByCode(code);
            request.setAttribute("student", student);
        }
        return "finance/stuEPAdd";
    }

    /**
     * 执行添加的方法
     * @param studentExpensePay
     * @param request
     * @return
     */
    @RequestMapping(value = "stuEPAdd")
    @ResponseBody
    public JSONObject addStudentExpensePay(StudentExpensePay studentExpensePay,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != studentExpensePay){
                studentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_SPOT);
                addStudentExpensePayService.addStudentExpensePay(studentExpensePay, UserTools.getLoginUserForName(request), UserTools.getLoginUserForLoginName(request));
            }
           jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"添加学生入账信息");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
