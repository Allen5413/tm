package com.zs.dao.sync.spotwx;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SpotWx;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/9/8.
 */
public interface FindSpotWxByCodeDAO  extends EntityJpaDao<SpotWx, Long> {
    @Query("from SpotWx where code = ?1 order by id")
    public List<SpotWx> find(String code);
}
