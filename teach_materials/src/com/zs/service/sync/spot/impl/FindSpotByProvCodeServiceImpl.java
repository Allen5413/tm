package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spot.FindSpotAllDAO;
import com.zs.dao.sync.spot.FindSpotByProvCodeDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/1.
 */
@Service("findSpotByProvCodeService")
public class FindSpotByProvCodeServiceImpl extends EntityServiceImpl<Spot, FindSpotByProvCodeDAO> implements FindSpotByProvCodeService {

    @Resource
    private FindSpotByProvCodeDAO findSpotByProvCodeDAO;
    @Resource
    private FindSpotAllDAO findSpotAllDAO;

    @Override
    public List<Spot> getSpotForListByProvCode(String provCode) {
        if(StringUtils.isEmpty(provCode)){
            return findSpotAllDAO.getSpotAll();
        }else {
            return findSpotByProvCodeDAO.getSpotByProvCode(provCode);
        }
    }

    @Override
    public JSONArray getSpotForJSONByProvCode(String provCode) {
        List<Spot> spotList = getSpotForListByProvCode(provCode);
        if(null != spotList && 0 <spotList.size()){
            JSONArray jsonArray = new JSONArray();
            for (Spot spot : spotList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", spot.getCode());
                jsonObject.put("name", spot.getName());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
        return null;
    }
}
