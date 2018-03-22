package com.zs.service.basic.press.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.press.FindPressByNameDao;
import com.zs.domain.basic.Press;
import com.zs.service.basic.press.FindPressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 实现根据出版社名称查找出版社信息接口
 * Created by LihongZhang on 2015/4/30.
 */
@Service("findPressService")
public class FindPressByNameServiceImpl extends EntityServiceImpl<Press, FindPressByNameDao> implements FindPressService {

    @Resource
    private FindPressByNameDao findPressByNameDao;

    @Override
    public Press findPress(String pressName) throws Exception {
        return findPressByNameDao.getPressByPressName(pressName);
    }
}
