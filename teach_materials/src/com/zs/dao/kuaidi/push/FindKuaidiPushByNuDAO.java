package com.zs.dao.kuaidi.push;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.kuaidi.KuaidiPush;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/10.
 */
public interface FindKuaidiPushByNuDAO extends EntityJpaDao<KuaidiPush, Long> {

    @Query("from KuaidiPush where nu = ?")
    public KuaidiPush find(String nu);
}
