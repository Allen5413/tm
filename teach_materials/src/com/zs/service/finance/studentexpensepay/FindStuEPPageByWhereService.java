package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpensePay;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 根据学生学号分页查询学生入账明细的接口
 * Created by LihongZhang on 2015/5/15.
 */
public interface FindStuEPPageByWhereService extends EntityService<StudentExpensePay>{

    /**
     * 根据学生学号分页查询入账明细的方法
     * @param payPageInfo
     * @param map
     * @param sortMap
     * @return
     * @throws Exception
     */
    public JSONObject findPage(PageInfo payPageInfo,Map<String,String> map,Map<String,Boolean> sortMap) throws Exception;
}
