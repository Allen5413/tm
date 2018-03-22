package com.zs.dao.basic.spotteachmaterialstock;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SpotTeachMaterialStock;
import org.springframework.data.jpa.repository.Query;

/**
 * 根据教材的id和学习中心编号查询教材库存
 * Created by LihongZhang on 2015/5/9.
 */
public interface FindStockByTeachMaterialIdAndSpotCodeDao extends EntityJpaDao<SpotTeachMaterialStock,Long> {

    @Query("from SpotTeachMaterialStock where teachMaterialId = ?1 and spotCode = ?2")
    public SpotTeachMaterialStock getStockByTeachMaterialIdAndSpotCode(Long teachMaterialId,String spotCode);
}
