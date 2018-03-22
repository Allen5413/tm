package com.zs.service.placeorder.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.FindPlaceOrderPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;

@Service("findPlaceOrderPageByWhereService")
public class FindPlaceOrderPageByWhereServiceImpl  extends EntityServiceImpl implements FindPlaceOrderPageByWhereService{
	@Resource
    private FindPageByWhereDAO findPlaceOrderPageByWhereDAO;

    @Override
    @Transactional
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception{
        pageInfo = findPlaceOrderPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                TeachMaterialPlaceOrder teachMaterialPlaceOrder = new TeachMaterialPlaceOrder();
                teachMaterialPlaceOrder.setOperateTime((Date) objs[9]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(teachMaterialPlaceOrder, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("id", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", objs[2]);
                jsonObject.put("orderCode", objs[3]);
                jsonObject.put("specName", null == objs[4] ? "" : SpecEnum.getDescn(objs[4].toString()));
                jsonObject.put("levelName", null == objs[5] ? "" : LevelEnum.getDescn(objs[5].toString()));
                jsonObject.put("enYear", objs[6]);
                jsonObject.put("enQuarter", objs[7]);
                jsonObject.put("operator", objs[8]);
                jsonObject.put("state", objs[10]);
                jsonObject.put("address", objs[11]);
                jsonObject.put("adminName", objs[12]);
                jsonObject.put("phone", objs[13]);
                jsonObject.put("tel", objs[14]);
                jsonObject.put("postalCode", objs[15]);
                jsonObject.put("tmCount", objs[16]);
                jsonObject.put("totalPrice", objs[17]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
