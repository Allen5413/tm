package com.zs.web.controller.kuaidi.push;

import com.zs.service.kuaidi.push.FindKuaidiPushByNuService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/10/15.
 */
@Controller
@RequestMapping(value = "/findKuaidiPushByNu")
public class FindKuaidiPushByNuController extends LoggerController {
    private static Logger log = Logger.getLogger(FindKuaidiPushByNuController.class);

    @Resource
    private FindKuaidiPushByNuService findKuaidiPushByNuService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="logisticCode") String logisticCode, HttpServletRequest request){
        try{
            Map<String, JSONArray> kuaidiPushMap = new HashMap<String, JSONArray>();
            String[] nus = logisticCode.split(",");
            if(null != nus && 0 < nus.length){
                for(String nu : nus) {
                    JSONArray jsonArray = findKuaidiPushByNuService.find(nu);
                    if(null != jsonArray) {
                        kuaidiPushMap.put(nu, jsonArray);
                    }
                }
            }
            request.setAttribute("kuaidiPushMap", kuaidiPushMap);
            return "kuaidi/kuaidiPushByNuList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询快递推送明细");
            return "error";
        }
    }
}
