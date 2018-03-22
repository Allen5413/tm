package com.zs.service.placeorder.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;

import com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages_sv;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialByCourseCodeDAO;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialFromSetTMByCourseCodeDAO;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.placeorder.*;
import com.zs.dao.sale.studentbookordertm.FindTMCountForConfirmOrderDAO;
import com.zs.dao.sync.beginschedule.FindBeginScheduleForPlaceOrderDAO;
import com.zs.domain.basic.*;
import com.zs.domain.finance.SpotExpenseBuy;
import com.zs.domain.sync.BeginSchedule;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.finance.spotexpensebuy.AddSpotExpenseBuyService;
import com.zs.service.placeorder.*;
import com.zs.tools.HttpRequestTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.SemesterDAO;
import com.zs.dao.placeorder.impl.FindPlaceOrderPackageByWhereDAOImpl;
import com.zs.domain.placeorder.PlaceOrderLog;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import com.zs.service.placeorder.bean.PlaceOrderStatus;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;

/**
 * 
 * @author yanghaosen
 *
 */
@Service("placeOrderService")
public class PlaceOrderServiceImpl extends EntityServiceImpl implements PlaceOrderService{
	
	@Resource
	private PlaceOrderDAO placeOrderDAO;
	@Resource
	private SemesterDAO semesterDAO;
	@Resource
	private FindNowSemesterDAO findNowSemesterDAO;
	@Resource
	private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;
	@Resource
	private PlaceOrderTeachMaterialDAO placeOrderTeachMaterialDAO;
	@Resource
	private PlaceOrderLogDAO placeOrderLogDAO;
	@Resource
	private FindPlaceOrderPackageByWhereDAOImpl findPlaceOrderPackageByWhereDAOImpl;
	@Resource
	private FindPlaceOrderPackageForMaxCodeDAO findPlaceOrderPackageForMaxCodeDAO;
	@Resource
	private FindPlaceOrderPackageForMaxSortNotSendDAO findPlaceOrderPackageForMaxSortNotSendDAO;
	@Resource
	private PlaceOrderPackageDAO placeOrderPackageDAO;
	@Resource
	private FindPlaceOrderByCodeDAO findPlaceOrderByCodeDAO;
	@Resource
	private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
	@Resource
	private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
	@Resource
	private FindPlaceOrderListByOrderCodeService findPlaceOrderListByOrderCodeService;
	@Resource
	private FindTMCountForConfirmOrderDAO findTMCountForConfirmOrderDAO;
	@Resource
	private FindTMCountForConfirmPlaceOrderDAO findTMCountForConfirmPlaceOrderDAO;
	@Resource
	private FindPlaceOrderByPackageIdDAO findPlaceOrderByPackageIdDAO;
	@Resource
	private FindBeginScheduleForPlaceOrderDAO findBeginScheduleForPlaceOrderDAO;
	@Resource
	private FindTeachMaterialByCourseCodeDAO findTeachMaterialByCourseCodeDAO;
	@Resource
	private FindTeachMaterialFromSetTMByCourseCodeDAO findTeachMaterialFromSetTMByCourseCodeDAO;
	@Resource
	private FindPlaceOrderTMListForManualByOrderCodeService findPlaceOrderTMListForManualByOrderCodeService;
	@Resource
	private FindTeachMaterialService findTeachMaterialService;
	@Resource
	private AddSpotExpenseBuyService addSpotExpenseBuyService;
	@Resource
	private FindPlaceOrderPageByWhereService findPlaceOrderPageByWhereService;
	@Resource
	private CountSendPlaceOrderByTMIdService countSendPlaceOrderByTMIdService;
	@Resource
	private TeachMaterialDAO teachMaterialDAO;
	
	@Override
    @Transactional
	public TeachMaterialPlaceOrder createPlaceOrder(String spotCode, String enYear,String enQuarter, String specCode, String levelCode,String loginUserName, int personNumStr, String address, String adminName, String phone, String tel, String postalCode) throws Exception{
		try {
			TeachMaterialPlaceOrder teachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
			//查询学习中心对应的渠道
			IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(spotCode);
			if(null == issueRange){
				throw new BusinessException("该学习中心没有对应发行渠道，不能生成预订单");
			}
			//查询出当前学期
			Semester localSemester = semesterDAO.queryLocalSemester();

			//查出当前学期的对应入学年，入学季，专业，层次的开课计划教材
			List<Object[]> beginScheduleList = findBeginScheduleForPlaceOrderDAO.findBeginScheduleForPlaceOrder(localSemester.getYear(), localSemester.getQuarter(), Integer.parseInt(enYear), Integer.parseInt(enQuarter), specCode, levelCode);

			if(null != beginScheduleList && beginScheduleList.size() > 0){
				synchronized (new Object()) {
					//创建订单号
					String maxOrderCode = this.placeOrderDAO.queryMaxOrderNumber(spotCode, localSemester.getId());
					if(null == maxOrderCode){
						maxOrderCode = "0";
					}else{
						maxOrderCode = maxOrderCode.substring(maxOrderCode.length() - 6,maxOrderCode.length());
					}
					String orderCode = OrderCodeTools.createSpotOrderCode(localSemester.getYear(), localSemester.getQuarter(), spotCode, Integer.parseInt(maxOrderCode) + 1);				
					//存储预订单数据
					teachMaterialPlaceOrder.setCreateTime(DateTools.getLongNowTime());
					teachMaterialPlaceOrder.setCreator(loginUserName);
					teachMaterialPlaceOrder.setOperator(loginUserName);
					teachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
					teachMaterialPlaceOrder.setEnQuarter(enQuarter);
					teachMaterialPlaceOrder.setEnYear(enYear);
					teachMaterialPlaceOrder.setLevelCode(levelCode);
					teachMaterialPlaceOrder.setOrderCode(orderCode);
					teachMaterialPlaceOrder.setOrderStatus(TeachMaterialPlaceOrder.STATE_CONFIRMED);
					teachMaterialPlaceOrder.setSemesterId(localSemester.getId());
					teachMaterialPlaceOrder.setSpecCode(specCode);
					teachMaterialPlaceOrder.setSpotCode(spotCode);
					teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
					teachMaterialPlaceOrder.setAddress(address);
					teachMaterialPlaceOrder.setAdminName(adminName);
					teachMaterialPlaceOrder.setPhone(phone);
					teachMaterialPlaceOrder.setTel(tel);
					teachMaterialPlaceOrder.setPostalCode(postalCode);
					teachMaterialPlaceOrder.setIsSpecAll(TeachMaterialPlaceOrder.IS_SPEC_ALL_NOT);
					teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.save(teachMaterialPlaceOrder);

					//存储预订单明细数据
					//记录订单需要的教材已经有的数量
					Map<Long, Integer> tmCountMap = new HashMap<Long, Integer>();
					//该订单所需教材库存是否满足
					boolean isStock = true;
					for(Object[] objs : beginScheduleList){
						String courseCode = null == objs[6] ? "" : objs[6].toString();
						PlaceOrderTeachMaterial placeOrderTeachMaterial = new PlaceOrderTeachMaterial();
						placeOrderTeachMaterial.setCount(Long.parseLong(String.valueOf(personNumStr)));
						placeOrderTeachMaterial.setCourseCode(courseCode);
						placeOrderTeachMaterial.setCreateTime(DateTools.getLongNowTime());
						placeOrderTeachMaterial.setCreator(loginUserName);
						placeOrderTeachMaterial.setOrderId(teachMaterialPlaceOrder.getId());
						placeOrderTeachMaterial.setOperator(loginUserName);
						placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
						placeOrderTeachMaterialDAO.save(placeOrderTeachMaterial);

						if(isStock) {
							//通过课程查询课程关联的教材
							List<TeachMaterial> teachMaterialList = new ArrayList<TeachMaterial>();
							List<TeachMaterial> teachMaterialList2 = findTeachMaterialByCourseCodeDAO.getTeachMaterialByCourseCode(courseCode);
							List<TeachMaterial> teachMaterialList3 = findTeachMaterialFromSetTMByCourseCodeDAO.getTeachMaterialFromSetTMByCourseCode(courseCode);
							if(null != teachMaterialList2 && 0 < teachMaterialList2.size()){
								teachMaterialList.addAll(teachMaterialList2);
							}
							if(null != teachMaterialList3 && 0 < teachMaterialList3.size()){
								teachMaterialList.addAll(teachMaterialList3);
							}

							if(null != teachMaterialList && 0 < teachMaterialList.size()){
								for(TeachMaterial teachMaterial : teachMaterialList){
									//检查所需教材库存是否满足
									TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
									//查询当前学期学生订单该渠道下的教材已经确认未发货的数量
									BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), localSemester.getId());
									//查询当前学期预订单该渠道下的教材已生成未发货的数量
									BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), localSemester.getId());
									int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
									int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
									int count = null == tmCountMap.get(teachMaterial.getId()) ? 0 : tmCountMap.get(teachMaterial.getId());
									if ((null == teachMaterialStock && 0 < count + personNumStr + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock()- confirmCount - confirmCount2 - count - personNumStr)) {
										isStock = false;
									}
									tmCountMap.put(teachMaterial.getId(), personNumStr + count);
								}
							}
						}
					}

					//如果库存不满足，就修改订单信息
					//2017-09-25  邸老师说不存在库存不足的订单了
//					if(!isStock){
//						teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_NOT);
//						teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
//					}

					//存储订单日志
					PlaceOrderLog PlaceOrderLog = new PlaceOrderLog();
					PlaceOrderLog.setOperateTime(DateTools.getLongNowTime());
					PlaceOrderLog.setOperator(loginUserName);
					PlaceOrderLog.setOrderId(teachMaterialPlaceOrder.getId());
					PlaceOrderLog.setState((PlaceOrderStatus.CREATED.getCode()));
					this.placeOrderLogDAO.save(PlaceOrderLog);
				}
			}
			return teachMaterialPlaceOrder;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	@Transactional
	public void updatePlaceOrder(Long placeOrderId,String orderMaterialId_count_str,String loginName) {
		if(null != orderMaterialId_count_str && !"".equals(orderMaterialId_count_str.trim())){
			boolean isAuto = true;
			TeachMaterialPlaceOrder teachMaterialPlaceOrder = this.teachMaterialPlaceOrderDAO.get(placeOrderId);
			if(null == teachMaterialPlaceOrder){
				throw new BusinessException("没有找到该订单信息");
			}
			if(StringUtils.isEmpty(teachMaterialPlaceOrder.getEnYear()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getEnQuarter()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getSpecCode()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getLevelCode())){
				isAuto = false;
			}
			//获取当前学期
			Semester semester = semesterDAO.queryLocalSemester();
			//查询该订单的学习中心的发行渠道
			IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(teachMaterialPlaceOrder.getSpotCode());
			//拆分明细ID数量字符串
			String[] oLevelArr = orderMaterialId_count_str.split("a");
			if(null != oLevelArr && oLevelArr.length > 0){
				//记录订单需要的教材已经有的数量
				Map<Long, Integer> tmCountMap = new HashMap<Long, Integer>();
				//该订单所需教材库存是否满足
				boolean isStock = true;
				for(String oLevelStr : oLevelArr){
					Long orderMaterialId = Long.parseLong(oLevelStr.split("_")[0]);
					int count = Integer.parseInt(oLevelStr.split("_")[1]);
					if(count < 0){
						count = 0;
					}
					
					PlaceOrderTeachMaterial placeOrderTeachMaterial = this.placeOrderTeachMaterialDAO.get(orderMaterialId);
					placeOrderTeachMaterial.setCount(Long.parseLong(String.valueOf(count)));
					placeOrderTeachMaterial.setOperator(loginName);
					placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
					this.placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);

					if(isStock) {
						List<TeachMaterial> teachMaterialList = new ArrayList<TeachMaterial>();
						//判断是否是手动添加的订单，是的话就直接查教材；不是的话就通过课程去找教材
						if(isAuto) {
							//通过课程查询课程关联的教材
							List<TeachMaterial> teachMaterialList2 = findTeachMaterialByCourseCodeDAO.getTeachMaterialByCourseCode(placeOrderTeachMaterial.getCourseCode());
							List<TeachMaterial> teachMaterialList3 = findTeachMaterialFromSetTMByCourseCodeDAO.getTeachMaterialFromSetTMByCourseCode(placeOrderTeachMaterial.getCourseCode());
							if (null != teachMaterialList2 && 0 < teachMaterialList2.size()) {
								teachMaterialList.addAll(teachMaterialList2);
							}
							if (null != teachMaterialList3 && 0 < teachMaterialList3.size()) {
								teachMaterialList.addAll(teachMaterialList3);
							}
						}else{
							TeachMaterial teachMaterial = findTeachMaterialByCourseCodeDAO.get(placeOrderTeachMaterial.getTeachMaterialId());
							if(null != teachMaterial){
								teachMaterialList.add(teachMaterial);
							}
						}

						if(null != teachMaterialList && 0 < teachMaterialList.size()){
							for(TeachMaterial teachMaterial : teachMaterialList){
								//检查所需教材库存是否满足
								TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
								//查询当前学期学生订单该渠道下的教材已经确认未发货的数量
								BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
								//查询当前学期预订单该渠道下的教材已生成未发货的数量
								BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
								int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
								int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
								int beforeCount = null == tmCountMap.get(teachMaterial.getId()) ? 0 : tmCountMap.get(teachMaterial.getId());
								if ((null == teachMaterialStock && 0 < count + beforeCount + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock()- confirmCount - confirmCount2 - count - beforeCount)) {
									isStock = false;
								}
								tmCountMap.put(teachMaterial.getId(), beforeCount + count);
							}
						}
					}
				}
				//2017-09-25  狄老师说不存在库存不足的订单了
				if(!isStock){
					teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
				}else{
					teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
				}
				this.teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
			}
		}
	}

	@Override
	@Transactional
	public void editSpotOrderForState(String orderCode, Long packageId,String status, Timestamp operateTime, String loginName) throws Exception {
		if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("没有传入订单号");
        }
        //查询订单信息
		TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.findPlaceOrderByCode(orderCode);
        if(null == teachMaterialPlaceOrder){
            throw new BusinessException("没有找到["+orderCode+"]该订单");
        }
        //修改状态
        teachMaterialPlaceOrder.setOrderStatus(status);
        if(null != packageId){
        	teachMaterialPlaceOrder.setPackageId(packageId);
        }
        teachMaterialPlaceOrder.setOperator(loginName);
        teachMaterialPlaceOrder.setOperateTime(null == operateTime ? DateTools.getLongNowTime() : operateTime);
        teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);

        //存储订单日志
		PlaceOrderLog PlaceOrderLog = new PlaceOrderLog();
		PlaceOrderLog.setOperateTime(new Date());
		PlaceOrderLog.setOperator(loginName);
		PlaceOrderLog.setOrderId(teachMaterialPlaceOrder.getId());
		PlaceOrderLog.setState(status);
		
		this.placeOrderLogDAO.save(PlaceOrderLog);
		
		
	}

	@Override
	@Transactional
	public PageInfo queryPlaceOrderPackageCount(PageInfo pageInfo,Map<String, String> map, Map<String, Boolean> sortMap) {
		pageInfo = findPlaceOrderPackageByWhereDAOImpl.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                PlaceOrderPackage placeOrderPackage = new PlaceOrderPackage();
                placeOrderPackage.setSendTime((Date) objs[9]);
                placeOrderPackage.setCreateTime((Date) objs[11]);
                placeOrderPackage.setOperateTime((Date) objs[13]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(placeOrderPackage, jsonConfig);
                jsonObject.put("sendTime", "null".equals(jsonObject.get("sendTime").toString()) ? "" : jsonObject.get("sendTime"));
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("spotName", objs[0]);
                jsonObject.put("id", objs[2]);
                jsonObject.put("semesterId", objs[3]);
                jsonObject.put("code", objs[4]);
                jsonObject.put("spotCode", objs[5]);
                jsonObject.put("sort", objs[6]);
                jsonObject.put("logisticCode", objs[7]);
                jsonObject.put("isSign", objs[8]);
                jsonObject.put("creator", objs[10]);
                jsonObject.put("operator", objs[12]);
                jsonObject.put("version", objs[14]);
                jsonObject.put("orderCount", objs[15]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
	}

	@Override
	@Transactional
	public PlaceOrderPackage addPlaceOrderPackage(String spotCode,String orderCodes, String logisticCodes, String loginName) throws Exception {
		
		if(StringUtils.isEmpty(spotCode)){
            throw new BusinessException("没有传入学习中心编号");
        }
        if(StringUtils.isEmpty(orderCodes)){
            throw new BusinessException("没有传入订单号");
        }

		Timestamp operateTime = DateTools.getLongNowTime();

        //得到当前学期
        Semester semester = semesterDAO.queryLocalSemester();

        //查询当天该学习中心最大的编号
        int number = 0;
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = (calendar.get(Calendar.MONTH)+1) < 10 ? "0"+(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)+"";
        String day = calendar.get(Calendar.DATE) < 10 ? "0"+calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE)+"";
        PlaceOrderPackage maxCodePlaceOrderPackage = findPlaceOrderPackageForMaxCodeDAO.findPlaceOrderPackageForMaxCode(year+month+day+spotCode+"%");
        if(null != maxCodePlaceOrderPackage){
            String maxCode = maxCodePlaceOrderPackage.getCode();
            number = Integer.parseInt(maxCode.substring(maxCode.length()-4, maxCode.length()));
        }
        //生成打包编号
        String code = OrderCodeTools.createPlaceOrderPacageCode(spotCode, number+1);

        //查询该中心，已打包的最大顺序号
        int sort = 0;
        PlaceOrderPackage maxSortPlaceOrderPackage = findPlaceOrderPackageForMaxSortNotSendDAO.findPlaceOrderPackageForMaxSortNotSend(spotCode, semester.getId());
        if(null != maxSortPlaceOrderPackage){
            sort = maxSortPlaceOrderPackage.getSort();
        }
		//如果录入了物流单号，就走发货流程
		if(!StringUtils.isEmpty(logisticCodes)){
			sort += logisticCodes.split(",").length;
		}

        PlaceOrderPackage placeOrderPackage = new PlaceOrderPackage();
        placeOrderPackage.setSemesterId(semester.getId());
        placeOrderPackage.setCode(code);
        placeOrderPackage.setSpotCode(spotCode);
        placeOrderPackage.setIsSign(StudentBookOrderPackage.IS_SIGN_NOT);
		placeOrderPackage.setSort(sort);
        placeOrderPackage.setCreator(loginName);
        placeOrderPackage.setOperator(loginName);
        this.placeOrderPackageDAO.save(placeOrderPackage);

        //添加预订单与大包关联
        String[] orderCodeArray = orderCodes.split(",");
        for(String orderCode : orderCodeArray){
        	TeachMaterialPlaceOrder teachMaterialPlaceOrder = findPlaceOrderByCodeDAO.findPlaceOrderByCode(orderCode);
            if(null == teachMaterialPlaceOrder){
                throw new BusinessException("订单号："+orderCode+", 没有找到");
            }
            if(null != teachMaterialPlaceOrder.getPackageId() && 0 < teachMaterialPlaceOrder.getPackageId()){
                throw new BusinessException("编号："+teachMaterialPlaceOrder.getSpotCode()+", 订单号："+orderCode+", 已经打包");
            }
            if(!PlaceOrderStatus.DISPATCH.getCode().equals(teachMaterialPlaceOrder.getOrderStatus())){
                throw new BusinessException("编号："+teachMaterialPlaceOrder.getSpotCode()+", 订单号："+orderCode+", 状态不是分拣中");
            }
            //修改订单状态为已打包，记录状态变更日志
            editSpotOrderForState(orderCode, placeOrderPackage.getId(), PlaceOrderStatus.PACKGED.getCode(), operateTime, loginName);
        }

		//如果录入了物流单号，就走发货流程
		if(!StringUtils.isEmpty(logisticCodes)){
			Long[] ids = {placeOrderPackage.getId()};
			String[] logisticCodeArray = {logisticCodes};
			this.sendPlaceOrderPackage(ids, logisticCodeArray, loginName);
		}
       return placeOrderPackage;
	}

	/**
	 * 刷新预订单，如果库存满足了，修改预订单库存状态
	 * @param id
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	public void refreshPlaceOrder(long id, String loginName) throws Exception {
		//库存是否够
		boolean isStock = true;
		//是否是自动添加订单
		boolean isAuto = true;
		//查询订单信息
		TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.get(id);
		if(null == teachMaterialPlaceOrder){
			throw new BusinessException("没有找到预订单信息");
		}
		if(StringUtils.isEmpty(teachMaterialPlaceOrder.getEnYear()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getEnQuarter()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getSpecCode()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getLevelCode())){
			isAuto = false;
		}
		//查询订单明细
		Map<String, Object> map = null;
		List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
		if(isAuto){
			map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(teachMaterialPlaceOrder.getOrderCode(), 0);
		}else{
			map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(teachMaterialPlaceOrder.getOrderCode());
		}

		if(null != map){
			placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
		}

		if(null == placeOrderDetailShowList || 1 > placeOrderDetailShowList.size()){
			throw new BusinessException("没有找到该预订单的明细");
		}
		//获取当前学期
		Semester semester = semesterDAO.queryLocalSemester();
		//查询预订单的发行渠道
		IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(teachMaterialPlaceOrder.getSpotCode());
		//记录订单需要的教材已经有的数量
		Map<Long, Integer> tmCountMap = new HashMap<Long, Integer>();
		//遍历明细，判断库存是否够
		for(PlaceOrderDetailShow placeOrderDetailShow : placeOrderDetailShowList){
			List<TeachMaterial> teachMaterialList = new ArrayList<TeachMaterial>();
			//判断是否是手动添加的订单，是的话就直接查教材；不是的话就通过课程去找教材
			if(isAuto) {
				//通过课程查询课程关联的教材
				List<TeachMaterial> teachMaterialList2 = findTeachMaterialByCourseCodeDAO.getTeachMaterialByCourseCode(placeOrderDetailShow.getCourseCode());
				List<TeachMaterial> teachMaterialList3 = findTeachMaterialFromSetTMByCourseCodeDAO.getTeachMaterialFromSetTMByCourseCode(placeOrderDetailShow.getCourseCode());
				if (null != teachMaterialList2 && 0 < teachMaterialList2.size()) {
					teachMaterialList.addAll(teachMaterialList2);
				}
				if (null != teachMaterialList3 && 0 < teachMaterialList3.size()) {
					teachMaterialList.addAll(teachMaterialList3);
				}
			}else{
				TeachMaterial teachMaterial = findTeachMaterialByCourseCodeDAO.get(placeOrderDetailShow.getMaterialId());
				if(null != teachMaterial){
					teachMaterialList.add(teachMaterial);
				}
			}

			if(null != teachMaterialList && 0 < teachMaterialList.size()){
				for(TeachMaterial teachMaterial : teachMaterialList){
					if(null == tmCountMap.get(teachMaterial.getId())){
						tmCountMap.put(teachMaterial.getId(), placeOrderDetailShow.getCount());
					}else{
						tmCountMap.put(teachMaterial.getId(), tmCountMap.get(teachMaterial.getId()) + placeOrderDetailShow.getCount());
					}
					//检查所需教材库存是否满足
					TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
					//查询当前学期学生订单该渠道下的教材已经确认未发货的数量
					BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
					//查询当前学期预订单该渠道下的教材已生成未发货的数量
					BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
					int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
					int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
					int beforeCount = null == tmCountMap.get(teachMaterial.getId()) ? 0 : tmCountMap.get(teachMaterial.getId());
					if ((null == teachMaterialStock && 0 < beforeCount + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock()- confirmCount - confirmCount2 - beforeCount)) {
						isStock = false;
						break;
					}
				}
			}
		}

		//如果库存不足，不修改，否则修改
		if(isStock){
			teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
			teachMaterialPlaceOrder.setOperator(loginName);
			//修改数据
			teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
		}
	}

	/**
	 * 刷新当前学期所有库存不足的预订单
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void refreshPlaceOrderAll(String loginName) throws Exception {
		Semester semester = findNowSemesterDAO.getNowSemester();
		Map<String, String> params = new HashMap<String, String>();
		params.put("semesterId", semester.getId()+"");
		params.put("isStock", TeachMaterialPlaceOrder.ISSTOCK_NOT+"");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(1);
		pageInfo.setCountOfCurrentPage(999999);
		Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
		sortMap.put("t.order_code", true);
		pageInfo = findPlaceOrderPageByWhereService.findPage(pageInfo, params, sortMap);
		if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
			JSONArray jsonArray = (JSONArray)pageInfo.getPageResults();
			for(int i=0; i<jsonArray.size(); i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				this.refreshPlaceOrder(Long.parseLong(jsonObject.get("id").toString()), loginName);
			}
		}
	}

	/**
	 * 拆分预订单，把库存不足的教材筛选出来重新生成一个订单
	 * @param id
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void splitPlaceOrder(long id, String loginName, boolean flag) throws Exception {
		try {
			boolean isNotStockAll = true;
			//是否是自动添加订单
			boolean isAuto = true;
			List<PlaceOrderDetailShow> stockOrderTMList = new ArrayList<PlaceOrderDetailShow>();
			List<PlaceOrderDetailShow> notStockOrderTMList = new ArrayList<PlaceOrderDetailShow>();
			//查询当前学期
			Semester semester = semesterDAO.queryLocalSemester();
			//查询订单信息
			TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.get(id);
			if (null == teachMaterialPlaceOrder) {
				throw new BusinessException("没有找到预订单信息");
			}
			if (StringUtils.isEmpty(teachMaterialPlaceOrder.getEnYear()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getEnQuarter()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getSpecCode()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getLevelCode())) {
				isAuto = false;
			}
			//查询订单明细
			Map<String, Object> map = null;
			List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
			if (isAuto) {
				map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(teachMaterialPlaceOrder.getOrderCode(), 0);
			} else {
				map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(teachMaterialPlaceOrder.getOrderCode());
			}
			if(null != map){
				placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
			}

			if (null == placeOrderDetailShowList || 1 > placeOrderDetailShowList.size()) {
				throw new BusinessException("没有找到该预订单的明细");
			}

			//查询预订单的发行渠道
			IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(teachMaterialPlaceOrder.getSpotCode());
			//记录订单需要的教材已经有的数量
			Map<Long, Integer> tmCountMap = new HashMap<Long, Integer>();

			//遍历明细，判断库存是否够
			for (PlaceOrderDetailShow placeOrderDetailShow : placeOrderDetailShowList) {
				List<TeachMaterial> teachMaterialList = new ArrayList<TeachMaterial>();
				//判断是否是手动添加的订单，是的话就直接查教材；不是的话就通过课程去找教材
				if (isAuto) {
					//通过课程查询课程关联的教材
					List<TeachMaterial> teachMaterialList2 = findTeachMaterialByCourseCodeDAO.getTeachMaterialByCourseCode(placeOrderDetailShow.getCourseCode());
					List<TeachMaterial> teachMaterialList3 = findTeachMaterialFromSetTMByCourseCodeDAO.getTeachMaterialFromSetTMByCourseCode(placeOrderDetailShow.getCourseCode());
					if (null != teachMaterialList2 && 0 < teachMaterialList2.size()) {
						teachMaterialList.addAll(teachMaterialList2);
					}
					if (null != teachMaterialList3 && 0 < teachMaterialList3.size()) {
						teachMaterialList.addAll(teachMaterialList3);
					}
					boolean courseIsStock = true;
					if (null != teachMaterialList && 0 < teachMaterialList.size()) {
						for (TeachMaterial teachMaterial : teachMaterialList) {
							if(null == tmCountMap.get(teachMaterial.getId())){
								tmCountMap.put(teachMaterial.getId(), placeOrderDetailShow.getCount());
							}else{
								tmCountMap.put(teachMaterial.getId(), tmCountMap.get(teachMaterial.getId()) + placeOrderDetailShow.getCount());
							}
							//检查所需教材库存是否满足
							TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
							//查询当前学期学生订单该渠道下的教材已经确认未发货的数量
							BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
							//查询当前学期预订单该渠道下的教材已生成未发货的数量
							BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
							int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
							int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
							int beforeCount = null == tmCountMap.get(teachMaterial.getId()) ? 0 : tmCountMap.get(teachMaterial.getId());
							if ((null == teachMaterialStock && 0 < beforeCount + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock() - confirmCount - confirmCount2 - beforeCount)) {
								courseIsStock = false;
								break;
							} else {
								isNotStockAll = false;
								break;
							}
						}
					}
					if (courseIsStock) {
						stockOrderTMList.add(placeOrderDetailShow);
					} else {
						notStockOrderTMList.add(placeOrderDetailShow);
					}
				} else {
					TeachMaterial teachMaterial = findTeachMaterialByCourseCodeDAO.get(placeOrderDetailShow.getMaterialId());
					if (null != teachMaterial) {
						if(null == tmCountMap.get(teachMaterial.getId())){
							tmCountMap.put(teachMaterial.getId(), placeOrderDetailShow.getCount());
						}else{
							tmCountMap.put(teachMaterial.getId(), tmCountMap.get(teachMaterial.getId()) + placeOrderDetailShow.getCount());
						}
						//检查所需教材库存是否满足
						TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
						//查询当前学期学生订单该渠道下的教材已经确认未发货的数量
						BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
						//查询当前学期预订单该渠道下的教材已生成未发货的数量
						BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(teachMaterial.getId(), issueRange.getIssueChannelId(), semester.getId());
						int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
						int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
						int beforeCount = null == tmCountMap.get(teachMaterial.getId()) ? 0 : tmCountMap.get(teachMaterial.getId());
						if ((null == teachMaterialStock && 0 < beforeCount + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > teachMaterialStock.getStock() - confirmCount - confirmCount2 - beforeCount)) {
							notStockOrderTMList.add(placeOrderDetailShow);
						} else {
							stockOrderTMList.add(placeOrderDetailShow);
							isNotStockAll = false;
						}
					}
				}
			}

			//如果所有的教材都没有库存，就不用拆分了; 单个拆分的时候，需要提示，批量拆分的时候就不需要提示这个
			if (isNotStockAll && flag) {
				throw new BusinessException("预订单下的教材都没有库存，不需要拆分");
			}

			//重新建一个新的订单，来关联库存不够教材。全部都不满足的话就不管这个订单了。
			if (null != notStockOrderTMList && 0 < notStockOrderTMList.size() && !isNotStockAll) {
				//创建订单号
				String maxOrderCode = placeOrderDAO.queryMaxOrderNumber(teachMaterialPlaceOrder.getSpotCode(), semester.getId());
				if (null == maxOrderCode) {
					maxOrderCode = "0";
				} else {
					maxOrderCode = maxOrderCode.substring(maxOrderCode.length() - 6, maxOrderCode.length());
				}
				String orderCode = OrderCodeTools.createSpotOrderCode(semester.getYear(), semester.getQuarter(), teachMaterialPlaceOrder.getSpotCode(), Integer.parseInt(maxOrderCode) + 1);
				//存储预订单数据
				TeachMaterialPlaceOrder newTeachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
				newTeachMaterialPlaceOrder.setCreateTime(DateTools.getLongNowTime());
				newTeachMaterialPlaceOrder.setCreator(loginName);
				newTeachMaterialPlaceOrder.setOperator(loginName);
				newTeachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
				newTeachMaterialPlaceOrder.setEnQuarter(teachMaterialPlaceOrder.getEnQuarter());
				newTeachMaterialPlaceOrder.setEnYear(teachMaterialPlaceOrder.getEnYear());
				newTeachMaterialPlaceOrder.setLevelCode(teachMaterialPlaceOrder.getLevelCode());
				newTeachMaterialPlaceOrder.setOrderCode(orderCode);
				newTeachMaterialPlaceOrder.setOrderStatus(PlaceOrderStatus.CREATED.getCode());
				newTeachMaterialPlaceOrder.setSemesterId(semester.getId());
				newTeachMaterialPlaceOrder.setSpecCode(teachMaterialPlaceOrder.getSpecCode());
				newTeachMaterialPlaceOrder.setSpotCode(teachMaterialPlaceOrder.getSpotCode());
				newTeachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_NOT);
				newTeachMaterialPlaceOrder.setAddress(teachMaterialPlaceOrder.getAddress());
				newTeachMaterialPlaceOrder.setAdminName(teachMaterialPlaceOrder.getAdminName());
				newTeachMaterialPlaceOrder.setPhone(teachMaterialPlaceOrder.getPhone());
				newTeachMaterialPlaceOrder.setTel(teachMaterialPlaceOrder.getTel());
				newTeachMaterialPlaceOrder.setPostalCode(teachMaterialPlaceOrder.getPostalCode());
				newTeachMaterialPlaceOrder.setIsSpecAll(TeachMaterialPlaceOrder.IS_SPEC_ALL_NOT);
				teachMaterialPlaceOrderDAO.save(newTeachMaterialPlaceOrder);

				//把库存不足的订单明细关联过来
				for (PlaceOrderDetailShow placeOrderDetailShow : notStockOrderTMList) {
					long placeOrderTMId = placeOrderDetailShow.getPlaceOrderDetailId();
					//查询预订单明细
					PlaceOrderTeachMaterial placeOrderTeachMaterial = placeOrderTeachMaterialDAO.get(placeOrderTMId);
					if (null != placeOrderTeachMaterial) {
						placeOrderTeachMaterial.setOrderId(newTeachMaterialPlaceOrder.getId());
						placeOrderTeachMaterial.setOperator(loginName);
						placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
						placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);
					}
				}

				//记录新订单的操作日志
				PlaceOrderLog PlaceOrderLog = new PlaceOrderLog();
				PlaceOrderLog.setOperateTime(DateTools.getLongNowTime());
				PlaceOrderLog.setOperator(loginName);
				PlaceOrderLog.setOrderId(newTeachMaterialPlaceOrder.getId());
				PlaceOrderLog.setState((PlaceOrderStatus.CREATED.getCode()));
				placeOrderLogDAO.save(PlaceOrderLog);
			}

			//要有库存足的教材，才修改原有订单，如果所有教材都不足，原有订单不修改
			if(!isNotStockAll) {
				//把原有订单修改为库存足够
				teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
				teachMaterialPlaceOrder.setOperator(loginName);
				teachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
				//修改数据
				teachMaterialPlaceOrderDAO.update(teachMaterialPlaceOrder);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 拆分当前学期所有库存不足的预订单
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void splitPlaceOrderAll(String spotCode, String loginName) throws Exception {
		Semester semester = findNowSemesterDAO.getNowSemester();
		Map<String, String> params = new HashMap<String, String>();
		params.put("semesterId", semester.getId()+"");
		params.put("spotCode", spotCode);
		params.put("isStock", TeachMaterialPlaceOrder.ISSTOCK_NOT+"");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(1);
		pageInfo.setCountOfCurrentPage(999999);
		Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
		sortMap.put("t.order_code", true);
		pageInfo = findPlaceOrderPageByWhereService.findPage(pageInfo, params, sortMap);
		if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
			JSONArray jsonArray = (JSONArray)pageInfo.getPageResults();
			for(int i=0; i<jsonArray.size(); i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				this.splitPlaceOrder(Long.parseLong(jsonObject.get("id").toString()), loginName, false);
			}
		}
	}

	@Override
	@Transactional
	public void sendPlaceOrderPackage(Long[] ids, String[] logisticCodes, String loginName) throws Exception {
		if(null == ids){
			throw new BusinessException("没有传入教材包id");
		}
		if(null == logisticCodes){
			throw new BusinessException("没有传入物流单号");
		}
		if(ids.length != logisticCodes.length){
			throw new BusinessException("传入教材包对应物流单号不一致");
		}
		//修改教材包
		Timestamp operateTime = DateTools.getLongNowTime();
		for(int i=0; i<ids.length; i++){
			long id = ids[i];
			String logisticCode = logisticCodes[i];
			//查询教材包
			PlaceOrderPackage placeOrderPackage = placeOrderPackageDAO.get(id);
			//修改教材包订单
			if(null != placeOrderPackage){
				placeOrderPackage.setLogisticCode(logisticCode);
				placeOrderPackage.setSendTime(DateTools.getLongNowTime());
				placeOrderPackage.setOperator(loginName);
				placeOrderPackage.setOperateTime(DateTools.getLongNowTime());
				placeOrderPackageDAO.update(placeOrderPackage);
			}
			//获取该教材包下的订单
			List<TeachMaterialPlaceOrder> teachMaterialPlaceOrderList = findPlaceOrderByPackageIdDAO.findPlaceOrderByPackageId(id);
			if(null != teachMaterialPlaceOrderList){
				//获取当前学期
				Semester semester = findNowSemesterDAO.getNowSemester();
				//修改订单状态，记录订单日志
				for(TeachMaterialPlaceOrder teachMaterialPlaceOrder : teachMaterialPlaceOrderList){
					editSpotOrderForState(teachMaterialPlaceOrder.getOrderCode(), null, TeachMaterialPlaceOrder.STATE_SEND+"", operateTime, loginName);
					boolean isAuto = true;
					if (StringUtils.isEmpty(teachMaterialPlaceOrder.getEnYear()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getEnQuarter()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getSpecCode()) && StringUtils.isEmpty(teachMaterialPlaceOrder.getLevelCode())) {
						isAuto = false;
					}
					//查询订单下的明细
					Map<String, Object> map = null;
					List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
					if(isAuto){
						map = findPlaceOrderListByOrderCodeService.getPlaceOrderListByOrderCode(teachMaterialPlaceOrder.getOrderCode(), 1);
					}else{
						map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(teachMaterialPlaceOrder.getOrderCode(), 1);
					}
					if(null != map){
						placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
					}

					if(null != placeOrderDetailShowList && 0 < placeOrderDetailShowList.size()) {
						String beforeCourseCode = "";
						String beforeCreator = "";
						Date beforeCreateTime = null;
						for(PlaceOrderDetailShow placeOrderDetailShow : placeOrderDetailShowList) {
							PlaceOrderTeachMaterial placeOrderTeachMaterial = null;
							//如果是自动生成的预订单，需要把发出去的教材信息记录到预订单明细里面去
							if(isAuto){
								//这里判断如果一门课程关联了多个教材，那么后面的教材需要新增进来；这里做了课程编号排序，所以可以利用下一个课程编号是否与上一个一样
								if(!beforeCourseCode.equals(placeOrderDetailShow.getCourseCode())){
									//查询明细
									placeOrderTeachMaterial = placeOrderTeachMaterialDAO.get(placeOrderDetailShow.getPlaceOrderDetailId());
									if(null != placeOrderTeachMaterial){
										placeOrderTeachMaterial.setTeachMaterialId(placeOrderDetailShow.getMaterialId());
										placeOrderTeachMaterial.setTmPrice(placeOrderDetailShow.getMaterialPrice());
										placeOrderTeachMaterial.setOperator(loginName);
										placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
										placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);
									}
								}else {
									placeOrderTeachMaterial = new PlaceOrderTeachMaterial();
									placeOrderTeachMaterial.setOrderId(placeOrderDetailShow.getOrderId());
									placeOrderTeachMaterial.setCourseCode(placeOrderDetailShow.getCourseCode());
									placeOrderTeachMaterial.setTeachMaterialId(placeOrderDetailShow.getMaterialId());
									placeOrderTeachMaterial.setTmPrice(placeOrderDetailShow.getMaterialPrice());
									placeOrderTeachMaterial.setCount(Long.parseLong(String.valueOf(placeOrderDetailShow.getCount())));
									placeOrderTeachMaterial.setIsSend(PlaceOrderTeachMaterial.IS_SEND_NOT);
									placeOrderTeachMaterial.setCreator(beforeCreator);
									placeOrderTeachMaterial.setCreateTime(beforeCreateTime);
									placeOrderTeachMaterial.setOperator(loginName);
									placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
									placeOrderTeachMaterialDAO.save(placeOrderTeachMaterial);

								}
								beforeCourseCode = placeOrderDetailShow.getCourseCode();
								beforeCreator = placeOrderDetailShow.getCreator();
								beforeCreateTime = placeOrderDetailShow.getCreateTime();
							}else{
								//查询明细
								placeOrderTeachMaterial = placeOrderTeachMaterialDAO.get(placeOrderDetailShow.getPlaceOrderDetailId());
							}

							//查询教材信息
							TeachMaterial teachMaterial = findTeachMaterialService.get(placeOrderDetailShow.getMaterialId());
							if(null != teachMaterial && teachMaterial.getState() == TeachMaterial.STATE_ENABLE && 0 < placeOrderDetailShow.getCount()) {
								//记录中心消费信息
								SpotExpenseBuy spotExpenseBuy = new SpotExpenseBuy();
								spotExpenseBuy.setSpotCode(teachMaterialPlaceOrder.getSpotCode());
								spotExpenseBuy.setSemesterId(semester.getId());
								spotExpenseBuy.setType(SpotExpenseBuy.TYPE_BUY_TM);
								spotExpenseBuy.setDetail("购买了" + placeOrderDetailShow.getCount() + "本，[" + teachMaterial.getName() + "] 教材");
								spotExpenseBuy.setMoney(new BigDecimal(placeOrderDetailShow.getCount()).multiply(new BigDecimal(placeOrderDetailShow.getMaterialPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
								spotExpenseBuy.setTeachMaterialId(teachMaterial.getId());
								addSpotExpenseBuyService.addSpotExpenseBuy(spotExpenseBuy, loginName);
								//减去教材库存
								IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(teachMaterialPlaceOrder.getSpotCode());
								if (null != issueRange) {
									TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), issueRange.getIssueChannelId());
									if(null != teachMaterialStock) {
										teachMaterialStock.setStock(teachMaterialStock.getStock() - placeOrderDetailShow.getCount());
										findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
									}
								}

								//记录该订单明细为已发出
								placeOrderTeachMaterial.setIsSend(PlaceOrderTeachMaterial.IS_SEND_YES);
								placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);
							}
						}
					}
				}
			}
			if(!StringUtils.isEmpty(logisticCode)){
				//发起快递100的请求
				String[] nus = logisticCode.split(",");
				if(null != nus) {
					for(String nu : nus) {
						HttpRequestTools.reqKuaidi(nu);
					}
				}
			}
		}
	}

	/**
	 * 修改已发出的预订单中的教材价格
	 * @param paramsMap
	 * @param sortMap
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void editSendStudentBookOrderTMPrice(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName) throws Exception {
		JSONArray jsonArray = countSendPlaceOrderByTMIdService.countSendPlaceOrderByTMId(paramsMap, sortMap);
		if(null != jsonArray && 0 < jsonArray.size()){
			//获取教材信息
			TeachMaterial teachMaterial = teachMaterialDAO.get(Long.parseLong(paramsMap.get("tmId")));
			if(null == teachMaterial){
				throw new BusinessException("没有找到教材信息");
			}
			//获取当前学期
			Semester semester = findNowSemesterDAO.getNowSemester();
			float newPrice = Float.valueOf(paramsMap.get("newPrice"));
			Timestamp operateTime = DateTools.getLongNowTime();
			for (int i = 0; i < jsonArray.size(); i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				long potmId = Long.parseLong(jsonObject.get("potmId").toString());
				PlaceOrderTeachMaterial placeOrderTeachMaterial = placeOrderTeachMaterialDAO.get(potmId);
				if(null != placeOrderTeachMaterial){
					//修改教材明细里面的价格
					placeOrderTeachMaterial.setTmPrice(newPrice);
					placeOrderTeachMaterial.setOperator(loginName);
					placeOrderTeachMaterial.setOperateTime(operateTime);
					placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);

					//追加学习中心的消费记录
					String spotCode = jsonObject.get("spotCode").toString();
					String tmName = teachMaterial.getName();
					float oldPrice = Float.parseFloat(jsonObject.get("price").toString());
					float money = new BigDecimal(newPrice).subtract(new BigDecimal(oldPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					if(0 != money) {
						SpotExpenseBuy spotExpenseBuy = new SpotExpenseBuy();
						spotExpenseBuy.setSpotCode(spotCode);
						spotExpenseBuy.setSemesterId(semester.getId());
						spotExpenseBuy.setType(SpotExpenseBuy.TYPE_TM_UPDATE_PRICE);
						spotExpenseBuy.setMoney(new BigDecimal(money).multiply(new BigDecimal(placeOrderTeachMaterial.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
						spotExpenseBuy.setDetail("教材 [" + tmName + "] 价格由" + oldPrice + ", 变为" + newPrice + "，产生的差额");
						spotExpenseBuy.setCreator(loginName);
						spotExpenseBuy.setCreateTime(operateTime);
						addSpotExpenseBuyService.addSpotExpenseBuy(spotExpenseBuy, loginName);
					}
				}
			}
		}
	}

	/**
	 * 修改已发出的预订单中的教材数量
	 * @param paramsMap
	 * @param sortMap
	 * @param loginName
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void editSendStudentBookOrderTMCount(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName) throws Exception {
		JSONArray jsonArray = countSendPlaceOrderByTMIdService.countSendPlaceOrderByTMId(paramsMap, sortMap);
		if(null != jsonArray && 0 < jsonArray.size()){
			//获取教材信息
			TeachMaterial teachMaterial = teachMaterialDAO.get(Long.parseLong(paramsMap.get("tmId")));
			if(null == teachMaterial){
				throw new BusinessException("没有找到教材信息");
			}
			//获取当前学期
			Semester semester = findNowSemesterDAO.getNowSemester();
			int newCount = Integer.parseInt(paramsMap.get("newCount"));
			Timestamp operateTime = DateTools.getLongNowTime();
			for (int i = 0; i < jsonArray.size(); i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				long potmId = Long.parseLong(jsonObject.get("potmId").toString());
				PlaceOrderTeachMaterial placeOrderTeachMaterial = placeOrderTeachMaterialDAO.get(potmId);
				if(null != placeOrderTeachMaterial){
					//修改教材明细里面的价格
					placeOrderTeachMaterial.setCount(Long.parseLong(newCount+""));
					placeOrderTeachMaterial.setOperator(loginName);
					placeOrderTeachMaterial.setOperateTime(operateTime);
					placeOrderTeachMaterialDAO.update(placeOrderTeachMaterial);

					//追加学习中心的消费记录
					String spotCode = jsonObject.get("spotCode").toString();
					String tmName = teachMaterial.getName();
					float price = Float.parseFloat(jsonObject.get("price").toString());
					int oldCount = Integer.parseInt(jsonObject.get("count").toString());
					float money = new BigDecimal(newCount).subtract(new BigDecimal(oldCount)).multiply(new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					if(0 != money) {
						SpotExpenseBuy spotExpenseBuy = new SpotExpenseBuy();
						spotExpenseBuy.setSpotCode(spotCode);
						spotExpenseBuy.setSemesterId(semester.getId());
						spotExpenseBuy.setType(SpotExpenseBuy.TYPE_TM_UPDATE_PRICE);
						spotExpenseBuy.setMoney(money);
						spotExpenseBuy.setDetail("教材 [" + tmName + "] 数量由" + oldCount + ", 变为" + newCount + "，产生的差额");
						spotExpenseBuy.setCreator(loginName);
						spotExpenseBuy.setCreateTime(operateTime);
						addSpotExpenseBuyService.addSpotExpenseBuy(spotExpenseBuy, loginName);
					}
				}
			}
		}
	}
}
