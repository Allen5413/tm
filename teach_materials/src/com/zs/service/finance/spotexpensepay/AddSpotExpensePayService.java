package com.zs.service.finance.spotexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpensePay;

/**
 * 添加学习中心入账记录的接口
 * Created by LihongZhang on 2015/5/17.
 */
public interface AddSpotExpensePayService extends EntityService<SpotExpensePay> {

    /**
     * 添加学习中心入账记录的方法
     * @param spotExpensePay
     * @return
     * @throws Exception
     */
    public void addSpotEP(SpotExpensePay spotExpensePay, String userName) throws Exception;
}
