package com.zs.web.controller.weixin;

import com.zs.domain.wx.WxNotifyLog;
import com.zs.service.wx.notify.FindNotifyByOutTradeNoService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/7/5.
 */
@Controller
@RequestMapping(value = "/wxNotify")
public class WxNotifyController extends LoggerController {
    private static Logger log = Logger.getLogger(WxSearchController.class);

    @Resource
    private FindNotifyByOutTradeNoService findNotifyByOutTradeNoService;


    /**
     * 打开支付页面，正式用
     * @param code
     */
    @RequestMapping(value = "findByOrderCode")
    @ResponseBody
    public JSONObject findByOrderCode(@RequestParam("code")String code){
        JSONObject jsonObject = new JSONObject();
        try {
            List<WxNotifyLog> wxNotifyLogList = findNotifyByOutTradeNoService.find(code);
            jsonObject.put("state", 0);
            jsonObject.put("notifyList", wxNotifyLogList);
        }catch (Exception e){
            jsonObject.put("state", 1);
        }
        return jsonObject;
    }
}
