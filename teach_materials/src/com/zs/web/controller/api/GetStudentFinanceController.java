package com.zs.web.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.zs.domain.sync.Student;
import com.zs.service.api.GetStudentFinanceService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/5/27.
 */
@Controller
@RequestMapping(value = "/api")
public class GetStudentFinanceController extends LoggerController<Student, FindStudentByCodeService> {
    private static Logger log = Logger.getLogger(GetStudentFinanceController.class);

    @Resource
    private GetStudentFinanceService getStudentFinanceService;

    @RequestMapping(value = "getStudentFinance")
    @ResponseBody
    public JSONObject getStudentFinance(HttpServletRequest request,
                                        @RequestParam("code") String code){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = getStudentFinanceService.get(code);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "调用获取学生财务信息接口");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "getStudentFinance2")
    @ResponseBody
    public String getStudentFinance2(HttpServletRequest request,
                                        @RequestParam("code") String code,
                                        @RequestParam("callback")String callback){
        String returnStr = "";
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = getStudentFinanceService.get(code);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "调用获取学生财务信息接口");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        returnStr = callback+"("+jsonObject.toString()+")";
        return returnStr;
    }
}
