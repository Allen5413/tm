package com.zs.service.basic.press.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.press.FindPressByCodeDao;
import com.zs.dao.basic.press.FindPressByNameDao;
import com.zs.dao.basic.press.PressDao;
import com.zs.domain.basic.Press;
import com.zs.service.basic.press.EditPressService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 实现了修改出版社接口
 * Created by LihongZhang on 2015/5/3.
 */
@Service("editPressService")
public class EditPressServiceImp extends EntityServiceImpl<Press,PressDao> implements EditPressService{

    @Resource
    private FindPressByNameDao findPressByNameDao;
    @Resource
    private FindPressByCodeDao findPressByCodeDao;

    @Override
    public void editPress(Press press, String userName) throws Exception {
        if (null != press){
            //验证所编辑的出版社是否存在
            Press validPress = findPressByNameDao.get(press.getId());
            if (null == validPress){
                throw new BusinessException("所编辑的出版社不存在");
            }
            //判断是否修改过出版社名称
            if (!press.getName().equals(validPress.getName())){ //修改了出版社名称，则验证出版社名称是否重复
                if (null != findPressByNameDao.getPressByPressName(press.getName())){
                    throw new BusinessException("出版社名称重复");
                }
            }
            //判断是否修改过出版社编号
            if (!press.getCode().equals(validPress.getCode())){
                //修改了出版社编号，则验证是否重复
                if (null != findPressByCodeDao.getPressByCode(press.getCode())){
                    throw new BusinessException("出版社编号重复");
                }
            }
//            不重复就执行更新操作
//            创建时间和创建人不能改变
            press.setCreator(validPress.getCreator());
            press.setCreateTime(validPress.getCreateTime());
            //写入操作人
            press.setOperator(userName);
            //执行更新
            super.update(press);
        }
    }
}
