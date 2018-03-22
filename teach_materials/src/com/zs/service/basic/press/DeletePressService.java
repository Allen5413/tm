package com.zs.service.basic.press;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Press;

/**
 * 删除出版社的接口
 * Created by LihongZhang on 2015/5/3.
 */
public interface DeletePressService extends EntityService<Press> {
    /**
     *  删除出版社的方法
     * @param id 出版社id
     * @throws Exception
     */
    public void deletePressById(Long id) throws Exception;
}
