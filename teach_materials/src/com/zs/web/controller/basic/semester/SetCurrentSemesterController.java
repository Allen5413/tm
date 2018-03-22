package com.zs.web.controller.basic.semester;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.SetCurrentSemesterService;
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
 * 设置当前学期的controller
 * Created by LihongZhang on 2015/5/11.
 */
@Controller
@RequestMapping(value = "/setNowSemester")
public class SetCurrentSemesterController extends LoggerController<Semester,SetCurrentSemesterService> {
    private static Logger log = Logger.getLogger(SetCurrentSemesterController.class);

    @Resource
    private SetCurrentSemesterService setCurrentSemesterService;

    @RequestMapping(value = "setSemester")
    @ResponseBody
    public JSONObject setNowSemester(@RequestParam("id") Long id,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            if (null !=id){
                setCurrentSemesterService.setCurrentSemester(UserTools.getLoginUserForName(request),id);
            }else {
                throw new BusinessException("修改当前学期");
            }
            jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"设置当前学期");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
