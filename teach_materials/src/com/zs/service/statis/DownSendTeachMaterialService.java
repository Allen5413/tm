package com.zs.service.statis;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/12/2.
 */
public interface DownSendTeachMaterialService {
    public String down(String semesterId, String name, String beginDate, String endDate, String tmTypeId, String fileName)throws Exception;
}
