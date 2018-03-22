package com.zs.web.controller.basic.environment;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.basic.environment.EnvironmentDAO;
import com.zs.domain.basic.Environment;
import com.zs.service.basic.environment.UpdateEnvironmentService;
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
 * Created by Allen on 2015/5/13.
 */
@Controller
@RequestMapping(value = "/editEnvironment")
public class EditEnvironmentController extends LoggerController<Environment, UpdateEnvironmentService> {
    private static Logger log = Logger.getLogger(EditEnvironmentController.class);


    @Resource
    private EnvironmentDAO environmentDAO;

    @Resource
    private UpdateEnvironmentService updateEnvironmentService;

    @RequestMapping(value = "openEditEnvironmentPage")
    public String openEditEnvironmentPage(@RequestParam("id") long id, HttpServletRequest request){
        Environment environment = environmentDAO.get(id);
        request.setAttribute("environment", environment);
        return "environment/environmentEdit";
    }

    @RequestMapping(value = "environmentEdit")
    @ResponseBody
    public JSONObject environmentEdit(HttpServletRequest request, Environment environment){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != environment) {
                updateEnvironmentService.updateE(environment, UserTools.getLoginUserForName(request));
            }else {
                throw new BusinessException("传入的值为空");
            }
            jsonObject.put("state",0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改环境变量");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
