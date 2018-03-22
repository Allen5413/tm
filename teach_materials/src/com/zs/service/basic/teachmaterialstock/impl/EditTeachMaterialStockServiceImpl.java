package com.zs.service.basic.teachmaterialstock.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialstock.TeachMaterialStockDAO;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.EditTeachMaterialStockService;
import org.springframework.stereotype.Service;


/**
 * Created by Allen on 2015/8/11.
 */
@Service("editTeachMaterialStockService")
public class EditTeachMaterialStockServiceImpl extends EntityServiceImpl<TeachMaterialStock, TeachMaterialStockDAO>
        implements EditTeachMaterialStockService {

    @Override
    public void edit(TeachMaterialStock teachMaterialStock, String loginName) throws Exception {
        if(null != teachMaterialStock) {
            super.update(teachMaterialStock);
        }
    }
}
