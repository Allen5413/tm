package com.zs.web.controller.sync.spot;

import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.AddSpotService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**新增学习中心基本信息，新增的不是网院的学习中心，是其他一些需要订书，但是没有账号登录的学习中心，中心编号必须4位数，以9开头
 * Created by Allen on 2015/7/6.
 */
@Controller
@RequestMapping(value = "/addSpot")
public class AddSpotController  extends LoggerController<Spot, FindSpotService> {

    private static Logger log = Logger.getLogger(EditSpotController.class);

    @Resource
    private AddSpotService addSpotService;

    /**
     * 打开新增学习中心页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(){
        return "user/spotUserAdd";
    }

    /**
     * 新增学习中心用户
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, Spot spot){
        JSONObject jsonObject = new JSONObject();
        try{
            addSpotService.add(spot);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增学习中心");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}