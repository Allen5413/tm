package com.zs.web.controller.weixin;

import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Student;
import com.zs.service.sale.studentbookorder.ConfirmStudentBookOrderService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
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
 * 确认学生订单  学习中心微信端使用
 * Created by Allen on 2015/7/8.
 */
@Controller
@RequestMapping(value = "/wxConfirmStudentBookOrder")
public class WxConfirmStudentBookOrderController extends LoggerController {
    private static Logger log = Logger.getLogger(WxConfirmStudentBookOrderController.class);

    @Resource
    private ConfirmStudentBookOrderService confirmStudentBookOrderService;
    @Resource
    private FindStudentBookOrderByCodeService findStudentBookOrderByCodeService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;

    @RequestMapping(value = "cf")
    @ResponseBody
    public JSONObject confirm(@RequestParam(value = "code") String code, @RequestParam(value = "studentCode") String studentCode, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            Student student = findStudentByCodeService.getStudentByCode(studentCode);
            StudentBookOrder studentBookOrder = findStudentBookOrderByCodeService.findStudentBookOrderByCode(code);
            if(null != studentBookOrder) {
                confirmStudentBookOrderService.confirmStudentBookOrder(studentBookOrder.getId(), student.getSpotCode(), student.getSpotCode());
            }
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "确认订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    @RequestMapping(value = "studentConfirm")
    @ResponseBody
    public JSONObject studentConfirm(@RequestParam("cb") String[] idCodeCount,
                                     @RequestParam("studentCode") String studentCode,
                                     HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = confirmStudentBookOrderService.confirmStudentBookOrderForStudent(studentCode, idCodeCount, UserTools.getLoginUserForName(request));
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "确认订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
