package com.zs.web.controller.basic.teachmaterialrevision;

import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.EditTeachMaterialRevisionService;
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
@RequestMapping(value = "/editTMRForPrice")
public class EditTeachMaterialRevisionForPriceController extends
        LoggerController<TeachMaterialRevision, EditTeachMaterialRevisionService> {

    private static Logger log = Logger.getLogger(EditTeachMaterialRevisionForPriceController.class);

    @Resource
    private EditTeachMaterialRevisionService editTeachMaterialRevisionForPriceService;

    /**
     * 打开编辑页面
     * @return
     */
    @RequestMapping(value = "openEditTMRForPricePage")
    public String openEditTMRForPricePage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterialRevision teachMaterialRevision = editTeachMaterialRevisionForPriceService.get(id);
        request.setAttribute("tmr", teachMaterialRevision);
        return "teachMaterial/tmRevisionPriceEdit";
    }

    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping(value = "tmRForPriceEdit")
    @ResponseBody
    public JSONObject editMenu(HttpServletRequest request, TeachMaterialRevision teachMaterialRevision){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterialRevision) {
                editTeachMaterialRevisionForPriceService.editTeachMaterialRevision(teachMaterialRevision, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑版次价格");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
