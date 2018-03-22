package com.zs.service.finance.spotexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpense;

/**
 * 用于确认学习中心中待确认金额的接口
 * Created by LihongZhang on 2015/5/17.
 */
public interface InsureSpotMoneyService extends EntityService<SpotExpense> {

    /**
     * 确认金额的方法
     * @param insureMoney
     * @param spotCode
     * @param userName
     * @throws Exception
     */
    public void insureMoney(Float insureMoney, String spotCode, String userName) throws Exception;
}
