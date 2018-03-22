package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2016/5/5.
 */
public interface DownStuEPService extends EntityService {
    public String down(PageInfo pageInfo, Map<String, String> map, String fileName)throws Exception;
}
