package com.zs.web.controller.basic.stockallotlog;

import com.zs.service.basic.stockallotlog.FindStockAllotLogListBytmIdService;
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
 * Created by Allen on 2015/5/28.
 */
@Controller
@RequestMapping(value = "/findStockAllotLogListBytmId")
public class FindStockAllotLogListBytmIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStockAllotLogListBytmIdController.class);

    @Resource
    private FindStockAllotLogListBytmIdService findStockAllotLogListBytmIdService;

    @RequestMapping(value = "doFindStockAllotLogListBytmId")
    public String findStockAllotLogBytmId(@RequestParam("tmId") long tmId, HttpServletRequest request){
        try{
            JSONArray jsonArray = findStockAllotLogListBytmIdService.getStockAllotLogListBytmId(tmId);
            request.setAttribute("jsonArray", jsonArray);
        }catch(Exception e){
            super.outputException(request, e, log, "查询教材库存调拨记录");
        }
        return "teachMaterial/stockAllotLogList";
    }
}
