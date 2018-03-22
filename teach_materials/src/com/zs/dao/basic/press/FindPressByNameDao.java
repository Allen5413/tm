package com.zs.dao.basic.press;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Press;
import com.zs.domain.basic.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 根据出版社的名称查询出版社信息
 * Created by LihongZhang on 2015/4/30.
 */
public interface FindPressByNameDao extends EntityJpaDao<Press,Long>{
    @Query("from Press where name = ?1")
    public Press getPressByPressName(String pressName);
}
