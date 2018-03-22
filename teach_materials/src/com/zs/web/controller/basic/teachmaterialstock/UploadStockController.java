package com.zs.web.controller.basic.teachmaterialstock;

import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.UploadStockService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/5/6.
 */
@Controller
@RequestMapping(value = "/uploadStock")
public class UploadStockController extends LoggerController<TeachMaterialStock, UploadStockService> {
    private static Logger log = Logger.getLogger(UploadStockController.class);

    @Resource
    private UploadStockService uploadStockService;


    @RequestMapping(value = "open")
    public String open() {
        return "teachMaterial/stockUpload";
    }

    @RequestMapping(value = "stockUpload")
    @ResponseBody
    public JSONObject applyUpload(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            String str = uploadStockService.upload(request);
            jsonObject.put("state", 0);
            jsonObject.put("str", str);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "上传批量库存");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
