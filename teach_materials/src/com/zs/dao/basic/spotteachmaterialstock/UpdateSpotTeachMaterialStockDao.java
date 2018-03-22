package com.zs.dao.basic.spotteachmaterialstock;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SpotTeachMaterialStock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 用于修改学习中心教材库指定教材的库存
 * Created by LihongZhang on 2015/5/8.
 */
public interface UpdateSpotTeachMaterialStockDao extends EntityJpaDao<SpotTeachMaterialStock,Long> {

    @Modifying
    @Query("update SpotTeachMaterialStock set stock = ?1 where spotCode = ?2 and teachMaterialId = ?3")
    public void updateSpotTeachMaterialStock(int stock,String spotCode,Long teachMaterialId);

}
