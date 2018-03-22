package com.zs.service.basic.teachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialStock;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/9/7.
 */
public interface UploadStockService extends EntityService<TeachMaterialStock> {
    public String upload(HttpServletRequest request)throws Exception;
}
