package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spot.FindSpotByBankCodeDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.EditSpotService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/3/24.
 */
@Service("editSpotService")
public class EditSpotServiceImpl extends EntityServiceImpl<Spot, SpotDAO> implements EditSpotService {

    @Resource
    private FindSpotByBankCodeDAO findSpotByBankCodeDAO;

    @Override
    public void edit(Spot spot) {
        if(null != spot) {
            if(!StringUtils.isEmpty(spot.getBankCode())) {
                Spot searchSpot = findSpotByBankCodeDAO.find(spot.getBankCode());
                if(null != searchSpot && !searchSpot.getCode().equals(spot.getCode())){
                    throw new BusinessException("银行账号："+spot.getBankCode()+", 已经存在");
                }
            }
            spot.setOperateTime(DateTools.getLongNowTime());
            findSpotByBankCodeDAO.update(spot);
        }
    }
}
