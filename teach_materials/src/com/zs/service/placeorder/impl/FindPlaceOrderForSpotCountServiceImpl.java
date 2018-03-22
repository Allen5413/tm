package com.zs.service.placeorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.FindPlaceOrderPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/31.
 */
@Service("findPlaceOrderForSpotCountService")
public class FindPlaceOrderForSpotCountServiceImpl extends EntityServiceImpl implements FindPlaceOrderPageByWhereService {

    @Resource
    private FindPageByWhereDAO findPlaceOrderForSpotCountDAO;

    @Override
    @Transactional
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findPlaceOrderForSpotCountDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                if(!StringUtils.isEmpty(objs[0].toString()) && !StringUtils.isEmpty(objs[1].toString())) {
                    JSONObject jsonObject = new JSONObject();
                    //查询已打印的，需要按打印时间统计
                    if(5 < objs.length) {
                        //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                        TeachMaterialPlaceOrder teachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
                        teachMaterialPlaceOrder.setOperateTime((Date) objs[3]);
                        JsonConfig jsonConfig = new JsonConfig();
                        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                        jsonObject = JSONObject.fromObject(teachMaterialPlaceOrder, jsonConfig);
                        jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                        jsonObject.put("spotCode", objs[0]);
                        jsonObject.put("spotName", objs[1]);
                        jsonObject.put("operator", objs[2]);
                        jsonObject.put("tmCount", objs[4]);
                        jsonObject.put("totalPrice", StringTools.getFinancePrice(objs[5].toString()));
                    }else{
                        jsonObject.put("spotCode", objs[0]);
                        jsonObject.put("spotName", objs[1]);
                        jsonObject.put("tmCount", objs[2]);
                        jsonObject.put("totalPrice", StringTools.getFinancePrice(objs[3].toString()));
                    }
                    jsonArray.add(jsonObject);
                }
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
