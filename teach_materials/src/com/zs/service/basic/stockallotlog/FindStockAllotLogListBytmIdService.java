package com.zs.service.basic.stockallotlog;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/5/28.
 */
public interface FindStockAllotLogListBytmIdService extends EntityService {
    public JSONArray getStockAllotLogListBytmId(Long tmId);
}
