package com.zs.web.controller.sync.spot;

import com.zs.domain.sync.Spot;
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

/**
 * Created by Allen on 2015/8/13.
 */
@Controller
@RequestMapping(value = "/printSpot")
public class PrintSpotController extends LoggerController<Spot, FindSpotService> {

    private static Logger log = Logger.getLogger(PrintSpotController.class);

    @Resource
    private FindSpotService findSpotService;

    /**
     * 打开编辑学习中心页面
     * @return
     */
    @RequestMapping(value = "print")
    public String open(@RequestParam("id") long id, HttpServletRequest request){
        Spot spot = findSpotService.get(id);
        request.setAttribute("spot", spot);
        return "user/spotUserPrint";
    }
}
