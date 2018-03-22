package com.zs.service.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.PlaceOrderDAO;
import com.zs.dao.placeorder.PlaceOrderLogDAO;
import com.zs.dao.placeorder.PlaceOrderTeachMaterialDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.basic.Semester;
import com.zs.domain.placeorder.PlaceOrderLog;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.placeorder.AddPlaceOrderForSpecAllService;
import com.zs.service.placeorder.bean.PlaceOrderStatus;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/11/9.
 */
@Service("addPlaceOrderForSpecAllService")
public class AddPlaceOrderForSpecAllServiceImpl extends EntityServiceImpl<TeachMaterialPlaceOrder, TeachMaterialPlaceOrderDAO>
                    implements AddPlaceOrderForSpecAllService{

    @Resource
    private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private PlaceOrderDAO placeOrderDAO;
    @Resource
    private PlaceOrderTeachMaterialDAO placeOrderTeachMaterialDAO;
    @Resource
    private PlaceOrderLogDAO placeOrderLogDAO;

    @Override
    @Transactional
    public void add(String spotCode, String address, String adminName, String phone, String tel, String postalCode, String enYear, String enQuarter, String specCode, String levelCode, int personNum, String courseCodes, String loginName) throws Exception {
        try {
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
            //查询学习中心对应的渠道
            IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(spotCode);
            if(null == issueRange){
                throw new BusinessException("该学习中心没有对应发行渠道，不能生成预订单");
            }
            if(StringUtils.isEmpty(courseCodes)){
                throw new BusinessException("请输入订单明细课程");
            }
            if(StringUtils.isEmpty(address)){
                throw new BusinessException("请输入邮寄地址");
            }
            if(StringUtils.isEmpty(adminName)){
                throw new BusinessException("请输入收件人");
            }
            if(StringUtils.isEmpty(phone) || phone.length() < 11){
                throw new BusinessException("请输入正确的手机号码");
            }
            if(personNum < 1){
                throw new BusinessException("请输入人数");
            }
            //查询出当前学期
            Semester localSemester = findNowSemesterService.getNowSemester();

            synchronized (new Object()) {
                //创建订单号
                String maxOrderCode = placeOrderDAO.queryMaxOrderNumber(spotCode, localSemester.getId());
                if(null == maxOrderCode){
                    maxOrderCode = "0";
                }else{
                    maxOrderCode = maxOrderCode.substring(maxOrderCode.length() - 6,maxOrderCode.length());
                }
                String orderCode = OrderCodeTools.createSpotOrderCode(localSemester.getYear(), localSemester.getQuarter(), spotCode, Integer.parseInt(maxOrderCode) + 1);
                //存储预订单数据
                teachMaterialPlaceOrder.setCreateTime(DateTools.getLongNowTime());
                teachMaterialPlaceOrder.setCreator(loginName);
                teachMaterialPlaceOrder.setOperator(loginName);
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
                teachMaterialPlaceOrder.setIsSpecAll(TeachMaterialPlaceOrder.IS_SPEC_ALL_YES);
                super.save(teachMaterialPlaceOrder);

                //存储预订单明细数据
                for(String courseCode : courseCodes.split(",")){
                    PlaceOrderTeachMaterial placeOrderTeachMaterial = new PlaceOrderTeachMaterial();
                    placeOrderTeachMaterial.setCount(Long.parseLong(String.valueOf(personNum)));
                    placeOrderTeachMaterial.setCourseCode(courseCode);
                    placeOrderTeachMaterial.setCreateTime(DateTools.getLongNowTime());
                    placeOrderTeachMaterial.setCreator(loginName);
                    placeOrderTeachMaterial.setOrderId(teachMaterialPlaceOrder.getId());
                    placeOrderTeachMaterial.setOperator(loginName);
                    placeOrderTeachMaterial.setOperateTime(DateTools.getLongNowTime());
                    placeOrderTeachMaterialDAO.save(placeOrderTeachMaterial);

                }

                //存储订单日志
                PlaceOrderLog PlaceOrderLog = new PlaceOrderLog();
                PlaceOrderLog.setOperateTime(DateTools.getLongNowTime());
                PlaceOrderLog.setOperator(loginName);
                PlaceOrderLog.setOrderId(teachMaterialPlaceOrder.getId());
                PlaceOrderLog.setState((PlaceOrderStatus.CREATED.getCode()));
                placeOrderLogDAO.save(PlaceOrderLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
