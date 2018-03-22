package com.zs.service.orderbook.teachmaterialratio.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.teachmaterialratio.FindTeachMaterialRatioBySemesterIdDAO;
import com.zs.dao.orderbook.teachmaterialratio.TeachMaterialRatioDAO;
import com.zs.domain.orderbook.TeachMaterialRatio;
import com.zs.service.orderbook.teachmaterialratio.FindTeachMaterialRatioBySemesterIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/6.
 */
@Service("findTeachMaterialRatioBySemesterIdService")
public class FindTeachMaterialRatioBySemesterIdServiceImpl
        extends EntityServiceImpl<TeachMaterialRatio, TeachMaterialRatioDAO>
implements FindTeachMaterialRatioBySemesterIdService {

    @Resource
    public FindTeachMaterialRatioBySemesterIdDAO findTeachMaterialRatioBySemesterIdDAO;

    @Override
    public TeachMaterialRatio getTeachMaterialRatioBySemesterId(Long semesterId) {
        return findTeachMaterialRatioBySemesterIdDAO.getTeachMaterialRatioBySemesterId(semesterId);
    }
}
