package com.zs.service.basic.teachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialStock;

/**
 * Created by Allen on 2015/7/29.
 */
public interface FindTeachMaterialStockBytmIdAndChannelIdService extends EntityService {
    public TeachMaterialStock findTeachMaterialStockBytmIdAndChannelId(Long tmId, long channelId)throws Exception;
}
