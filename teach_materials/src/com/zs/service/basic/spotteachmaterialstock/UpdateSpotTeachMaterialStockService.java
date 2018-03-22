package com.zs.service.basic.spotteachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotTeachMaterialStock;

/**
 * 用于修改学习中心教材库的接口
 * Created by LihongZhang on 2015/5/8.
 */
public interface UpdateSpotTeachMaterialStockService extends EntityService<SpotTeachMaterialStock> {

    /**
     * 用于修改学习中心教材库的方法
     * @param spotTeachMaterialStock 新的数据
     * @param changeNum 需要减少的数量
     * @throws Exception
     */
    public void updateSpotTeachMaterialStock(SpotTeachMaterialStock spotTeachMaterialStock, int changeNum) throws Exception;
}
