package com.zs.service.basic.press.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.press.PressDao;
import com.zs.domain.basic.Press;
import com.zs.service.basic.press.FindPressPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 实现分页查询出版社信息的接口
 * Created by LihongZhang on 2015/5/3.
 */
@Service("findPressPageByWhereService")
public class FindPressPageByWhereServiceImp extends EntityServiceImpl<Press,PressDao> implements FindPressPageByWhereService{

    @Resource
    private FindPageByWhereDAO findPressPageByWhereDao;

    @Override
    public PageInfo<Press> findPageByWhere(PageInfo<Press> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findPressPageByWhereDao.findPageByWhere(pageInfo,map,sortMap);
    }
}
