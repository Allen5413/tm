package com.zs.service.finance.spotexpensepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpensePay;

import java.util.Map;

/**
 * 根据学习中心编号分页查询入账明细信息的几口
 * Created by LihongZhang on 2015/5/17.
 */
public interface FindSpotEPPageByWhereService extends EntityService<SpotExpensePay> {
    /**
     * 根据学生学号分页查询入账明细的方法
     * @param payPageInfo
     * @param map
     * @param sortMap
     * @return
     * @throws Exception
     */
    public PageInfo<SpotExpensePay> findPage(PageInfo<SpotExpensePay> payPageInfo, Map<String,String> map,Map<String,Boolean> sortMap) throws Exception;
}
