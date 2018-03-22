package com.zs.web.controller.sale.onceorderpackage;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Student;
import com.zs.service.sale.onceorder.FindOnceOrderByCodeService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import com.zs.service.sale.studentbookorderpackage.AddStudentBookOrderPackageService;
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
 * 学生订单打包操作, 发学生快递的在此打包
 * Created by Allen on 2015/7/22.
 */
@Controller
@RequestMapping(value = "/addOnceOrderPackageForStudent")
public class AddOnceOrderPackageForStudentController extends
        LoggerController<StudentBookOrderPackage, AddStudentBookOrderPackageService> {

    private static Logger log = Logger.getLogger(AddOnceOrderPackageForStudentController.class);

    @Resource
    private AddStudentBookOrderPackageService addStudentBookOrderPackageService;
    @Resource
    private FindOnceOrderByCodeService findOnceOrderByCodeService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;

    /**
     * 打开新增菜单页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        return "onceOrder/onceOrderPackageAddForStudent";
    }


    /**
     * 学生订单打包
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                      @RequestParam("orderCode") String orderCode,
                      @RequestParam("logisticCode") String logisticCode){
        JSONObject jsonObject = new JSONObject();
        try{
            StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeService.find(orderCode);
            if(null == studentBookOnceOrder){
                throw new BusinessException("没有找到订单号："+orderCode+"，的订单信息");
            }
            Student student = findStudentByCodeService.getStudentByCode(studentBookOnceOrder.getStudentCode());
            if(null == student){
                throw new BusinessException("没有找到订单号："+orderCode+"，的学生信息");
            }

            addStudentBookOrderPackageService.addStudentBookOrderPackage(student.getSpotCode(), orderCode, logisticCode, StudentBookOrderPackage.IS_ONCE_YES, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
            jsonObject.put("studentCode", student.getCode());
            jsonObject.put("studentName", student.getName());
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "生成学生包");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
