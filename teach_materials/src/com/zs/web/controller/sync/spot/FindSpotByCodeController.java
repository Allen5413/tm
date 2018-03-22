package com.zs.web.controller.sync.spot;

import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotByCodeService;
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
 * Created by Allen on 2016/11/9.
 */
@Controller
@RequestMapping(value = "/findSpotByCode")
public class FindSpotByCodeController extends LoggerController<Spot, FindSpotByCodeService> {

    private static Logger log = Logger.getLogger(PrintSpotController.class);

    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    /**
     * 打开编辑学习中心页面
     * @return
     */
    @RequestMapping(value = "find")
    @ResponseBody
    public JSONObject find(@RequestParam("code") String code, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            Spot spot = findSpotByCodeService.getSpotByCode(code);
            jsonObject.put("spot", spot);
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "查询学习中心");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
