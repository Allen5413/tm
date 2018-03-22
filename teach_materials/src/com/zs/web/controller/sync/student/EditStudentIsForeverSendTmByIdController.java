package com.zs.web.controller.sync.student;

import com.alibaba.fastjson.JSONObject;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.EditStudentIsForeverSendTmByIdService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/9/19.
 */
@Controller
@RequestMapping(value = "/editStudentIsForeverSendTmById")
public class EditStudentIsForeverSendTmByIdController extends
        LoggerController<Student, EditStudentIsForeverSendTmByIdService> {

    private static Logger log = Logger.getLogger(EditStudentIsForeverSendTmByIdController.class);

    @Resource
    private EditStudentIsForeverSendTmByIdService editStudentIsForeverSendTmByIdService;


    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(@RequestParam("id") Long id,
                                 @RequestParam("isForeverSendTm") int isForeverSendTm,
                                 HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try{
            editStudentIsForeverSendTmByIdService.edit(id, isForeverSendTm, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改错误");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
