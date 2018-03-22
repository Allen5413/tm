package com.zs.service.orderbook.teachmaterialratio.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.teachmaterialratio.FindTotalPriceBySemesterDAO;
import com.zs.service.orderbook.teachmaterialratio.FindTotalPriceBySemesterService;
import com.zs.tools.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/12.
 */
@Service("findTotalPriceBySemesterService")
public class FindTotalPriceBySemesterServiceImpl extends EntityServiceImpl implements FindTotalPriceBySemesterService {

    @Resource
    private FindTotalPriceBySemesterDAO findTotalPriceBySemesterDAO;

    @Override
    public String getTotalPriceBySemester(Long semesterId) {
        return StringTools.getFinancePrice(findTotalPriceBySemesterDAO.getTotalPriceBySemester(semesterId));
    }
}
