package com.zs.dao.basic.resource;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Resource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一个用户组下的菜单和资源关系，返回结果主要用于生成zTree
 * Created by Allen on 2016/9/25 0025.
 */
public interface FindResourceForMenuByUserGroupIdDAO extends EntityJpaDao<Resource, Long> {
    @Query(nativeQuery = true, value = "select t.*, ugr.id ugId from (select m.id treeId, m.name, 0 pId, m.id rId from menu m " +
            "UNION ALL " +
            "select CONCAT(r.id, '_r') treeId, r.name, r.menu_id pId, r.id rId from resource r) t " +
            "left JOIN user_group_resource ugr on t.rId = ugr.resource_id and ugr.user_group_id = ?1 " +
            "order by t.pId, t.rId")
    public List<Object[]> find(long userGroupId);
}
