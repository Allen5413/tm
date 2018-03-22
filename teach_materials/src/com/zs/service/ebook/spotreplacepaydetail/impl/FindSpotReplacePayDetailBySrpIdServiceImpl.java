package com.zs.service.ebook.spotreplacepaydetail.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.spotreplacepaydetail.FindSpotReplacePayDetailBySrpIdDAO;
import com.zs.dao.ebook.spotreplacepaydetail.SpotReplacePayDetailDAO;
import com.zs.domain.ebook.SpotReplacePayDetail;
import com.zs.service.ebook.spotreplacepaydetail.FindSpotReplacePayDetailBySrpIdService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2018/1/3.
 */
@Service("findSpotReplacePayDetailBySrpIdService")
public class FindSpotReplacePayDetailBySrpIdServiceImpl extends EntityServiceImpl<SpotReplacePayDetail, SpotReplacePayDetailDAO>
        implements FindSpotReplacePayDetailBySrpIdService {

    @Resource
    private FindSpotReplacePayDetailBySrpIdDAO findSpotReplacePayDetailBySrpIdDAO;

    @Override
    @Transactional
    public List<JSONObject> find(long srpId) throws Exception {
        List<Object[]> list = findSpotReplacePayDetailBySrpIdDAO.find(srpId);
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        if(null != list && 0 < list.size()){
            for(Object[] objs : list){
                JSONObject json = new JSONObject();
                json.put("id", objs[0]);
                json.put("code", objs[1]);
                json.put("name", objs[2]);
                json.put("money", objs[3]);
                json.put("moneyLong", objs[4]);
                jsonList.add(json);
            }
        }
        return jsonList;
    }
}
