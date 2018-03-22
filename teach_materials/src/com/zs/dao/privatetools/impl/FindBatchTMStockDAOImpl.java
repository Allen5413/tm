package com.zs.dao.privatetools.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 批量修改教材数量，查询临时导入的要修改的教材数量的表
 * Created by Allen on 2015/8/9.
 */
@Service("findBatchTMStockDAO")
public class FindBatchTMStockDAOImpl  extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        StringBuilder sql = new StringBuilder("select * from aaa");
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), null);
        return list;
    }
}
