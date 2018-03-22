package com.zs.service.basic.spotteachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotTeachMaterialStock;

/**
 * 根据教材的名称查询库存数量的service
 * Created by LihongZhang on 2015/5/8.
 */
public interface FindStockNumByTeachMaterialIdAndSpotCodeService extends EntityService<SpotTeachMaterialStock>{

    /**
     * 根据教材的id和学习中心的编号查询教材的库存数量
     * @param teachMaterialId
     * @param spotCode
     * @return
     * @throws Exception
     */
    public int getStockByTeachMaterialIdAndSpotCode(Long teachMaterialId,String spotCode) throws Exception;
}
