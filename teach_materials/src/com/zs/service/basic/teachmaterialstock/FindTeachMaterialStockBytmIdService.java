package com.zs.service.basic.teachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/5/20.
 */
public interface FindTeachMaterialStockBytmIdService extends EntityService {
    public JSONArray getTeachMaterialStockBytmId(Long tmId)throws Exception;
}
