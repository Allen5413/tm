package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spot.FindSpotByBankCodeDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.AddSpotService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/3/24.
 */
@Service("addSpotService")
public class AddSpotServiceImpl extends EntityServiceImpl<Spot, SpotDAO> implements AddSpotService {

    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindSpotByBankCodeDAO findSpotByBankCodeDAO;

    @Override
    public void add(Spot spot) {
        if(null != spot) {
            Spot searchSpot = findSpotByCodeService.getSpotByCode(spot.getCode());
            if(null != searchSpot && !StringUtils.isEmpty(searchSpot.getName())){
                throw new BusinessException("中心编号："+spot.getCode()+", 已经存在");
            }
            if(!StringUtils.isEmpty(spot.getBankCode())) {
                searchSpot = findSpotByBankCodeDAO.find(spot.getBankCode());
                if(null != searchSpot && !StringUtils.isEmpty(searchSpot.getName())){
                    throw new BusinessException("银行账号："+spot.getBankCode()+", 已经存在");
                }
            }
            findSpotByCodeService.save(spot);
        }
    }
}
