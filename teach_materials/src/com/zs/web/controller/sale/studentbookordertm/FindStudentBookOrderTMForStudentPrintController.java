package com.zs.web.controller.sale.studentbookordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMForStudentPrintService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/7/19.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderTMForStudentPrint")
public class FindStudentBookOrderTMForStudentPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderTMForStudentPrintController.class);

    @Resource
    private FindStudentBookOrderTMForStudentPrintService findStudentBookOrderTMForStudentPrintService;
    @Resource
    private FindPreviousSemesterService findPreviousSemesterService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                      @RequestParam(value="countNum", required=false, defaultValue="1000") String countNum,
                      @RequestParam(value="pageNum", required=false, defaultValue="0") String pageNum,
                      @RequestParam(value="state", required=false, defaultValue="1") String state,
                      @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                      @RequestParam(value="flag") String flag,
                      @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                        HttpServletRequest request){
        try{

            //查询学期
            Semester semester = findPreviousSemesterService.get(Long.parseLong(semesterId));
            if(null == semester){
                throw new BusinessException("没有找到学期信息");
            }

            JSONObject result = findStudentBookOrderTMForStudentPrintService.print(Long.parseLong(semesterId), Integer.parseInt(state), Integer.parseInt(pageNum), Integer.parseInt(countNum), operateTime, spotCode, "0".equals(flag) ? null : request);
            request.setAttribute("resultJson", result);
            request.setAttribute("semester", semester);
            return "studentOrder/spotOrderTMPrintListForStudent";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学生发书单");
            return "error";
        }
    }
}
