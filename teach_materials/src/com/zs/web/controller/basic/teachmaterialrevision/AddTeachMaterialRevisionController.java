package com.zs.web.controller.basic.teachmaterialrevision;

import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.AddTeachMaterialRevisionService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/addTeachMaterialRevision")
public class AddTeachMaterialRevisionController extends
        LoggerController<TeachMaterialRevision, AddTeachMaterialRevisionService> {

    private static Logger log = Logger.getLogger(AddTeachMaterialRevisionController.class);

    @Resource
    private AddTeachMaterialRevisionService addTeachMaterialRevisionService;

    /**
     * 打开新增版次页面
     * @return
     */
    @RequestMapping(value = "openAddTMR")
    public String openAddTMR(){
        return "teachMaterial/tmRevisionAdd";
    }

    /**
     * 新增版次
     * @param request
     * @return
     */
    @RequestMapping(value = "tmrAdd")
    @ResponseBody
    public JSONObject addTMR(HttpServletRequest request, TeachMaterialRevision teachMaterialRevision){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterialRevision) {
                addTeachMaterialRevisionService.addTeachMaterialRevision(teachMaterialRevision, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增版次");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
