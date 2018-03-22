package com.zs.web.controller.sale.studentbookordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMForSpotPrintService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/7/19.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderTMForSpotPrint")
public class FindStudentBookOrderTMForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderTMForSpotPrintController.class);

    @Resource
    private FindStudentBookOrderTMForSpotPrintService findStudentBookOrderTMForSpotPrintService;
    @Resource
    private FindPreviousSemesterService findPreviousSemesterService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                      @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                      @RequestParam(value="state", required=false, defaultValue="1") String state,
                      @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                      @RequestParam(value="flag") String flag,
                        HttpServletRequest request){
        try{

            //查询学期
            Semester semester = findPreviousSemesterService.get(Long.parseLong(semesterId));
            if(null == semester){
                throw new BusinessException("没有找到学期信息");
            }
            //查询学习中心
            Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
            if(null == spot){
                throw new BusinessException(spotCode+"，学习中心没找到");
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("state", state);
            params.put("operateTime", operateTime);
            JSONObject result = findStudentBookOrderTMForSpotPrintService.findStudentBookOrderTMForSpotPrint("0".equals(flag) ? null : request, params, null);
            request.setAttribute("resultJson", result);
            request.setAttribute("spot", spot);
            request.setAttribute("semester", semester);
            return "studentOrder/spotOrderTMPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学生发书单");
            return "error";
        }
    }
}
