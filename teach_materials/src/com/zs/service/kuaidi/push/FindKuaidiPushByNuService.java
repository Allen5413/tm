package com.zs.service.kuaidi.push;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.kuaidi.KuaidiPush;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/10/10.
 */
public interface FindKuaidiPushByNuService extends EntityService<KuaidiPush> {
    public JSONArray find(String nu)throws Exception;
}
