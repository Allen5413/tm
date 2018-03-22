package com.zs.web.controller.statis;

import com.zs.service.statis.FindTotalFinanceForSemesterService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2016/1/14.
 */
@Controller
@RequestMapping(value = "/findTotalFinanceForSemester")
public class FindTotalFinanceForSemesterController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSendTeachMaterialController.class);

    @Resource
    private FindTotalFinanceForSemesterService findTotalFinanceForSemesterService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request){
        try{

            JSONObject jsonObject = findTotalFinanceForSemesterService.find();
            request.setAttribute("json", jsonObject);
            return "statis/findTotalFinanceForSemester";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计学期总财务信息");
            return "error";
        }
    }

    @RequestMapping(value = "findBySemesterForSpot")
    public String findBySemesterForSpot(HttpServletRequest request,
                                        @RequestParam("year") int year,
                                        @RequestParam("quarter") int quarter){
        try{

            List<JSONObject> list = findTotalFinanceForSemesterService.findBySemesterForSpot(year,quarter);
            request.setAttribute("list", list);
            return "statis/findTotalFinanceForSemesterAndSpot";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计学期总财务信息");
            return "error";
        }
    }
}
