package com.zs.service.finance.spotexpensepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.spotexpensepay.SpotExpensePayDao;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.service.finance.spotexpensepay.FindSpotEPPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 实现分页查询入账明细的接口
 * Created by LihongZhang on 2015/5/17.
 */
@Service("findSpotEPPageByWhereService")
public class FindSpotEPPageByWhereServiceImpl extends EntityServiceImpl<SpotExpensePay,SpotExpensePayDao> implements FindSpotEPPageByWhereService {

    @Resource
    private FindPageByWhereDAO findSpotEPPageByWhereDao;

    @Override
    public PageInfo<SpotExpensePay> findPage(PageInfo<SpotExpensePay> payPageInfo, Map<String,String> map,Map<String,Boolean> sortMap) throws Exception {
        return findSpotEPPageByWhereDao.findPageByWhere(payPageInfo,map,sortMap);
    }
}
