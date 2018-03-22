package com.zs.service.basic.stockallotlog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.StockAllotLog;

/**
 * Created by Allen on 2015/5/29.
 */
public interface AddStockAllotLogService extends EntityService<StockAllotLog> {
    public void addStockAllotLog(StockAllotLog stockAllotLog, String loginName)throws Exception;
}
