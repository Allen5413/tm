package com.zs.web.controller.sale.onceorder;

import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.EditOnceOrderForStudentSignService;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStudentSignService;
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
 * 学生领书
 * Created by Allen on 2015/9/10.
 */
@Controller
@RequestMapping(value = "/editOnceOrderForStudentSign")
public class EditOnceOrderForStudentSignController extends LoggerController<StudentBookOrder, EditStudentBookOrderForStudentSignService> {
    private static Logger log = Logger.getLogger(EditOnceOrderForStudentSignController.class);

    @Resource
    private EditOnceOrderForStudentSignService editOnceOrderForStudentSignService;

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                          @RequestParam("ids") String[] ids){
        JSONObject jsonObject = new JSONObject();
        try{
            editOnceOrderForStudentSignService.editor(UserTools.getLoginUserForName(request), ids);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "学生领书");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
