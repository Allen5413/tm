package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByIdCardDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.EditSpotWxOpenIdByIdCardService;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("editSpotWxOpenIdByIdCardService")
public class EditSpotWxOpenIdByIdCardServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByIdCardDAO> implements EditSpotWxOpenIdByIdCardService {

    @Resource
    private FindSpotWxByIdCardDAO findSpotWxByIdCardDAO;
    @Resource
    private FindSpotWxByOpenIdService findSpotWxByOpenIdService;

    @Override
    @Transactional
    public synchronized JSONObject edit(String idCard, String openId, Timestamp boundTime) throws Exception {
        int flag = 0;
        String msg = "";
        if(StringUtils.isEmpty(idCard)){
            flag = 1;
            msg = "没有传入身份证号";
        }
        if(StringUtils.isEmpty(openId)){
            flag = 1;
            msg = "没有传入openId";
        }
        SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
        if(null != spotWx){
            flag = 2;
            msg = "您已经绑定了，如需重新绑定请先在更多服务里面解除绑定。";
        }else{
            spotWx = findSpotWxByIdCardDAO.find(idCard);
            if(null == spotWx){
                flag = 1;
                msg = "没有找到用户信息";
            }else{
                if(!StringUtils.isEmpty(spotWx.getOpenId())){
                    flag = 1;
                    msg = "该证件号码已经被绑定了";
                }else {
                    spotWx.setOpenId(openId);
                    spotWx.setBoundTime(boundTime);
                    super.update(spotWx);
                    msg = spotWx.getAdminName();
                }
            }
        }
        JSONObject json = new JSONObject();
        json.put("flag", flag);
        json.put("msg", msg);
        return json;
    }

    @Override
    public JSONObject cancelBound(String openId) throws Exception {
        JSONObject json = new JSONObject();
        int flag = 0;
        String msg = "";
        if(StringUtils.isEmpty(openId)){
            flag = 1;
            msg = "没有传入openId";
        }
        SpotWx spotWx = findSpotWxByOpenIdService.find(openId);
        if(null == spotWx){
            flag = 1;
            msg = "你目前还没有绑定该公众号";
        }else{
            msg = "解除绑定";
            String time = spotWx.getBoundTimeStr();
            String name = spotWx.getAdminName();
            spotWx.setOpenId("");
            spotWx.setBoundTime(null);
            super.update(spotWx);

            json.put("time", time);
            json.put("name", name);
        }

        json.put("flag", flag);
        json.put("msg", msg);
        return json;
    }
}
