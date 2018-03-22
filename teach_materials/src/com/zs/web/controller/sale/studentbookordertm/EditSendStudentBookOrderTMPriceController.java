package com.zs.web.controller.sale.studentbookordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.sale.studentbookordertm.EditSendStudentBookOrderTMPriceService;
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
 * Created by Allen on 2015/9/5.
 */
@Controller
@RequestMapping(value = "/editSendStudentBookOrderTMPrice")
public class EditSendStudentBookOrderTMPriceController extends LoggerController {
    private static Logger log = Logger.getLogger(EditSendStudentBookOrderTMPriceController.class);

    @Resource
    private EditSendStudentBookOrderTMPriceService editSendStudentBookOrderTMPriceService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;


    @RequestMapping(value = "open")
    public String open(){
        return "studentOrder/editSendStudentBookOrderTMPrice";
    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(@RequestParam(value="semesterId") String semesterId,
                                          @RequestParam(value="spotCodes", required=false, defaultValue="") String spotCodes,
                                          @RequestParam(value="studentCode", required=false, defaultValue="") String studentCode,
                                          @RequestParam(value="tmId", required=false, defaultValue="") String tmId,
                                          @RequestParam(value="beginDate", required=false, defaultValue="") String beginDate,
                                          @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
                                          @RequestParam(value="price", required=false, defaultValue="") String price,
                                          @RequestParam(value="newPrice", required=false, defaultValue="") String newPrice,
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
            params.put("studentCode", studentCode.trim());
            params.put("tmId", tmId);
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            params.put("price", price);
            params.put("newPrice", newPrice);
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("s.spot_code", true);
            sortMap.put("sbo.student_code", true);

            editSendStudentBookOrderTMPriceService.editSendStudentBookOrderTMPrice(params, sortMap, UserTools.getLoginUserForName(request));

            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改已发出的教材价格");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
