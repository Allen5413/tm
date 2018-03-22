package com.zs.service.placeorder;

import java.sql.Timestamp;
import java.util.Map;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.bean.PlaceOrderShow;

/**
 * 
 * @author yanghaosen
 *
 */
public interface PlaceOrderService extends EntityService {
	
	/**
	 * 生成预订单
	 * @param spotCode
	 * @param enYear
	 * @param enQuarter
	 * @param specCode
	 * @param levelCode
	 * @param loginUserName
	 */
	public TeachMaterialPlaceOrder createPlaceOrder(String spotCode,String enYear,String enQuarter,String specCode,String levelCode,String loginUserName,int personNumStr,String address,String adminName,String phone,String tel,String postalCode) throws Exception;
	
	/**
	 * 修改预订单数量
	 * @param placeOrderId 预订单ID
	 * @param orderMaterialId_count_str 预订单明细数据ID数量字符串,格式为1_3a2_9a3_6
	 * @return
	 */
	public void updatePlaceOrder(Long placeOrderId,String orderMaterialId_count_str, String loginName);

	/**
	 * 修改预订单状态
	 * @param orderCode
	 * @param packageId
	 * @param status
	 * @param loginName
	 * @throws Exception
	 */
	public void editSpotOrderForState(String orderCode, Long packageId, String status, Timestamp operateTime, String loginName)throws Exception;
	
	public PageInfo queryPlaceOrderPackageCount(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap);
	
	public PlaceOrderPackage addPlaceOrderPackage(String spotCode, String orderCodes, String logisticCodes, String loginName) throws Exception;

	/**
	 * 刷新预订单
	 * @param id
	 * @param loginName
	 * @throws Exception
	 */
	public void refreshPlaceOrder(long id, String loginName)throws Exception;

	/**
	 * 刷新所有库存不足预订单
	 * @param loginName
	 * @throws Exception
	 */
	public void refreshPlaceOrderAll(String loginName)throws Exception;

	/**
	 * 拆分预订单
	 * @param id
	 * @param loginName
	 * @throws Exception
	 */
	public void splitPlaceOrder(long id, String loginName, boolean flag)throws Exception;

	/**
	 * 拆分所有库存不足预订单
	 * @param loginName
	 * @throws Exception
	 */
	public void splitPlaceOrderAll(String spotCode, String loginName)throws Exception;

	/**
	 * 预订单邮寄
	 * @param ids
	 * @param logisticCodes
	 * @param loginName
	 * @throws Exception
	 */
	public void sendPlaceOrderPackage(Long[] ids, String[] logisticCodes, String loginName) throws Exception;


	/**
	 * 修改已发出的预订单中的教材的价格
	 * @param paramsMap
	 * @param sortMap
	 * @param loginName
	 * @throws Exception
	 */
	public void editSendStudentBookOrderTMPrice(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName)throws Exception;

	/**
	 * 修改已发出的预订单中的教材的数量
	 * @param paramsMap
	 * @param sortMap
	 * @param loginName
	 * @throws Exception
	 */
	public void editSendStudentBookOrderTMCount(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName)throws Exception;
}
