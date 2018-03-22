package com.zs.service.placeorder.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.FindPlaceOrderListByOrderCodeService;
import com.zs.tools.DateJsonValueProcessorTools;

@Service("findPlaceOrderListByOrderCodeService")
public class FindPlaceOrderListByOrderCodeServiceImpl extends EntityServiceImpl implements FindPlaceOrderListByOrderCodeService{

	 @Resource
	 private FindListByWhereDAO findPlaceOrderListByOrderIdDAOImpl;
	 @Resource
	 private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;
	 

	    @Override
	    @Transactional
		/**
		 * isGLTM 如果传0，直接通过tmid关联；如果传1，通过课程编号关联。
		 */
	    public Map<String, Object> getPlaceOrderListByOrderCode(String orderCode, int isGLTM) {
			Map<String, Object> map = new HashMap<String, Object>();
	        Map<String, String> params = new HashMap<String, String>();
			double sumTotalPrice = 0;
			List<PlaceOrderDetailShow> placeOrderDetailShowList = new ArrayList<PlaceOrderDetailShow>();
	        TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.findPlaceOrderByCode(orderCode);
			if(null == teachMaterialPlaceOrder){
				throw new BusinessException("没有找到订单信息");
			}
	        params.put("orderId", String.valueOf(teachMaterialPlaceOrder.getId()));
			params.put("state", teachMaterialPlaceOrder.getOrderStatus());
			params.put("isGLTM", isGLTM+"");
	        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
	        sortMap.put("t.courseCode", true);
	        List<Object[]> resultList = findPlaceOrderListByOrderIdDAOImpl.findListByWhere(params, sortMap);
	        if(null != resultList && 0 < resultList.size()){
	            for(Object[] objs : resultList){
					float price = null == objs[6] ? 0 : Float.parseFloat(objs[6].toString());
					int count = null == objs[7] ? 0 : Integer.parseInt(objs[7].toString());
					PlaceOrderDetailShow placeOrderDetailShow = new PlaceOrderDetailShow();
					placeOrderDetailShow.setPlaceOrderDetailId(Long.parseLong(objs[0].toString()));
					placeOrderDetailShow.setOrderId(Long.parseLong(objs[1].toString()));
					placeOrderDetailShow.setCourseCode(objs[2].toString());
					placeOrderDetailShow.setCourseName(objs[3].toString());
					placeOrderDetailShow.setMaterialId(Long.parseLong(objs[4].toString()));
					placeOrderDetailShow.setMaterialName(objs[5].toString());
					placeOrderDetailShow.setMaterialPrice(price);
					placeOrderDetailShow.setCount(count);
					placeOrderDetailShow.setCreator(objs[8].toString());
					placeOrderDetailShow.setCreateTime((Date) objs[9]);
					placeOrderDetailShow.setPackageId(teachMaterialPlaceOrder.getPackageId());
					placeOrderDetailShowList.add(placeOrderDetailShow);
					double totalPrice = new BigDecimal(count).multiply(new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					sumTotalPrice = new BigDecimal(sumTotalPrice).add(new BigDecimal(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	            }
	        }
			map.put("sumTotalPrice", sumTotalPrice);
			map.put("data", placeOrderDetailShowList);
	        return map;
	    }

}
