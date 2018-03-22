package com.zs.web.controller.sync.student;

import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.sync.Student;
import com.zs.service.api.GetStudentFinanceService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.service.sync.student.impl.FindStudentInfoServiceImpl;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/10/20.
 */
@Controller
@RequestMapping(value = "/studentPayInfo")
public class FindStudentInfoController extends
        LoggerController<Student, FindStudentByCodeService> {

    private static Logger log = Logger.getLogger(FindStudentInfoController.class);

    @Resource
    public FindStudentInfoServiceImpl findStudentInfoService;
    @Resource
    public FindStudentByCodeService findStudentByCodeService;
    @Resource
    private GetStudentFinanceService getStudentFinanceService;

    @RequestMapping(value = "adminUserOpen")
    public String adminUserOpen(HttpServletRequest request) {
        return "studentPages/adminUserOpen";
    }

    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "code")String code,
                       HttpServletRequest request) {
        try {
            //查询学生信息
            Student student = findStudentByCodeService.getStudentByCode(code);
            if(null == student){
                throw new BusinessException("学号："+code+"，学生信息没找到");
            }
            JSONObject json = getStudentFinanceService.get(code);
            request.setAttribute("code", code);
            request.setAttribute("name", student.getName());
            request.setAttribute("price", json.get("price"));
        } catch (Exception e) {
            super.outputException(request, e, log, "查询学生信息");
            return "error";
        }
        return "studentPages/payInfo";
    }
}
