package com.zs.service.placeorder.placeorderpackage.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.PlaceOrderPackageDAO;
import com.zs.domain.placeorder.PlaceOrderPackage;
import com.zs.service.placeorder.placeorderpackage.EditPlaceOrderPackageForLogisticCodeService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/3/23.
 */
@Service("editPlaceOrderPackageForLogisticCodeService")
public class EditPlaceOrderPackageForLogisticCodeServiceImpl
        extends EntityServiceImpl<PlaceOrderPackage, PlaceOrderPackageDAO>
        implements EditPlaceOrderPackageForLogisticCodeService {


    @Override
    public void edit(long id, String logisticCodes, String loginName) {
        PlaceOrderPackage placeOrderPackage = super.get(id);
        if(null == placeOrderPackage){
            throw new BusinessException("没有找到该包裹");
        }
        if(StringUtils.isEmpty(logisticCodes)){
            throw new BusinessException("没有传入快递单号");
        }
        placeOrderPackage.setLogisticCode(logisticCodes.replaceAll("，", ",").trim());
        placeOrderPackage.setOperator(loginName);
        placeOrderPackage.setOperateTime(DateTools.getLongNowTime());
        super.update(placeOrderPackage);
    }
}
