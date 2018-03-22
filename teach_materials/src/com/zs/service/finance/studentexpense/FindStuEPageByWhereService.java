package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpense;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 分页查询学生费用的service层接口
 * Created by LihongZhang on 2015/5/15.
 */
public interface FindStuEPageByWhereService extends EntityService{
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
