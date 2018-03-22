package com.zs.service.finance.refund;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Refund;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/1/11.
 */
public interface UploadApplyImgService extends EntityService<Refund> {
    public void upload(String code, HttpServletRequest request) throws Exception;
}
