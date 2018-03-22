package com.zs.web.controller.sync.spotwx;

import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.AddSpotWxService;
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
 * Created by Allen on 2015/7/6.
 */
@Controller
@RequestMapping(value = "/addSpotWx")
public class AddSpotWxController extends LoggerController<SpotWx, AddSpotWxService> {

    private static Logger log = Logger.getLogger(AddSpotWxController.class);

    @Resource
    private AddSpotWxService addSpotWxService;

    /**
     * 打开新增学习中心页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(){
        return "user/spotWxAdd";
    }

    /**
     * 新增学习中心用户
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, SpotWx spotWx){
        JSONObject jsonObject = new JSONObject();
        try{
            spotWx.setOperator(UserTools.getLoginUserForName(request));
            addSpotWxService.add(spotWx);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增学习中心微信管理员");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}