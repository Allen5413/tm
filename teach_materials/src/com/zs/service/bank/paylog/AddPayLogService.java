package com.zs.service.bank.paylog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.bank.BankPayLog;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/10/27.
 */
public interface AddPayLogService extends EntityService<BankPayLog> {
    public String add(String money, String payUserCode, int payUserType, HttpServletRequest request)throws Exception;
}
