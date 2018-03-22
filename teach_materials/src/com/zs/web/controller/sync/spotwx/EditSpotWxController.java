package com.zs.web.controller.sync.spotwx;

import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.EditSpotWxService;
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

/**修改学习中心基本信息
 * Created by Allen on 2015/7/6.
 */
@Controller
@RequestMapping(value = "/editSpotWx")
public class EditSpotWxController extends LoggerController<SpotWx, EditSpotWxService> {

    private static Logger log = Logger.getLogger(EditSpotWxController.class);

    @Resource
    private EditSpotWxService editSpotWxService;

    /**
     * 打开编辑学习中心页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id") long id, HttpServletRequest request){
        SpotWx spotWx = editSpotWxService.get(id);
        request.setAttribute("spotWx", spotWx);
        return "user/spotWxEdit";
    }

    /**
     * 编辑学习中心用户
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request,
                             @RequestParam("id") long id,
                             @RequestParam("idCard") String idCard,
                             @RequestParam("name") String name){
        JSONObject jsonObject = new JSONObject();
        try{
            editSpotWxService.edit(id, idCard, name, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑学习中心微信管理员");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
