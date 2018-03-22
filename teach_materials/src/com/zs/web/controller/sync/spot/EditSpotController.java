package com.zs.web.controller.sync.spot;

import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.EditSpotService;
import com.zs.service.sync.spot.FindSpotService;
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
@RequestMapping(value = "/editSpot")
public class EditSpotController extends LoggerController<Spot, FindSpotService> {

    private static Logger log = Logger.getLogger(EditSpotController.class);

    @Resource
    private EditSpotService editSpotService;

    /**
     * 打开编辑学习中心页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id") long id, HttpServletRequest request){
        Spot spot = editSpotService.get(id);
        request.setAttribute("spot", spot);
        return "user/spotUserEdit";
    }

    /**
     * 编辑学习中心用户
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, Spot spot){
        JSONObject jsonObject = new JSONObject();
        try{
            editSpotService.edit(spot);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "编辑学习中心");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
