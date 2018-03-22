package com.zs.web.controller.basic.setteachmaterialtm;

import com.zs.domain.basic.SetTeachMaterialTM;
import com.zs.service.basic.setteachmaterialtm.AddSetTeachMaterialTMService;
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
 * Created by Allen on 2015/5/21.
 */
@Controller
@RequestMapping(value = "/addsetTM")
public class AddSetTeachMaterialTMController  extends
        LoggerController<SetTeachMaterialTM, AddSetTeachMaterialTMService> {
    private static Logger log = Logger.getLogger(AddSetTeachMaterialTMController.class);

    @Resource
    private AddSetTeachMaterialTMService addSetTeachMaterialTMService;

    @RequestMapping(value = "stmTMAdd")
    @ResponseBody
    public JSONObject addSetTeachMaterialCourse(@RequestParam(value = "stmId") long stmId,
                                                @RequestParam(value = "tmIds", required = false, defaultValue = "") String tmIds,
                                                HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            addSetTeachMaterialTMService.addSetTeachMaterialTM(stmId, tmIds, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "新增套教材关联教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
