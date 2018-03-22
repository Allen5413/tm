package com.zs.web.controller.basic.resource;

import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.DelResourceService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 删除资源
 * Created by Allen on 2015/4/28.
 */
@Controller
@RequestMapping(value = "/delResource")
public class DelResourceController extends
        LoggerController<Resource, DelResourceService> {
    private static Logger log = Logger.getLogger(DelResourceController.class);

    @javax.annotation.Resource
    private DelResourceService delResourceService;

    @RequestMapping(value = "resourceDel")
    @ResponseBody
    public JSONObject delResource(@RequestParam("id") long id, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            delResourceService.delResource(id);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "删除资源");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
