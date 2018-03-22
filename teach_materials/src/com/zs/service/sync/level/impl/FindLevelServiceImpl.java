package com.zs.service.sync.level.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.level.LevelDAO;
import com.zs.domain.sync.Level;
import com.zs.service.sync.level.FindLevelService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/26.
 */
@Service("findLevelService")
public class FindLevelServiceImpl extends EntityServiceImpl<Level, LevelDAO> implements FindLevelService {
}
