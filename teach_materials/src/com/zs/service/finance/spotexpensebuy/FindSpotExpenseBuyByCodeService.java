package com.zs.service.finance.spotexpensebuy;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.SpotExpenseBuy;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/6/8.
 */
public interface FindSpotExpenseBuyByCodeService extends EntityService {
    public Map<String, Map<Double, List<SpotExpenseBuy>>> getSpotExpenseBuyByCode(String code);
}
