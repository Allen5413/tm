package com.zs.service.ebook.spotreplacepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.SpotReplacePay;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2018/1/3.
 */
public interface AuditSpotReplacePayService extends EntityService<SpotReplacePay> {
    public void audit(long id, int state, String arrivalTime, int payType, String remark, String loginName)throws Exception;
}
