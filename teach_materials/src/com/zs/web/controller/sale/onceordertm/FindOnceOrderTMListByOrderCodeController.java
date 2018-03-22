package com.zs.web.controller.sale.onceordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.config.UserTypeEnum;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.kuaidi.push.FindKuaidiPushByNuService;
import com.zs.service.sale.onceordertm.FindOnceOrderTMListByOrderCodeService;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderListByOrderCodeService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/5/26.
 */
@Controller
@RequestMapping(value = "/findOnceOrderTMListByOrderCode")
public class FindOnceOrderTMListByOrderCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(FindOnceOrderTMListByOrderCodeController.class);

    @Resource
    private FindOnceOrderTMListByOrderCodeService findOnceOrderTMListByOrderCodeService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindStudentBookOrderPackagePageByWhereService findStudentBookOrderPackagePageByWhereService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindKuaidiPushByNuService findKuaidiPushByNuService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="orderCode") String orderCode,
                       @RequestParam(value="studentCode") String studentCode,
                       @RequestParam(value="orderId") long orderId,
                       HttpServletRequest request){
        try{
            //查询学生信息
            Student student = findStudentByCodeService.getStudentByCode(studentCode);
            if(null == student){
                throw new BusinessException("没有找到学号："+studentCode+", 的信息");
            }
            //查询学习中心信息
            Spot spot = findSpotByCodeService.getSpotByCode(student.getSpotCode());
            //查询订单明细
            JSONArray resultJson = findOnceOrderTMListByOrderCodeService.find(orderId);
            if(null != resultJson && 0 < resultJson.size()){
                JSONObject jsonObject = (JSONObject)resultJson.get(0);
                if(null != jsonObject.get("packageId")) {
                    //查询订单大包信息
                    StudentBookOrderPackage studentBookOrderPackage = findStudentBookOrderPackagePageByWhereService.get(Long.parseLong(jsonObject.get("packageId").toString()));
                    if(!StringUtils.isEmpty(studentBookOrderPackage.getLogisticCode())){
                        String[] nus = studentBookOrderPackage.getLogisticCode().split(",");
                        if(null != nus && 0 < nus.length) {
                            //查询快递信息
                            Map<String, JSONArray> kuaidiMap = new HashMap<String, JSONArray>();
                            for(String nu : nus) {
                                JSONArray jsonArray = findKuaidiPushByNuService.find(nu);
                                kuaidiMap.put(nu, jsonArray);
                            }
                            request.setAttribute("kuaidiMap", kuaidiMap);
                        }
                    }
                    request.setAttribute("studentBookOrderPackage", studentBookOrderPackage);
                }
            }

            request.setAttribute("student", student);
            request.setAttribute("spot", spot);
            request.setAttribute("resultJson", resultJson);

            String loginType = UserTools.getLoginUserForLoginType(request);
            if(loginType.equals(UserTypeEnum.STUDENT.getValue())){
                return "studentPages/onceOrderTMList";
            }else{
                return "onceOrder/onceOrderTMList";
            }
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询学生订书单的教材明细");
            return "error";
        }
    }
}
