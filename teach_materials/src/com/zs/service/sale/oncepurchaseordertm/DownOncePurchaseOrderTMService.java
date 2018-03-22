package com.zs.service.sale.oncepurchaseordertm;

import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/6/3.
 */
public interface DownOncePurchaseOrderTMService extends EntityService {
    public String down(String orderCode, Long semesterId, Long channelId, String fileName)throws Exception;
}
