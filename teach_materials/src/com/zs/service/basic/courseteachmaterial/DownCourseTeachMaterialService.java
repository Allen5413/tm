package com.zs.service.basic.courseteachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by Allen on 2015/12/16.
 */
public interface DownCourseTeachMaterialService extends EntityService {
    public String down(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap, String fileName) throws Exception;
}
