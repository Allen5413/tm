package com.zs.service.orderbook.teachmaterialratio;

import com.feinno.framework.common.service.EntityService;

/**
 * 统计自动生成的购书单的总金额
 * Created by Allen on 2015/5/12.
 */
public interface FindTotalPriceBySemesterService extends EntityService {
    public String getTotalPriceBySemester(Long semesterId);
}
