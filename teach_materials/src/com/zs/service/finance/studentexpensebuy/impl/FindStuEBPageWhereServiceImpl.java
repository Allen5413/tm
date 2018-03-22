package com.zs.service.finance.studentexpensebuy.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.studentexpensebuy.StudentExpenseBuyDao;
import com.zs.domain.finance.StudentExpenseBuy;
import com.zs.service.finance.studentexpensebuy.FindStuEBPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 实现FindStuEBPageByStuCodeService接口
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEBPageByWhereService")
public class FindStuEBPageWhereServiceImpl extends EntityServiceImpl<StudentExpenseBuy,StudentExpenseBuyDao> implements FindStuEBPageByWhereService {

    @Resource
    private FindPageByWhereDAO findStuEBPageByWhereDao;

    @Override
    public PageInfo<StudentExpenseBuy> findPageByStuCode(PageInfo<StudentExpenseBuy> pageInfo, Map<String,String> map,Map<String,Boolean> sortMap) throws Exception {
        return findStuEBPageByWhereDao.findPageByWhere(pageInfo,map,sortMap);
    }
}
