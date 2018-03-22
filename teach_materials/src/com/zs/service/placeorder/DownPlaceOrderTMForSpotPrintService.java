package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/8/18.
 */
public interface DownPlaceOrderTMForSpotPrintService extends EntityService {
    public String down(String spotCode, String semesterId, String state, String fileName)throws Exception;
}
