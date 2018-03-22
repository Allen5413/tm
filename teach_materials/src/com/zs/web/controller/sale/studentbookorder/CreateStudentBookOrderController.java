package com.zs.web.controller.sale.studentbookorder;

import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.CreateStudentBookOrderService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2015/6/9.
 */
@Controller
@RequestMapping(value = "/createStudentBookOrder")
public class CreateStudentBookOrderController extends
        LoggerController<StudentBookOrder, CreateStudentBookOrderService> {
    private static Logger log = Logger.getLogger(CreateStudentBookOrderController.class);

    @Resource
    private CreateStudentBookOrderService createStudentBookOrderService;

    @RequestMapping(value = "doCreateStudentBookOrder")
    public String addTeachMaterialRatio(HttpServletRequest request, HttpServletResponse response){
        try{
            createStudentBookOrderService.addStudentBookOrder(UserTools.getLoginUserForName(request));
            return "/studentOrder/studentOrderList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "生成采购单");
            return "error";
        }
    }
}
