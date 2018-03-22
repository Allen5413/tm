package com.zs.web.controller.sale.studentbookorderpackage;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.sale.onceorder.FindOnceOrderByPackageIdService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByPackageIdService;
import com.zs.service.sale.studentbookorderpackage.AddStudentBookOrderPackageService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打印教材包
 * Created by Allen on 2015/7/25.
 */
@Controller
@RequestMapping(value = "/printStudentBookOrderPackage")
public class PrintStudentBookOrderPackageController extends
        LoggerController<StudentBookOrderPackage, AddStudentBookOrderPackageService> {
    private static Logger log = Logger.getLogger(PrintStudentBookOrderPackageController.class);

    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindStudentBookOrderByPackageIdService findStudentBookOrderByPackageIdService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindOnceOrderByPackageIdService findOnceOrderByPackageIdService;

    /**
     * 打开新增菜单页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam("id") long id){
        try {
            //查询教材包
            StudentBookOrderPackage studentBookOrderPackage = findStudentBookOrderPackagePageByWhereService.get(id);
            if (null == studentBookOrderPackage) {
                throw new BusinessException("没有找到教材包");
            }
            //查找教材包所属学习中心
            Spot spot = findSpotByCodeService.getSpotByCode(studentBookOrderPackage.getSpotCode());
            //学生订单
            if(studentBookOrderPackage.getIsOnce() == StudentBookOrderPackage.IS_ONCE_NOT) {
                Map<StudentBookOrder, String> studentBookOrderMap = new HashMap<StudentBookOrder, String>();
                //查询教材包下的学生订单
                List<StudentBookOrder> studentBookOrderList = findStudentBookOrderByPackageIdService.findStudentBookOrderByPackageId(studentBookOrderPackage.getId());
                if (null != studentBookOrderList && 0 < studentBookOrderList.size()) {
                    Student student = null;
                    for (StudentBookOrder studentBookOrder : studentBookOrderList) {
                        String name = "";
                        student = findStudentByCodeService.getStudentByCode(studentBookOrder.getStudentCode());
                        if (null != student) {
                            name = student.getName();
                        }
                        studentBookOrderMap.put(studentBookOrder, name);
                    }
                }
                request.setAttribute("studentBookOrderMap", studentBookOrderMap);
            }
            //一次性订单
            if(studentBookOrderPackage.getIsOnce() == StudentBookOrderPackage.IS_ONCE_YES) {
                Map<StudentBookOnceOrder, String> studentBookOrderMap = new HashMap<StudentBookOnceOrder, String>();
                //查询教材包下的学生订单
                List<StudentBookOnceOrder> studentBookOnceOrderList = findOnceOrderByPackageIdService.find(studentBookOrderPackage.getId());
                if (null != studentBookOnceOrderList && 0 < studentBookOnceOrderList.size()) {
                    Student student = null;
                    for (StudentBookOnceOrder studentBookOnceOrder : studentBookOnceOrderList) {
                        String name = "";
                        student = findStudentByCodeService.getStudentByCode(studentBookOnceOrder.getStudentCode());
                        if (null != student) {
                            name = student.getName();
                        }
                        studentBookOrderMap.put(studentBookOnceOrder, name);
                    }
                }
                request.setAttribute("studentBookOrderMap", studentBookOrderMap);
            }
            request.setAttribute("studentBookOrderPackage", studentBookOrderPackage);
            request.setAttribute("spot", spot);
        }catch(Exception e){
            super.outputException(request, e, log, "教材包打印");
            return "error";
        }
        return "studentOrder/studentOrderPackagePrint";
    }
}
