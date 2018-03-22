package com.zs.service.ebook.spotreplacepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.FindSpotReplacePayPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
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
 * Created by Allen on 2016/1/4.
 */
@Service("findSpotReplacePayPageByWhereService")
public class FindSpotReplacePayPageByWhereServiceImpl extends EntityServiceImpl implements FindSpotReplacePayPageByWhereService {

    @Resource
    private FindPageByWhereDAO findSpotReplacePayPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findSpotReplacePayPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SpotReplacePay spotReplacePay = new SpotReplacePay();
                spotReplacePay.setCreateTime(null == objs[6] ? null :  (Date) objs[6]);
                spotReplacePay.setVerifyTime(null == objs[8] ? null :  (Date) objs[8]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotReplacePay, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("verifyTime", "null".equals(jsonObject.get("verifyTime").toString()) ? "" : jsonObject.get("verifyTime"));

                jsonObject.put("id", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", objs[2]);
                jsonObject.put("money", StringTools.getFinancePrice(new BigDecimal(objs[3]+"").divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
                jsonObject.put("payWay", objs[4]);
                jsonObject.put("creator", objs[5]);
                jsonObject.put("verifyer", objs[7]);
                jsonObject.put("state", objs[9]);
                jsonObject.put("remark", objs[10]);
                jsonObject.put("imgUrl", objs[11]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
