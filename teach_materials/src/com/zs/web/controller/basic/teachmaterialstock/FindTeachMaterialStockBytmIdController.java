package com.zs.web.controller.basic.teachmaterialstock;

import com.zs.service.basic.teachmaterialstock.FindTeachMaterialStockBytmIdService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/20.
 */
@Controller
@RequestMapping(value = "/findtmStockBytmId")
public class FindTeachMaterialStockBytmIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindTeachMaterialStockBytmIdController.class);

    @Resource
    private FindTeachMaterialStockBytmIdService findTeachMaterialStockBytmIdService;

    @RequestMapping(value = "doFindtmStockBytmId")
    public String findtmStockBytmId(@RequestParam("tmId") long tmId, HttpServletRequest request){
        try{
            JSONArray teachMaterialStocks = findTeachMaterialStockBytmIdService.getTeachMaterialStockBytmId(tmId);
            request.setAttribute("teachMaterialStocks", teachMaterialStocks);
        }catch(Exception e){
            super.outputException(request, e, log, "查询教材库存信息");
        }
        return "teachMaterial/tmStockList";
    }
}
