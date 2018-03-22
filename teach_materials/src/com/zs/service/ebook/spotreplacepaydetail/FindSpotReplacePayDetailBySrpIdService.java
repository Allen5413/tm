package com.zs.service.ebook.spotreplacepaydetail;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePayDetail;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2018/1/3.
 */
public interface FindSpotReplacePayDetailBySrpIdService extends EntityService<SpotReplacePayDetail> {
    public List<JSONObject> find(long srpId)throws Exception;
}
