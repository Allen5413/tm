package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByIdCardDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.AddSpotWxService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/9/8.
 */
@Service("addSpotWxService")
public class AddSpotWxServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByIdCardDAO> implements AddSpotWxService {

    @Resource
    private FindSpotWxByIdCardDAO findSpotWxByIdCardDAO;

    @Override
    public synchronized void add(SpotWx spotWx) {
        if(null != spotWx) {
            SpotWx spotWx1 = findSpotWxByIdCardDAO.find(spotWx.getIdcard());
            if(null != spotWx1 && !StringUtils.isEmpty(spotWx1.getIdcard())){
                throw new BusinessException("身份证号："+spotWx1.getIdcard()+", 已经存在");
            }
            findSpotWxByIdCardDAO.save(spotWx);
        }
    }
}
