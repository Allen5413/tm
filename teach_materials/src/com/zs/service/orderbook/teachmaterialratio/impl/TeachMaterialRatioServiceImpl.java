package com.zs.service.orderbook.teachmaterialratio.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.teachmaterialratio.TeachMaterialRatioDAO;
import com.zs.domain.orderbook.TeachMaterialRatio;
import com.zs.service.orderbook.teachmaterialratio.TeachMaterialRatioService;
import org.springframework.stereotype.Service;

/**
* Created by Allen on 2015/5/4.
*/
@Service("teachMaterialRatioService")
public class TeachMaterialRatioServiceImpl extends EntityServiceImpl<TeachMaterialRatio, TeachMaterialRatioDAO> implements TeachMaterialRatioService {
}
