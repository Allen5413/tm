package com.zs.web.controller.sync.spotwx;

import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.AddSpotWxService;
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
 * Created by Allen on 2015/7/6.
 */
@Controller
@RequestMapping(value = "/delSpotWx")
public class DelSpotWxController extends LoggerController<SpotWx, AddSpotWxService> {

    private static Logger log = Logger.getLogger(DelSpotWxController.class);

    @Resource
    private AddSpotWxService addSpotWxService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, @RequestParam("id") long id){
        JSONObject jsonObject = new JSONObject();
        try{
            addSpotWxService.remove(addSpotWxService.get(id));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "删除学习中心微信管理员");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}