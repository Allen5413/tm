package com.zs.web.controller.finance.spotexpensepay;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.spotexpense.SpotExpenseOthService;
import com.zs.service.finance.spotexpensepay.AddSpotExpensePayService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LihongZhang on 2015/5/18.
 */
@Controller
@RequestMapping(value = "/addSpotEP")
public class AddSpotExpensePayController extends LoggerController<SpotExpensePay,AddSpotExpensePayService> {
    private static Logger log = Logger.getLogger(AddSpotExpensePayController.class);

    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private SpotExpenseOthService spotExpenseOthService;


    @RequestMapping(value = "openAddSpotEPPage")
    public String openPage(HttpServletRequest request,
                           @RequestParam("spotCode") String spotCode){

        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
        if(null == spot){
            throw new BusinessException("没有找到编号为["+spotCode+"]的学习中心信息");
        }
        request.setAttribute("spot", spot);
        return "finance/spotEPAdd";
    }


    @RequestMapping(value = "spotEPAdd")
    @ResponseBody
    public JSONObject addSpotEP(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            String spotCode = request.getParameter("spotCode");
            String money = request.getParameter("money");
            String payWay = request.getParameter("payWay");
            String remark = request.getParameter("remark");
            MultipartRequest mulReu = (MultipartRequest)request;
            //处理上传图片
            String imagUrl = UpLoadFileTools.uploadImg(request, mulReu.getFiles("payImg"), "jpg|png|jpeg", 400, 10, "/spotPayImg");
            spotExpenseOthService.saveTempPay(spotCode + "$" + spotCode + "$" + money, "-1", UserTools.getLoginUserForName(request), money, imagUrl, spotCode, payWay, remark);
            jsonObject.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request, e, log, "添加学习中心入账记录失败");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
