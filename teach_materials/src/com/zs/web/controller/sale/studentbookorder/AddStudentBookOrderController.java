package com.zs.web.controller.sale.studentbookorder;

import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.AddStudentBookOrderService;
import com.zs.service.sale.studentbookordertm.StudentBookOrderTMService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import com.zs.web.controller.sale.studentbookordertm.AddStudentBookOrderTMController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 新增学生订单
 * Created by Allen on 2015/7/15.
 */
@Controller
@RequestMapping(value = "/addStudentBookOrder")
public class AddStudentBookOrderController extends LoggerController<StudentBookOrder, AddStudentBookOrderService> {
    private static Logger log = Logger.getLogger(AddStudentBookOrderController.class);

    @Resource
    private AddStudentBookOrderService addStudentBookOrderService;


    @RequestMapping(value = "open")
    public String open() {
        return "studentOrder/confirmOrderTMAdd";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("studentCode") String studentCode,
                          @RequestParam("tmIds") String[] tmIds,
                          @RequestParam("tmCounts") String[] tmCounts,
                          @RequestParam("courseCodes") String[] courseCodes){
        JSONObject jsonObject = new JSONObject();
        try{
            StringBuilder idAndCounts = new StringBuilder();
            for(int i=0; i<tmIds.length; i++){
                String tmId = tmIds[i];
                String count = tmCounts[i];
                String courseCode = courseCodes[i];
                idAndCounts.append(tmId).append("_").append(count).append("_").append(courseCode).append(",");
            }
            JSONObject returnJSON = addStudentBookOrderService.addStudentBookOrder(studentCode, idAndCounts.toString().substring(0, idAndCounts.length() - 1), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
            jsonObject.put("orderInfo", returnJSON);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增学生订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}

