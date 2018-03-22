package com.zs.web.controller.sale.studentbookorder;

import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Student;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForIsSendStudentService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/9/19.
 */
@Controller
@RequestMapping(value = "/editStudentBookOrderForIsSendStudent")
public class EditStudentBookOrderForIsSendStudentController extends
        LoggerController<StudentBookOrder, EditStudentBookOrderForIsSendStudentService> {

    private static Logger log = Logger.getLogger(EditStudentBookOrderForIsSendStudentController.class);

    @Resource
    private EditStudentBookOrderForIsSendStudentService editStudentBookOrderForIsSendStudentService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindStudentBookOrderByCodeService findStudentBookOrderByCodeService;


    @RequestMapping(value = "open")
    public String open(@RequestParam("orderCode") String orderCode,
                       @RequestParam("studentCode") String studentCode,
                       HttpServletRequest request) {
        Student student = findStudentByCodeService.getStudentByCode(studentCode);
        StudentBookOrder studentBookOrder = findStudentBookOrderByCodeService.findStudentBookOrderByCode(orderCode);
        request.setAttribute("orderCode", orderCode);
        request.setAttribute("studentCode", studentCode);
        request.setAttribute("name", student.getName());
        request.setAttribute("level", LevelEnum.getDescn(student.getLevelCode()));
        request.setAttribute("spec", SpecEnum.getDescn(student.getSpecCode()));
        request.setAttribute("isSendStudent", studentBookOrder.getIsSendStudent());
        request.setAttribute("sendAddress", studentBookOrder.getSendAddress());
        request.setAttribute("mobile", studentBookOrder.getSendPhone());
        request.setAttribute("postalCode", studentBookOrder.getSendZipCode());
        return "studentOrder/editIsSendStudent";
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(@RequestParam("orderCode") String orderCode,
                                 @RequestParam("sendAddress") String sendAddress,
                                 @RequestParam("isSendStudent") int isSendStudent,
                                 @RequestParam("mobile") String mobile,
                                 @RequestParam("postalCode") String postalCode,
                                 HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try{
            if(isSendStudent == Student.IS_SEND_STUDENT_YES) {
                if (StringUtils.isEmpty(sendAddress)) {
                    throw new BusinessException("请输入邮寄地址");
                }
                if (StringUtils.isEmpty(mobile)) {
                    throw new BusinessException("请输入联系电话");
                }
            }
            editStudentBookOrderForIsSendStudentService.edit(orderCode, isSendStudent, sendAddress, mobile, postalCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改订单邮寄方式");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
