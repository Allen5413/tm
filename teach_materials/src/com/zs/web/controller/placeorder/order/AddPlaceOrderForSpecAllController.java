package com.zs.web.controller.placeorder.order;

import com.zs.config.UserTypeEnum;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.AddPlaceOrderForSpecAllService;
import com.zs.service.placeorder.placeorder.DelPlaceOrderByCodeService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotService;
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
 * Created by Allen on 2016/11/9.
 */
@Controller
@RequestMapping(value = "/addPlaceOrderForSpecAll")
public class AddPlaceOrderForSpecAllController extends LoggerController {
    private static Logger log = Logger.getLogger(EditPlaceOrderForAddressController.class);

    @Resource
    private AddPlaceOrderForSpecAllService addPlaceOrderForSpecAllService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        request.setAttribute("specList", findSpecService.getAll());
        request.setAttribute("levelList", findLevelService.getAll());
        return this.setSearchData(request, "placeOrderAddForAll");
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(@RequestParam("spotCode") String spotCode,
                          @RequestParam("address") String address,
                          @RequestParam("adminName") String adminName,
                          @RequestParam("phone") String phone,
                          @RequestParam(value = "tel", required = false, defaultValue = "") String tel,
                          @RequestParam(value = "postalCode", required = false, defaultValue = "") String postalCode,
                          @RequestParam("enYear") String enYear,
                          @RequestParam("enQuarter") String enQuarter,
                          @RequestParam("specCode") String specCode,
                          @RequestParam("levelCode") String levelCode,
                          @RequestParam("personNum") int personNum,
                          @RequestParam("courseCodes") String courseCodes,
                          HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        try{
            addPlaceOrderForSpecAllService.add(spotCode, address, adminName, phone, tel, postalCode, enYear, enQuarter, specCode, levelCode, personNum, courseCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e){
            String msg = super.outputException(request, e, log, "订专业全部教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 根据不同的用户类型，设置页面上的查询数据。
     * 如果是省中心用户，只能查看自己下面的学习中心的学生订单，
     * 如果是学习中心用户，只能查看自己中心的学生订单，
     * 管理员可以查所有的
     * @param request
     */
    private String setSearchData(HttpServletRequest request, String pageName){
        String returnPath = "";
        //获取学习中心数据
        List<Spot> spotList = null;

        //判断用户类型，如果是省中心用户，只能查看自己下面的学习中心的学生订单，如果是学习中心用户，只能查看自己中心的学生订单
        String loginType = UserTools.getLoginUserForLoginType(request);
        if(loginType.equals(UserTypeEnum.ADMIN.getValue())){
            spotList = findSpotService.getAll();
        }
        if(loginType.equals(UserTypeEnum.PROVINCE.getValue())){
            returnPath = "provPages/";
        }
        if(loginType.equals(UserTypeEnum.SPOT.getValue())){
            //查询登录学习中心用户
            Spot spot = findSpotByCodeService.getSpotByCode(UserTools.getLoginUserForSpotCode(request));
            request.setAttribute("spot", spot);
            returnPath = "spotPages/";
        }

        request.setAttribute("spotList", spotList);
        return returnPath+"placeOrder/"+pageName;
    }
}
