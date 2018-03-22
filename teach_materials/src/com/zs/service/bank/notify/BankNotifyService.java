package com.zs.service.bank.notify;

import java.util.Map;

/**
 * Created by Allen on 2015/10/28.
 */
public interface BankNotifyService {
    public String notify(Map<String,String> params, String method, String flag) throws Exception;
}
