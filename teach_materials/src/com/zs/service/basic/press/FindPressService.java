package com.zs.service.basic.press;


import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Press;

/**
 * 根据出版社名称查询出版社的接口
 * Created by LihongZhang on 2015/4/30.
 */
public interface FindPressService extends EntityService<Press>{
    /**
     * 根据出版社的名称查询出版社信息
     * @param pressName 出版社名称
     * @return  返回出版社对象
     * @throws Exception
     */
    public Press findPress(String pressName) throws Exception;
}
