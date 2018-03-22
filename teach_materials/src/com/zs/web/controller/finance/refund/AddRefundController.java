package com.zs.web.controller.finance.refund;

import com.zs.domain.finance.Refund;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.refund.AddRefundService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
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
import java.util.List;

/**
 * 新增退款申请
 * Created by Allen on 2016/1/5 0005.
 */
@Controller
@RequestMapping(value = "/addRefund")
public class AddRefundController extends LoggerController<Refund, AddRefundService> {
    private static Logger log = Logger.getLogger(AddRefundController.class);

    @Resource
    private AddRefundService addRefundService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;


    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam("spotCode") String spotCode) {
        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
        List<Spec> specList = findSpecService.getAll();
        List<Level> levelList = findLevelService.getAll();
        request.setAttribute("spot", spot);
        request.setAttribute("specList", specList);
        request.setAttribute("levelList", levelList);
        return "finance/refundAdd";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          @RequestParam("studentMoneyDetails") String studentMoneyDetails,
                          @RequestParam("bankName") String bankName,
                          @RequestParam("bankCode") String bankCode,
                          @RequestParam("spotCode") String spotCode,
                          @RequestParam("company") String company){
        JSONObject jsonObject = new JSONObject();
        try{
            addRefundService.add(studentMoneyDetails, bankName, bankCode, spotCode, company, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "提交退款申请");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
