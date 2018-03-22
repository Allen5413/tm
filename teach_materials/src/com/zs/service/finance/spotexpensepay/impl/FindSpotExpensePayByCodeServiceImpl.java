package com.zs.service.finance.spotexpensepay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.spotexpensepay.FindSpotExpensePayByCodeDAO;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.service.finance.spotexpensepay.FindSpotExpensePayByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/8.
 */
@Service("findSpotExpensePayByCodeService")
public class FindSpotExpensePayByCodeServiceImpl
    extends EntityServiceImpl<SpotExpensePay, FindSpotExpensePayByCodeDAO>
    implements FindSpotExpensePayByCodeService {

    @Resource
    private FindSpotExpensePayByCodeDAO findSpotExpensePayByCodeDAO;

    @Override
    public List<SpotExpensePay> getSpotExpensePayByCode(String code) {
        return findSpotExpensePayByCodeDAO.getSpotExpensePayByCode(code);
    }
}
