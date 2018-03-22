package com.zs.service.sync.eduwestuser.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.eduwestuser.FindEduwestUserBySpotCodeDAO;
import com.zs.domain.sync.EduwestUser;
import com.zs.service.sync.eduwestuser.FindEduwestUserBySpotCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/8/18.
 */
@Service("findEduwestUserBySpotCodeService")
public class FindEduwestUserBySpotCodeServiceImpl extends EntityServiceImpl<EduwestUser, FindEduwestUserBySpotCodeDAO>
        implements FindEduwestUserBySpotCodeService {

    @Resource
    private FindEduwestUserBySpotCodeDAO findEduwestUserBySpotCodeDAO;

    @Override
    public List<EduwestUser> getEduwestUserBySpotCode(String spotCode) {
        return findEduwestUserBySpotCodeDAO.getEduwestUserBySpotCode(spotCode);
    }
}
