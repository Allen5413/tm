package com.zs.web.controller.placeorder.order;

import com.zs.config.UserTypeEnum;
import com.zs.dao.sync.beginschedule.BeginScheduleDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sync.BeginScheduleBean;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.FindPlaceOrderListByOrderCodeService;
import com.zs.service.placeorder.PlaceOrderService;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import com.zs.service.placeorder.bean.PlaceOrderShow;
import com.zs.service.sync.beginSchedule.BeginScheduleService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/30.
 */
@Controller
@RequestMapping(value = "/placeOrder")
public class PlaceOrderController extends LoggerController {

    private static Logger log = Logger.getLogger(PlaceOrderController.class);

    @Resource
    private BeginScheduleService beginScheduleService;
    @Resource
    private PlaceOrderService placeOrderService;
    @Resource
    private BeginScheduleDAO beginScheduleDAO;
    @Resource
    private FindPlaceOrderListByOrderCodeService findPlaceOrderListByOrderCodeService;
    @Resource
    private FindSpotService findSpotService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @RequestMapping(value = "open")
    public String initBeginSchdeu(HttpServletRequest request){
        request.setAttribute("enYearList", beginScheduleDAO.queryEnYear());
        request.setAttribute("specList", beginScheduleDAO.querySpec());
        request.setAttribute("levelList", beginScheduleDAO.queryLevel());
        return this.setSearchData(request, "courseScheduList");
    }

    @RequestMapping(value = "queryBeginSchdeu")
    public String queryBeginSchdeu(HttpServletRequest request){

        String enYear = request.getParameter("enYear");
        String enQuarter = request.getParameter("enQuarter");
        String specCode = request.getParameter("specCode");
        String levelCode = request.getParameter("levelCode");
        String spotCode = request.getParameter("spotCode");
        if(StringUtils.isEmpty(spotCode)){
            spotCode = UserTools.getLoginUserForSpotCode(request);
        }

        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);

        Map<String,String> paramsMap = new HashMap<String,String>();
        paramsMap.put("enYear", enYear);
        paramsMap.put("enQuarter", enQuarter);
        paramsMap.put("specCode", specCode);
        paramsMap.put("levelCode", levelCode);

        List<BeginScheduleBean> beginScheduleList = this.beginScheduleService.queryBeginScheduleOnGroup(paramsMap);

        request.setAttribute("beginEduList", beginScheduleList);
        request.setAttribute("enYearList", beginScheduleDAO.queryEnYear());
        request.setAttribute("specList", beginScheduleDAO.querySpec());
        request.setAttribute("levelList", beginScheduleDAO.queryLevel());
        request.setAttribute("spot", spot);
        return this.setSearchData(request, "courseScheduList");
    }

    @RequestMapping(value = "createPlaceOrder")
    public String createPlaceOrder(HttpServletRequest request){
        String enYear = request.getParameter("enYear");
        String enQuarter = request.getParameter("enQuarter");
        String specCode = request.getParameter("specCode");
        String levelCode = request.getParameter("levelCode");
        String spotCode = request.getParameter("spotCode");
        String address = request.getParameter("address");
        String adminName = request.getParameter("adminName");
        String phone = request.getParameter("phone");
        String tel = request.getParameter("tel");
        String postalCode = request.getParameter("postalCode");

        if(StringUtils.isEmpty(spotCode)) {
            spotCode = UserTools.getLoginUserForSpotCode(request);
        }

        String loginUserName = UserTools.getLoginUserForName(request);
        String personNumStr = StringUtils.isEmpty(request.getParameter("personNum")) ? "0" : request.getParameter("personNum");

        try {
            //生成订单
            TeachMaterialPlaceOrder teachMaterialPlaceOrder =
                    placeOrderService.createPlaceOrder(spotCode, enYear, enQuarter, specCode, levelCode, loginUserName, Integer.parseInt(personNumStr), address, adminName, phone, tel, postalCode);
            //查询订单明细
            Map<String, Object> map = null;
            List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
            map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(teachMaterialPlaceOrder.getOrderCode(), 0);
            if(null != map){
                placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
            }

            request.setAttribute("teachMaterialPlaceOrder", teachMaterialPlaceOrder);
            request.setAttribute("placeOrderDetailShowList", placeOrderDetailShowList);
        } catch (Exception e) {
            outputException(request,e,log,"系统异常!!");
            return "error";
        }
        return this.setSearchData(request, "orderDetailPgae");
    }

    @RequestMapping(value = "updatePlaceOrder")
    @ResponseBody
    public JSONObject updatePlaceOrder(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            String material_order_str = request.getParameter("material_order_str");
            String placeOrderId = request.getParameter("placeOrderId");
            placeOrderService.updatePlaceOrder(Long.parseLong(placeOrderId), material_order_str, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "修改教材数量");
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
            returnPath = "spotPages/";
        }

        request.setAttribute("spotList", spotList);
        return returnPath+"placeOrder/"+pageName;
    }
}
