package com.zs.dao.basic.usergroup;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.UserGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一个用户关联得用户组
 * Created by Allen on 2015/5/15.
 */
public interface FindUserGroupByUserNameDAO extends EntityJpaDao<UserGroup, Long> {
    @Query(nativeQuery = true, value = "select ug.* from user_group ug, user_group_user ugu where ug.id = ugu.user_group_id and ugu.user_name = ?1")
    public List<UserGroup> getUserGroupByUserName(String userName);
}
