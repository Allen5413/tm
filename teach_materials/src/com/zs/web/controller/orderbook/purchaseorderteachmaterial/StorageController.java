package com.zs.web.controller.orderbook.purchaseorderteachmaterial;

import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.StorageService;
import com.zs.tools.UserTools;
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
 * Created by Allen on 2015/5/23.
 */
@Controller
@RequestMapping(value = "/storage")
public class StorageController extends
        LoggerController<PurchaseOrderTeachMaterial, StorageService> {
    private static Logger log = Logger.getLogger(StorageController.class);

    @Resource
    private StorageService storageService;

    @RequestMapping(value = "doStorage")
    @ResponseBody
    public JSONObject doStorage(@RequestParam(value="ids") Long[] ids,
                                @RequestParam(value="counts") String[] counts,
                                @RequestParam(value="channelId") Long channelId,
                                HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            storageService.storage(ids, counts, channelId, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "订单入库");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
