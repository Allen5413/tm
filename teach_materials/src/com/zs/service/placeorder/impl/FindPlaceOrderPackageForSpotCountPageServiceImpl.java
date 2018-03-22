package com.zs.service.placeorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.service.placeorder.FindPlaceOrderPackageForSpotCountPageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/3.
 */
@Service("findPlaceOrderPackageForSpotCountPageService")
public class FindPlaceOrderPackageForSpotCountPageServiceImpl extends EntityServiceImpl implements FindPlaceOrderPackageForSpotCountPageService {

    @Resource
    private FindPageByWhereDAO findPlaceOrderPackageForSpotCountPageDAO;

    /**
     * 统计学习中心未邮寄的包裹数量
     * @return
     */
    @Override
    @Transactional
    public PageInfo findPlaceOrderPackageForSpotCountPage(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        pageInfo = findPlaceOrderPackageForSpotCountPageDAO.findPageByWhere(pageInfo, paramsMap, sortMap);
        if (null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()) {
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for (Object[] objs : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("count", objs[2]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}

