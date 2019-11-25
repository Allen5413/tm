package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterial;

import java.util.Map;

/**
 * Created by Allen on 2015/4/29.
 */
public interface FindTeachMaterialPageByWhereService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;

    /**
     * 这个是给教材管理分页查询用的。
     * 上一个分页方法查询结果带了courseCode和courseName；对于一门教材关联多个课程的，页面上会出现重复数据
     * 所以新增个方法，不返回course和courseName
     * @param pageInfo
     * @param paramsMap
     * @param sortMap
     * @return
     */
    public PageInfo findPageByWhere2(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
