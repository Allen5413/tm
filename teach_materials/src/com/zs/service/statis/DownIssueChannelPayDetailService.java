package com.zs.service.statis;

import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/11/25 0025.
 */
public interface DownIssueChannelPayDetailService {
    public String down(long semesterId, long icId, String type, String title, String fileName)throws Exception;
}
