package com.zs.web.controller.placeorder.order;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.placeorder.CountSendPlaceOrderByTMIdService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/9/16.
 */
@Controller
@RequestMapping(value = "/countSendPlaceOrderByTMId")
public class CountSendPlaceOrderByTMIdController extends LoggerController {
    private static Logger log = Logger.getLogger(CountSendPlaceOrderByTMIdController.class);

    @Resource
    private CountSendPlaceOrderByTMIdService countSendPlaceOrderByTMIdService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="semesterId") String semesterId,
                                          @RequestParam(value="spotCodes", required=false, defaultValue="") String spotCodes,
                                          @RequestParam(value="tmId", required=false, defaultValue="") String tmId,
                                          @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                                          @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                                          @RequestParam(value="price", required=false, defaultValue="") String price,
                                          HttpServletRequest request){
        try{
            TeachMaterial teachMaterial = findTeachMaterialService.get(Long.parseLong(tmId));
            if(null == teachMaterial){
                throw new BusinessException("没有找到教材信息");
            }
            if(teachMaterial.getState() == TeachMaterial.STATE_DISABLE){
                throw new BusinessException("该教材已被停用");
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put("semesterId", semesterId);
            params.put("spotCodes", spotCodes.trim());
            params.put("tmId", tmId);
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            params.put("price", price);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("tmpo.spot_code", true);
            sortMap.put("tmpo.order_code", true);
            JSONArray jsonArray = countSendPlaceOrderByTMIdService.countSendPlaceOrderByTMId(params, sortMap);

            request.setAttribute("jsonArray", jsonArray);
            request.setAttribute("teachMaterial", teachMaterial);
            return "placeOrder/countSendPlaceOrderByTMIdList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计一个教材已发出的预订单信息");
            return "error";
        }
    }
}
