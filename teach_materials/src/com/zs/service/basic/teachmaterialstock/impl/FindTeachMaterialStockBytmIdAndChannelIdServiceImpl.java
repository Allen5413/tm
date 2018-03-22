package com.zs.service.basic.teachmaterialstock.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Created by Allen on 2015/7/29.
 */
@Service("findTeachMaterialStockBytmIdAndChannelIdService")
public class FindTeachMaterialStockBytmIdAndChannelIdServiceImpl extends EntityServiceImpl implements FindTeachMaterialStockBytmIdAndChannelIdService {

    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;

    @Override
    public TeachMaterialStock findTeachMaterialStockBytmIdAndChannelId(Long tmId, long channelId) throws Exception {
        return findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(tmId, channelId);
    }
}
