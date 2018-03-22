package com.zs.web.controller.sync.spotwx;

import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.FindSpotWxByCodeService;
import com.zs.web.controller.LoggerController;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/6/1.
 */
@Controller
@RequestMapping(value = "/findSpotWxByCode")
public class FindSpotWxByCodeController extends
        LoggerController<SpotWx, FindSpotWxByCodeService> {

    private static Logger log = Logger.getLogger(FindSpotWxByCodeController.class);

    @Resource
    public FindSpotWxByCodeService findSpotWxByCodeService;

    @RequestMapping(value = "open")
    public String open(){
        return "user/spotWxList";
    }

    @RequestMapping(value = "find")
    @ResponseBody
    public JSONObject find(@RequestParam("code") String code,
                                  HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<SpotWx> spotWxList = findSpotWxByCodeService.find(code);
            jsonObject.put("state", 0);
            jsonObject.put("spotWxList", spotWxList);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "查询学习中心微信管理员");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
