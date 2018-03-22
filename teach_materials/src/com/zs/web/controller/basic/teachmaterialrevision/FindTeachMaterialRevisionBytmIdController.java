package com.zs.web.controller.basic.teachmaterialrevision;

import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdService;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/findTeachMaterialRevisionBytmId")
public class FindTeachMaterialRevisionBytmIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindTeachMaterialRevisionBytmIdController.class);

    @Resource
    private FindTeachMaterialRevisionBytmIdService findTeachMaterialRevisionBytmIdService;


    @RequestMapping(value = "doFindTeachMaterialRevisionBytmId")
    public String doFindTeachMaterialRevisionBytmId(@RequestParam(value="tmId") Long tmId,HttpServletRequest request){
        try{
            List<TeachMaterialRevision> list = findTeachMaterialRevisionBytmIdService.findTeachMaterialRevisionBytmId(tmId);
            request.setAttribute("list", list);
            return "teachMaterial/tmRevisionList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查看教材版次信息");
            return "error";
        }
    }
}