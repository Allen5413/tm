package com.zs.web.controller.sale.studentbookordertm;

import com.zs.config.UserTypeEnum;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.service.sale.studentbookordertm.AddStudentBookOrderTMService;
import com.zs.service.sale.studentbookordertm.StudentBookOrderTMService;
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
 * 增加学生订单教材明细
 * Created by Allen on 2015/7/14.
 */
@Controller
@RequestMapping(value = "/addStudentBookOrderTM")
public class AddStudentBookOrderTMController extends LoggerController<StudentBookOrderTM, StudentBookOrderTMService> {
    private static Logger log = Logger.getLogger(AddStudentBookOrderTMController.class);

    @Resource
    private AddStudentBookOrderTMService addStudentBookOrderTMService;


    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) {
        String loginType = UserTools.getLoginUserForLoginType(request);
        if(loginType.equals(UserTypeEnum.STUDENT.getValue())){
            return "studentPages/studentOrderTMAdd";
        }else{
            return "studentOrder/studentOrderTMAdd";
        }
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("orderCode") String orderCode,
                          @RequestParam("tmIds") String[] tmIds,
                          @RequestParam("tmCounts") String[] tmCounts){
        JSONObject jsonObject = new JSONObject();
        try{
            StringBuilder idAndCounts = new StringBuilder();
            for(int i=0; i<tmIds.length; i++){
                String tmId = tmIds[i];
                String count = tmCounts[i];
                idAndCounts.append(tmId).append("_").append(count).append(",");
            }
            addStudentBookOrderTMService.AddStudentBookOrderTM (orderCode, idAndCounts.toString().substring(0, idAndCounts.length()-1), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
