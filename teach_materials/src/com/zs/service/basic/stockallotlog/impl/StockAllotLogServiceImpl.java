package com.zs.service.basic.stockallotlog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.stockallotlog.StockAllotLogDAO;
import com.zs.domain.basic.StockAllotLog;
import com.zs.service.basic.stockallotlog.StockAllotLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/28.
 */
@Service("stockAllotLogService")
public class StockAllotLogServiceImpl extends EntityServiceImpl<StockAllotLog, StockAllotLogDAO>
    implements StockAllotLogService{
}
