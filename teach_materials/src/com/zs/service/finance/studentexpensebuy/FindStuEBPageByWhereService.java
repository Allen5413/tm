package com.zs.service.finance.studentexpensebuy;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpenseBuy;

import java.util.Map;

/**
 * 根据学生学号查询学生消费明细的接口
 * Created by LihongZhang on 2015/5/15.
 */
public interface FindStuEBPageByWhereService extends EntityService<StudentExpenseBuy> {
    public PageInfo<StudentExpenseBuy> findPageByStuCode(PageInfo<StudentExpenseBuy> pageInfo, Map<String,String> map,Map<String,Boolean> sortMap) throws Exception;
}
