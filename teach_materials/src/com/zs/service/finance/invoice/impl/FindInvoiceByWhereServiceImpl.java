package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.FindInvoiceByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Service("findInvoiceByWhereService")
public class FindInvoiceByWhereServiceImpl extends EntityServiceImpl implements FindInvoiceByWhereService {

    @Resource
    private FindPageByWhereDAO findInvoiceByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map) throws Exception {
        JSONObject returnJSON = new JSONObject();
        BigDecimal totalMoney = new BigDecimal(0);
        pageInfo = findInvoiceByWhereDAO.findPageByWhere(pageInfo, map, null);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                BigDecimal money = new BigDecimal(objs[7]+"");
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                Invoice invoice = new Invoice();
                invoice.setOpenTime((Date) objs[9]);
                invoice.setOperateTime((Date) objs[11]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(invoice, jsonConfig);
                jsonObject.put("openTime", "null".equals(jsonObject.get("openTime").toString()) ? "" : jsonObject.get("openTime"));
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));

                jsonObject.put("id", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("studentCode", objs[2]);
                jsonObject.put("name", objs[3]);
                jsonObject.put("specName", SpecEnum.getDescn(objs[4]+""));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[5]+""));
                jsonObject.put("iCode", objs[6]);
                jsonObject.put("money", money);
                jsonObject.put("state", objs[8]);
                jsonObject.put("operator", objs[10]);
                jsonArray.add(jsonObject);

                totalMoney = totalMoney.add(money).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            pageInfo.setPageResults(jsonArray);
        }
        returnJSON.put("pageInfo", pageInfo);
        returnJSON.put("totalMoney", totalMoney);
        return returnJSON;
    }
}
