package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.FindOnceOrderForSpotCountService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/18.
 */
@Service("findOnceOrderForSpotCountService")
public class FindOnceOrderForSpotCountServiceImpl extends EntityServiceImpl implements FindOnceOrderForSpotCountService {

    @Resource
    private FindPageByWhereDAO findOnceOrderForSpotCountDAO;

    @Override
    @Transactional
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findOnceOrderForSpotCountDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                JSONObject jsonObject = new JSONObject();
                //查询已打印的，需要按打印时间统计
                if(5 < objs.length) {
                    //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                    StudentBookOnceOrder studentBookOnceOrder = new StudentBookOnceOrder();
                    studentBookOnceOrder.setOperateTime((Date) objs[5]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    jsonObject = JSONObject.fromObject(studentBookOnceOrder, jsonConfig);
                    jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                    jsonObject.put("operator", objs[6]);
                }
                jsonObject.put("spotCode", objs[0]);
                jsonObject.put("spotName", objs[1]);
                jsonObject.put("orderCount", objs[2]);
                jsonObject.put("tmCount", objs[3]);
                jsonObject.put("totalPrice", StringTools.getFinancePrice(objs[4].toString()));
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
