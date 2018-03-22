package com.zs.dao.sale.onceordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.sale.onceordertm.BatchOnceOrderTMDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2015/7/10.
 */
@Service("batchOnceOrderTMDAO")
public class BatchOnceOrderTMDAOImpl extends BaseQueryDao implements BatchOnceOrderTMDAO {
    @Override
    public void batchAdd(List list, int num) throws Exception {
        super.batchInsert(list, num);
    }
}
