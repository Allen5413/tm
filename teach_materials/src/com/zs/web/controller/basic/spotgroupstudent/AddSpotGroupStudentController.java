package com.zs.web.controller.basic.spotgroupstudent;

import com.zs.domain.basic.SpotGroupStudent;
import com.zs.service.basic.spotgroupstudent.AddSpotGroupStudentService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/3.
 */
@Controller
@RequestMapping(value = "/addSpotGroupStudent")
public class AddSpotGroupStudentController extends
        LoggerController<SpotGroupStudent, AddSpotGroupStudentService> {
    private static Logger log = Logger.getLogger(AddSpotGroupStudentController.class);

    @Resource
    private AddSpotGroupStudentService addSpotGroupStudentService;

    @RequestMapping(value = "openAddSpotGroupStudent")
    public String openAddMenu(){
        return "userGroup/userGroupAdd";
    }

    @RequestMapping(value = "spotGroupStudentAdd")
    public String addSpotGroupStudent(HttpServletRequest request, SpotGroupStudent menu){
        try{
            if(null != menu) {
                addSpotGroupStudentService.addSpotGroupStudent(menu, UserTools.getLoginUserForName(request));
            }
            return "welcome";
        }catch(Exception e){
            super.outputException(request, e, log, "新增学习中心分组的学生");
            return "error";
        }
    }
}
