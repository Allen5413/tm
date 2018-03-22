package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByIdCardDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.EditSpotWxService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/3/24.
 */
@Service("editSpotWxService")
public class EditSpotWxServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByIdCardDAO> implements EditSpotWxService {

    @Resource
    private FindSpotWxByIdCardDAO findSpotWxByIdCardDAO;

    @Override
    public void edit(long id, String idcard, String name, String loginName) {
        SpotWx spotWx = findSpotWxByIdCardDAO.get(id);
        SpotWx spotWx1 = findSpotWxByIdCardDAO.find(idcard);
        if(null != spotWx1 && id != spotWx1.getId()){
            throw new BusinessException("身份证号："+idcard+", 已经存在");
        }
        spotWx.setIdcard(idcard);
        spotWx.setAdminName(name);
        spotWx.setOperator(loginName);
        spotWx.setOperateTime(DateTools.getLongNowTime());
        findSpotWxByIdCardDAO.update(spotWx);
    }
}
