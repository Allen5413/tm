package com.zs.web.controller.placeorder.ordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.kuaidi.push.FindKuaidiPushByNuService;
import com.zs.service.placeorder.*;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/findPlaceOrderListByOrderCode")
public class FindPlaceOrderListByOrderCodeController extends LoggerController{
	private static Logger log = Logger.getLogger(FindPlaceOrderListByOrderCodeController.class);
	
	@Resource
	private FindPlaceOrderListByOrderCodeService findPlaceOrderListByOrderCodeService;
    @Resource
    private FindPlaceOrderTMListForManualByOrderCodeService findPlaceOrderTMListForManualByOrderCodeService;
	@Resource
	private SpotDAO spotDAO;
    @Resource
    private PlaceOrderPackageService placeOrderPackageService;
    @Resource
    private FindKuaidiPushByNuService findKuaidiPushByNuService;
    @Resource
    private FindPlaceOrderByCodeService findPlaceOrderByCodeService;
	
	@RequestMapping(value = "doFindPlaceOrderListByOrderCode")
    public String doFindPlaceOrderListByOrderCode(@RequestParam(value="spotCode") String spotCode,
                                                  @RequestParam(value="orderCode") String orderCode,
                                                  @RequestParam(value="isAuto") String isAuto,
                                                  @RequestParam(value="state") int state,
                                                  HttpServletRequest request){
        try{
            //查询订单信息
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = findPlaceOrderByCodeService.findByCode(orderCode);
            if(null == teachMaterialPlaceOrder){
                throw new BusinessException("没有找到订单信息");
            }

            Map<String, Object> map = null;
            double sumTotalPrice = 0;
            List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
            if ("0".equals(isAuto) && 4 > state) {
                map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(orderCode, 0);
            } else {
                map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(orderCode);
            }
            if(null != map){
                placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
                sumTotalPrice = (Double)map.get("sumTotalPrice");
            }
            if(null != placeOrderDetailShowList && 0 < placeOrderDetailShowList.size()){
                PlaceOrderDetailShow placeOrderDetailShow = placeOrderDetailShowList.get(0);
                if(null != placeOrderDetailShow.getPackageId()) {
                    //查询订单大包信息
                    PlaceOrderPackage placeOrderPackage = placeOrderPackageService.get(placeOrderDetailShow.getPackageId());
                    if(!StringUtils.isEmpty(placeOrderPackage.getLogisticCode())){
                        String[] nus = placeOrderPackage.getLogisticCode().split(",");
                        if(null != nus && 0 < nus.length) {
                            //查询快递信息
                            Map<String, JSONArray> kuaidiMap = new HashMap<String, JSONArray>();
                            for(String nu : nus) {
                                JSONArray jsonArray = findKuaidiPushByNuService.find(nu);
                                kuaidiMap.put(nu, jsonArray);
                            }
                            request.setAttribute("kuaidiMap", kuaidiMap);
                        }
                    }
                }
            }

            request.setAttribute("address", teachMaterialPlaceOrder.getAddress());
            request.setAttribute("adminName", teachMaterialPlaceOrder.getAdminName());
            request.setAttribute("placeOrderDetailShowList", placeOrderDetailShowList);
            request.setAttribute("sumTotalPrice", sumTotalPrice);
            request.setAttribute("spot", spotDAO.querySpotByCode(spotCode));
            return "placeOrder/placeOrderTmList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询预订单的教材明细");
            return "error";
        }
    }

    @RequestMapping(value = "print")
    public String print(@RequestParam(value="spotCode") String spotCode,
                                                  @RequestParam(value="orderCode") String orderCode,
                                                  @RequestParam(value="isAuto") String isAuto,
                                                  @RequestParam(value="state") int state,
                                                  HttpServletRequest request){
        try{
            //查询订单信息
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = findPlaceOrderByCodeService.findByCode(orderCode);
            if(null == teachMaterialPlaceOrder){
                throw new BusinessException("没有找到订单信息");
            }

            Map<String, Object> map = null;
            List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
            double sumTotalPrice = 0;
            if ("0".equals(isAuto) && 4 > state) {
                map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(orderCode, 0);
            } else {
                map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(orderCode);
            }

            if(null != map){
                placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
                sumTotalPrice = (Double)map.get("sumTotalPrice");
            }

            request.setAttribute("address", teachMaterialPlaceOrder.getAddress());
            request.setAttribute("adminName", teachMaterialPlaceOrder.getAdminName());
            request.setAttribute("placeOrderDetailShowList", placeOrderDetailShowList);
            request.setAttribute("sumTotalPrice", sumTotalPrice);
            request.setAttribute("spot", spotDAO.querySpotByCode(spotCode));
            return "placeOrder/printPlaceOrderTm";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印预订单的教材明细");
            return "error";
        }
    }
}
