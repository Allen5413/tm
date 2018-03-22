package com.zs.service.sync.spot;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created by Allen on 2015/6/1.
 */
public interface FindSpotByProvCodeService extends EntityService<Spot> {
    public List<Spot> getSpotForListByProvCode(String provCode);

    public JSONArray getSpotForJSONByProvCode(String provCode);
}
