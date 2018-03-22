package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by Allen on 2015/10/14.
 */
public interface DownStuEService extends EntityService {
    public String down(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap, String fileName)throws Exception;
}
