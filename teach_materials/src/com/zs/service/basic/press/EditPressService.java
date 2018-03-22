package com.zs.service.basic.press;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Press;

/**
 * 修改出版社接口
 * Created by LihongZhang on 2015/5/3.
 */
public interface EditPressService extends EntityService<Press> {
    /**
     * * 修改出版社信息的方法
     * @param press
     * @param userName
     * @throws Exception
     */
    public void editPress(Press press,String userName) throws Exception;
}
