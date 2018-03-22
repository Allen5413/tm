package com.zs.service.basic.press.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.press.FindPressByCodeDao;
import com.zs.dao.basic.press.FindPressByNameDao;
import com.zs.dao.basic.press.PressDao;
import com.zs.domain.basic.Press;
import com.zs.service.basic.press.AddPressService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 实现出版社新增的接口
 * Created by LihongZhang on 2015/5/3.
 */
@Service("addPressService")
public class AddPressServiceImp extends EntityServiceImpl<Press, PressDao> implements AddPressService {

    @Resource
    private FindPressByNameDao findPressByNameDao;

    @Resource
    private FindPressByCodeDao findPressByCodeDao;

    @Override
    public void addPress(Press press, String pressName, String userName) throws Exception {
        //如果press不为空就执行验证
        if (null != press) {
            //验证出版社名称和编号是否重复
            if (null != findPressByCodeDao.getPressByCode(press.getCode())) {
                throw new BusinessException("出版社编号已存在");
            }
            if (null != findPressByNameDao.getPressByPressName(press.getName())) {
                throw new BusinessException("出版社名称已存在");
            }
            //添加创建人
            press.setCreator(userName);
            //添加新增出版社的操作员
            press.setOperator(userName);
            super.save(press);
        }
    }
}
