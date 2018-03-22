package com.zs.service.basic.press;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Press;

import java.util.Map;

/**
 * 分頁查詢出版社信息
 * Created by LihongZhang on 2015/5/3.
 */
public interface FindPressPageByWhereService extends EntityService<Press>{
    public PageInfo<Press> findPageByWhere(PageInfo<Press> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
