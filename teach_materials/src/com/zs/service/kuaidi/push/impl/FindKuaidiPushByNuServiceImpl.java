package com.zs.service.kuaidi.push.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.kuaidi.push.FindKuaidiPushByNuDAO;
import com.zs.domain.kuaidi.KuaidiPush;
import com.zs.service.kuaidi.push.FindKuaidiPushByNuService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/10/10.
 */
@Service("findKuaidiPushByNuService")
public class FindKuaidiPushByNuServiceImpl extends EntityServiceImpl<KuaidiPush, FindKuaidiPushByNuDAO>
    implements FindKuaidiPushByNuService{

    @Resource
    private FindKuaidiPushByNuDAO findKuaidiPushByNuDAO;

    @Override
    public JSONArray find(String nu)throws Exception{
        JSONArray jsonArray = new JSONArray();
        KuaidiPush kuaidiPush = findKuaidiPushByNuDAO.find(nu);
        if(null != kuaidiPush){
            jsonArray = JSONArray.fromObject(kuaidiPush.getData());
        }
        return jsonArray;
    }
}
