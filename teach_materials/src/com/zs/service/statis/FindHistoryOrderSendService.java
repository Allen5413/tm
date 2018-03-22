package com.zs.service.statis;

import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/9/25.
 */
public interface FindHistoryOrderSendService {
    /**
     * 统计历史发出订单记录
     * @return
     * @throws Exception
     */
    public JSONArray findHistoryOrderSend(String isSpot)throws Exception;

    /**
     * 统计一个学期的学习中心订单发出情况
     * @param semesterId
     * @return
     * @throws Exception
     */
    public JSONArray findSpotOrderSendBySemesterId(long semesterId)throws Exception;
}
