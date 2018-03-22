package com.zs.web.controller.sale.studentbookorder;

import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderForSpotPrintService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
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
 * Created by Allen on 2015/7/18.
 */
@Controller
@RequestMapping(value = "/findStudentBookOrderForSpotPrint")
public class FindStudentBookOrderForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderForSpotPrintController.class);

    @Resource
    private FindStudentBookOrderForSpotPrintService findStudentBookOrderForSpotPrintService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId", required=false, defaultValue="") String semesterId,
                                          @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                          @RequestParam(value="state", required=false, defaultValue="1") String state,
                                          @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                                          HttpServletRequest request){
        try{

            Semester semester = findNowSemesterService.get(Long.parseLong(semesterId));

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCode", spotCode);
            params.put("state", state);
            params.put("operateTime", operateTime);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("deptName", true);
            JSONArray jsonArray = findStudentBookOrderForSpotPrintService.findStudentBookOrderForSpotPrint(params, sortMap);

            String title="";
            if(null != jsonArray && 0 < jsonArray.size()){
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                title += jsonObject.get("spotCode").toString() + jsonObject.get("spotName").toString();
            }

            title += "  "+semester.getSemester()+"  发书单";


            request.setAttribute("title", title);
            request.setAttribute("resultJson", jsonArray);
            return "studentOrder/spotOrderPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学习中心发书单");
            return "error";
        }
    }
}
