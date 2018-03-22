package com.zs.web.controller.placeorder.ordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.placeorder.PlaceOrderService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/9/16.
 */
@Controller
@RequestMapping(value = "/editSendPlaceOrderTMCount")
public class EditSendPlaceOrderTMCountController extends LoggerController {
    private static Logger log = Logger.getLogger(EditSendPlaceOrderTMCountController.class);

    @Resource
    private PlaceOrderService placeOrderService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;


    @RequestMapping(value = "open")
    public String open(){
        return "placeOrder/editSendPlaceOrderTMCount";
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(@RequestParam(value="semesterId") String semesterId,
                             @RequestParam(value="spotCodes", required=false, defaultValue="") String spotCodes,
                             @RequestParam(value="tmId", required=false, defaultValue="") String tmId,
                             @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                             @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                             @RequestParam(value="price", required=false, defaultValue="") String price,
                             @RequestParam(value="newCount", required=false, defaultValue="") String newCount,
                             @RequestParam(value="orderCode", required=false, defaultValue="") String orderCode,
                             HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
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
            params.put("newCount", newCount);
            params.put("orderCode", orderCode);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("tmpo.spot_code", true);
            sortMap.put("tmpo.order_code", true);

            placeOrderService.editSendStudentBookOrderTMCount(params, sortMap, UserTools.getLoginUserForName(request));

            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改已发出的教材数量");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
