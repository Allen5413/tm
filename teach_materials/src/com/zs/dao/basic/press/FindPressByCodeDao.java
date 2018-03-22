package com.zs.dao.basic.press;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Press;
import org.springframework.data.jpa.repository.Query;

/**
 * 根据编号查询出版社信息
 * Created by LihongZhang on 2015/5/7.
 */
public interface FindPressByCodeDao extends EntityJpaDao<Press,Long> {
    @Query("from Press where code = ?1")
    public Press getPressByCode(String code);
}
