package com.zs.service.basic.press;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Press;

/**
 * 用于添加出版社的接口
 * Created by LihongZhang on 2015/5/3.
 */
public interface AddPressService extends EntityService<Press>{
    /**
     * 用于添加出版社的方法
     * @param press 出版社对象
     * @param pressName 新增出版社的名称
     * @param userName 操作员姓名
     * @throws Exception
     */
    public void addPress(Press press,String pressName,String userName) throws Exception;
}
