package com.zs.web.controller.placeorder.orderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.zs.dao.basic.semester.SemesterDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.PlaceOrderService;
import com.zs.service.placeorder.bean.PlaceOrderStatus;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import com.zs.web.controller.sale.studentbookorderpackage.AddStudentBookOrderPackageController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yanghaosen
 *
 */
 @Controller
 @RequestMapping(value = "/placeOrderPackage")
public class PlaceOrderPackageController extends LoggerController{
	 
	 private static Logger log = Logger.getLogger(AddStudentBookOrderPackageController.class);
	 
	 @Resource
	 private FindSpotService findSpotService;
	 @Resource
	 private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;
	 @Resource
	 private SpotDAO spotDAO;
	 @Resource
	 private PlaceOrderService placeOrderService;
	 @Resource
	 private SemesterDAO semesterDAO;
	 
	 /**
	  * 转至预订单打包页面
	  * @return
	  */
	 @RequestMapping(value = "toPlaceOrderPackagePage")
	 public String toPlaceOrderPackagePage(HttpServletRequest request){
		List<Spot> spotList = findSpotService.getAll();
	    request.setAttribute("spotList", spotList);
		return "placeOrder/placeOrderPackagePage";
	 }

    /**
     * 转至预订单打包页面（合并多个订单打一个包）
     * @return
     */
    @RequestMapping(value = "toMergePlaceOrderPackagePage")
    public String toMergePlaceOrderPackagePage(HttpServletRequest request){
        List<Spot> spotList = findSpotService.getAll();
        request.setAttribute("spotList", spotList);
        return "placeOrder/mergePlaceOrderPackagePage";
    }
	 
	 /**
     * 查询预订单中心
     * @param request
     * @return
     */
    @RequestMapping(value = "searchOrderSpot")
    @ResponseBody
    public JSONObject searchOrderStudent(HttpServletRequest request,@RequestParam("orderCode") String orderCode,@RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            if(StringUtils.isEmpty(orderCode)) {
                throw new BusinessException("没有传入订单编号");
            }
            //查询订单信息
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.findPlaceOrderByCode(orderCode);
            if(null == teachMaterialPlaceOrder){
                throw new BusinessException("没有找到订单信息");
            }
            if(null != teachMaterialPlaceOrder.getPackageId() && 0 < teachMaterialPlaceOrder.getPackageId()){
                throw new BusinessException("中心编码："+teachMaterialPlaceOrder.getSpotCode()+", 订单号："+orderCode+", 已经打包");
            }
            if(!PlaceOrderStatus.DISPATCH.getCode().equals(teachMaterialPlaceOrder.getOrderStatus())){
                throw new BusinessException("中心编码："+teachMaterialPlaceOrder.getSpotCode()+", 订单号："+orderCode+", 状态不能扫描");
            }

            if(!teachMaterialPlaceOrder.getSpotCode().equals(spotCode)){
                throw new BusinessException("该订单不是该学习中心的");
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "查询订单信息");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
    
    /**
     * 查询学习中心已打包预订单数量
     * @param request
     * @return
     */
    @RequestMapping(value = "searchPackCount")
    @ResponseBody
    public JSONObject searchPackCount(HttpServletRequest request, @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            int orderCount = 0;
            int packageCount = 0;
            if(StringUtils.isEmpty(spotCode)) {
                throw new BusinessException("没有传入学习中心编号");
            }
            //查询学习中心
            Spot spot = spotDAO.querySpotByCode(spotCode);
            if(null == spot){
                throw new BusinessException("没有找到学习中心信息");
            }

            //获取当前学期
            Semester semester = semesterDAO.queryLocalSemester();
            Map<String,String> params = new HashMap<String, String>();
            params.put("semesterId", semester.getId()+"");
            params.put("spotCode", spotCode);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(999999);
            pageInfo = placeOrderService.queryPlaceOrderPackageCount(pageInfo, params, null);
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                for(int i=0; i<pageInfo.getPageResults().size(); i++){
                    JSONObject json = (JSONObject) pageInfo.getPageResults().get(i);
                    String logisticCodes = json.get("logisticCode").toString();
                    orderCount += Integer.parseInt(json.get("orderCount").toString());
                    packageCount += null == logisticCodes ? 1 : logisticCodes.split(",").length;
                }
            }
            jsonObject.put("state", 0);
            jsonObject.put("packageCount", packageCount);
            jsonObject.put("orderCount", orderCount);
            jsonObject.put("spot", spot);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "查询学习中心已打包数量");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
    
    /**
     * 订单打包,（一个订单发多个包）
     * @param request
     * @return
     */
    @RequestMapping(value = "addPlacePackage")
    @ResponseBody
    public JSONObject addPlacePackage(HttpServletRequest request,
                                  @RequestParam("orderCode") String orderCode,
                                  @RequestParam("logisticCodes") String logisticCodes,
                                  @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            PlaceOrderPackage placeOrderPackage = this.placeOrderService.addPlaceOrderPackage(spotCode, orderCode, logisticCodes, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
            jsonObject.put("packageId", placeOrderPackage.getId());
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "单个扫描结束，打印清单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }

    /**
     * 订单打包（一个包发多个订单）
     * @param request
     * @return
     */
    @RequestMapping(value = "addMergePlacePackage")
    @ResponseBody
    public JSONObject addMergePlacePackage(HttpServletRequest request,
                                      @RequestParam("orderCodes") String orderCodes,
                                      @RequestParam("logisticCode") String logisticCode,
                                      @RequestParam("spotCode") String spotCode){
        JSONObject jsonObject = new JSONObject();
        try{
            PlaceOrderPackage placeOrderPackage = this.placeOrderService.addPlaceOrderPackage(spotCode, orderCodes, logisticCode, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
            jsonObject.put("packageId", placeOrderPackage.getId());
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "合并扫描结束，打印清单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
