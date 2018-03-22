package com.zs.web.controller.basic.teachmaterialrevision;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.EditTeachMaterialRevisionService;
import com.zs.service.basic.teachmaterialrevision.impl.EditTeachMaterialRevisionForIsNowServiceImpl;
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
 * Created by Allen on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/editRevisionForIsNow")
public class EditTeachMaterialRevisionForIsNowController
        extends LoggerController<TeachMaterialRevision,EditTeachMaterialRevisionService> {
    private static Logger log = Logger.getLogger(EditTeachMaterialRevisionForIsNowController.class);

    @Resource
    private EditTeachMaterialRevisionService editTeachMaterialRevisionForIsNowService;

    @RequestMapping(value = "revisionForIsNowEdit")
    @ResponseBody
    public JSONObject revisionForIsNowEdit(HttpServletRequest request, TeachMaterialRevision teachMaterialRevision){
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != teachMaterialRevision){
                editTeachMaterialRevisionForIsNowService.editTeachMaterialRevision(teachMaterialRevision, UserTools.getLoginUserForName(request));
            }else {
                throw new BusinessException("设置当前版次");
            }
            jsonObject.put("state",0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"设置当前版次");
            jsonObject.put("state",1);
            jsonObject.put("msg",msg);
        }
        return jsonObject;
    }
}
