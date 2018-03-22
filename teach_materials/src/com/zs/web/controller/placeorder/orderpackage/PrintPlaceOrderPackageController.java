package com.zs.web.controller.placeorder.orderpackage;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.placeorder.PlaceOrderPackageDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.PlaceOrderService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/printPlaceOrderPackage")
public class PrintPlaceOrderPackageController extends LoggerController{
	
	private static Logger log = Logger.getLogger(PrintPlaceOrderPackageController.class);

	@Resource
	private PlaceOrderPackageDAO placeOrderPackageDAO;
	@Resource
	private SpotDAO spotDAO;
	@Resource
	private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;
	
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam("id") long id){
        try {
            //查询教材包
            PlaceOrderPackage placeOrderPackage = placeOrderPackageDAO.get(id);
            if (null == placeOrderPackage) {
                throw new BusinessException("没有找到教材包");
            }
            if(StringUtils.isEmpty(placeOrderPackage.getLogisticCode())){
                throw new BusinessException("该教材包没有快递编号");
            }
            //查找教材包所属学习中心
            Spot spot = spotDAO.querySpotByCode(placeOrderPackage.getSpotCode());
            //查询教材包下的订单
            List<TeachMaterialPlaceOrder> placeOrderList = teachMaterialPlaceOrderDAO.findPlaceOrderByPackageId(placeOrderPackage.getId());
            String[] logisticCodes = placeOrderPackage.getLogisticCode().split(",");
            request.setAttribute("placeOrderPackage", placeOrderPackage);
            request.setAttribute("placeOrderList", placeOrderList);
            request.setAttribute("logisticCodes", logisticCodes);
            request.setAttribute("spot", spot);
            request.setAttribute("logisticCodesLength", logisticCodes.length);
        }catch(Exception e){
            super.outputException(request, e, log, "教材包打印");
            return "error";
        }
        return "placeOrder/placeOrderPackagePrint";
    }

}
