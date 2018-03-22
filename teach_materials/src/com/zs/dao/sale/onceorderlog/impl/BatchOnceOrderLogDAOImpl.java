package com.zs.dao.sale.onceorderlog.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.sale.onceorderlog.BatchOnceOrderLogDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2015/7/10.
 */
@Service("batchOnceOrderLogDAO")
public class BatchOnceOrderLogDAOImpl extends BaseQueryDao implements BatchOnceOrderLogDAO {
    @Override
    public void batchAdd(List list, int num) throws Exception {
        super.batchInsert(list, num);
    }
}
