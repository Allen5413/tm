package com.zs.service.ebook.spotreplacepay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.spotreplacepay.SpotReplacePayDAO;
import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.FindSpotReplacePayByIdService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2018/1/3.
 */
@Service("findSpotReplacePayByIdService")
public class FindSpotReplacePayByIdServiceImpl extends EntityServiceImpl<SpotReplacePay, SpotReplacePayDAO>
    implements FindSpotReplacePayByIdService{

    @Override
    public SpotReplacePay find(long id) throws Exception {
        return super.get(id);
    }
}
