package com.zs.service.finance.spotexpense.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.spotexpense.SpotExpenseDao;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.EditSpotExpenseDiscountByIDService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/1/26.
 */
@Service("editSpotExpenseDiscountByIDService")
public class EditSpotExpenseDiscountByIDServiceImpl extends EntityServiceImpl<SpotExpense, SpotExpenseDao> implements EditSpotExpenseDiscountByIDService {

    @Override
    @Transactional
    public String edit(long id, int discount, String userName) throws Exception {
        SpotExpense spotExpense = super.get(id);
        if(null == spotExpense){
            throw new BusinessException("没有找到相关记录");
        }
        spotExpense.setDiscount(discount);
        spotExpense.setOperator(userName);
        spotExpense.setOperateTime(DateTools.getLongNowTime());
        super.update(spotExpense);
        return spotExpense.getSpotCode();
    }
}
