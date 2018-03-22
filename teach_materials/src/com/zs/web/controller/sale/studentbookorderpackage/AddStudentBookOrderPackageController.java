package com.zs.web.controller.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import com.zs.service.sale.studentbookorderpackage.AddStudentBookOrderPackageService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生订单打包操作
 * Created by Allen on 2015/7/22.
 */
@Controller
@RequestMapping(value = "/addStudentBookOrderPackage")
public class AddStudentBookOrderPackageController extends
        LoggerController<StudentBookOrderPackage, AddStudentBookOrderPackageService> {

    private static Logger log = Logger.getLogger(AddStudentBookOrderPackageController.class);

    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindStudentBookOrderByCodeService findStudentBookOrderByCodeService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private AddStudentBookOrderPackageService addStudentBookOrderPackageService;

    /**
     * 打开新增菜单页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        List<Spot> spotList = findSpotService.getAll();
        request.setAttribute("spotList", spotList);
        return "studentOrder/studentOrderPackageAdd";
    }

    /**
     * 查询学习中心已打包数量，已扫描学生订单数量
     * @param request
     * @return
     */
    @RequestMapping(value = "searchPackCount")
    @ResponseBody
    public JSONObject searchPackCount(HttpServletRequest request, @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            int orderCount = 0;
            if(StringUtils.isEmpty(spotCode)) {
                throw new BusinessException("没有传入学习中心编号");
            }
            //查询学习中心
            Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
            if(null == spot){
                throw new BusinessException("没有找到学习中心信息");
            }

            //获取当前学期
            Semester semester = findNowSemesterService.getNowSemester();
            Map<String,String> params = new HashMap<String, String>();
            params.put("semesterId", semester.getId()+"");
            params.put("spotCode", spotCode);
            params.put("isOnce", StudentBookOrderPackage.IS_ONCE_NOT+"");
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(999999);
            pageInfo = findStudentBookOrderPackagePageByWhereService.findPageByWhere(pageInfo, params, null);
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                for(int i=0; i<pageInfo.getPageResults().size(); i++){
                    JSONObject json = (JSONObject) pageInfo.getPageResults().get(i);
                    orderCount += Integer.parseInt(json.get("orderCount").toString());
                }
            }
            jsonObject.put("state", 0);
            jsonObject.put("packageCount", null == pageInfo ? 0 : pageInfo.getTotalCount());
            jsonObject.put("orderCount", orderCount);
            jsonObject.put("spot", spot);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "查询学习中心已打包数量");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }


    /**
     * 查询订单学生
     * @param request
     * @return
     */
    @RequestMapping(value = "searchOrderStudent")
    @ResponseBody
    public JSONObject searchOrderStudent(HttpServletRequest request,
                                         @RequestParam("orderCode") String orderCode,
                                         @RequestParam("spotCode") String spotCode,
                                         @RequestParam("isSendStudent") int isSendStudent){
        JSONObject jsonObject = new JSONObject();
        try{
            if(StringUtils.isEmpty(orderCode)) {
                throw new BusinessException("没有传入订单编号");
            }
            //查询订单信息
            StudentBookOrder studentBookOrder = findStudentBookOrderByCodeService.findStudentBookOrderByCode(orderCode);
            if(null == studentBookOrder){
                throw new BusinessException("没有找到订单信息");
            }
            if(null != studentBookOrder.getPackageId() && 0 < studentBookOrder.getPackageId()){
                throw new BusinessException("学号："+studentBookOrder.getStudentCode()+", 订单号："+orderCode+", 已经打包");
            }
            if(studentBookOrder.getState() != StudentBookOrder.STATE_CONFIRMED && studentBookOrder.getState() != StudentBookOrder.STATE_SORTING){
                throw new BusinessException("学号："+studentBookOrder.getStudentCode()+", 订单号："+orderCode+", 状态不能扫描");
            }
            if(-1 < isSendStudent){
                if(0 == isSendStudent && studentBookOrder.getIsSendStudent() == StudentBookOrder.IS_SEND_STUDENT_YES){
                    throw new BusinessException("该次打包是快递给中心的，订单："+orderCode+"，是快递给学生的，不能在此扫描");
                }
                if(1 == isSendStudent && studentBookOrder.getIsSendStudent() == StudentBookOrder.IS_SEND_STUDENT_NOT){
                    throw new BusinessException("该次打包是快递给学生的，订单："+orderCode+"，是快递给中心的，不能在此扫描");
                }
            }
            //获取订单学生信息
            Student student = findStudentByCodeService.getStudentByCode(studentBookOrder.getStudentCode());
            if(null == student){
                throw new BusinessException("没有找到订单的学生信息");
            }
            if(!student.getSpotCode().equals(spotCode)){
                throw new BusinessException("该订单不是该学习中心的");
            }

            jsonObject.put("state", 0);
            jsonObject.put("code", student.getCode());
            jsonObject.put("name", student.getName());
            jsonObject.put("isSendStudent", studentBookOrder.getIsSendStudent());
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "查询订单信息");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 学生订单打包
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                      @RequestParam("orderCodes") String orderCodes,
                      @RequestParam("logisticCode") String logisticCode,
                      @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            StudentBookOrderPackage studentBookOrderPackage = addStudentBookOrderPackageService.addStudentBookOrderPackage(spotCode, orderCodes, logisticCode, StudentBookOrderPackage.IS_ONCE_NOT, UserTools.getLoginUserForName(request));
            request.setAttribute("studentBookOrderPackage", studentBookOrderPackage);
            jsonObject.put("state", 0);
            jsonObject.put("packageId", studentBookOrderPackage.getId());
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "扫描结束，打印清单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
