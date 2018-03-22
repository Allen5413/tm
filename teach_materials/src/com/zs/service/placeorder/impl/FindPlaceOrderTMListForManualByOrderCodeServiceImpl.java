package com.zs.service.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.FindPlaceOrderTMListForManualByOrderCodeService;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Allen on 2015/8/5.
 */
@Service("findPlaceOrderTMListForManualByOrderCodeService")
public class FindPlaceOrderTMListForManualByOrderCodeServiceImpl extends EntityServiceImpl implements FindPlaceOrderTMListForManualByOrderCodeService {

    @Resource
    private FindListByWhereDAO findPlaceOrderTMListForManualByOrderIdDAO;
    @Resource
    private TeachMaterialPlaceOrderDAO teachMaterialPlaceOrderDAO;

    @Override
    @Transactional
    public Map<String, Object> findPlaceOrderTMListForManualByOrderCode(String orderCode) {
        return findPlaceOrderTMListForManualByOrderCode(orderCode, 0);
    }


    /**
     *
     * @param orderCode
     * @param isSetState  是否让状态来影响查询， 0：是，1：否
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> findPlaceOrderTMListForManualByOrderCode(String orderCode, int isSetState) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, Object> map = new HashMap<String, Object>();
        double sumTotalPrice = 0;
        List<PlaceOrderDetailShow> placeOrderDetailShowList = new ArrayList<PlaceOrderDetailShow>();
        TeachMaterialPlaceOrder teachMaterialPlaceOrder = teachMaterialPlaceOrderDAO.findPlaceOrderByCode(orderCode);
        if(null == teachMaterialPlaceOrder){
            throw new BusinessException("没有找到订单信息");
        }
        params.put("orderId", String.valueOf(teachMaterialPlaceOrder.getId()));
        if(0 == isSetState) {
            params.put("state", teachMaterialPlaceOrder.getOrderStatus());
        }
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("potm.create_time", true);
        List<Object[]> resultList = findPlaceOrderTMListForManualByOrderIdDAO.findListByWhere(params, sortMap);
        if(null != resultList && 0 < resultList.size()){

            for(Object[] objs : resultList){
                float price = null == objs[4] ? 0 : Float.parseFloat(objs[4].toString());
                int count = null == objs[5] ? 0 : Integer.parseInt(objs[5].toString());
                String courseCode = null == objs[8] ? "" : objs[8].toString();
                PlaceOrderDetailShow placeOrderDetailShow = new PlaceOrderDetailShow();
                placeOrderDetailShow.setPlaceOrderDetailId(Long.parseLong(objs[0].toString()));
                placeOrderDetailShow.setOrderId(Long.parseLong(objs[1].toString()));
                placeOrderDetailShow.setMaterialId(Long.parseLong(objs[2].toString()));
                placeOrderDetailShow.setMaterialName(objs[3].toString());
                placeOrderDetailShow.setMaterialPrice(price);
                placeOrderDetailShow.setCount(count);
                placeOrderDetailShow.setCreator(objs[6].toString());
                placeOrderDetailShow.setCreateTime((Date) objs[7]);
                placeOrderDetailShow.setCourseCode(courseCode);
                placeOrderDetailShow.setCourseName(CourseEnum.getDescn(courseCode));
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
