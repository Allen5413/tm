package com.zs.dao.sale.onceorderlog;

import java.util.List;

/**
 * Created by Allen on 2015/7/10.
 */
public interface BatchOnceOrderLogDAO {
    public void batchAdd(List list, int num) throws Exception;
}
