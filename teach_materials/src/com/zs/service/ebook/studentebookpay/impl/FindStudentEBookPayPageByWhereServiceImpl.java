package com.zs.service.ebook.studentebookpay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.service.ebook.studentebookpay.FindStudentEBookPayPageByWhereService;
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
 * Created by Allen on 2016/1/4.
 */
@Service("findStudentEBookPayPageByWhereService")
public class FindStudentEBookPayPageByWhereServiceImpl extends EntityServiceImpl implements FindStudentEBookPayPageByWhereService {

    @Resource
    private FindPageByWhereDAO findStudentEBookPayPageByWhereDAO;

    @Override
    @Transactional
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        JSONObject returnJSON = new JSONObject();
        pageInfo = findStudentEBookPayPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        BigDecimal totalPayPrice = new BigDecimal(0);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StudentEBookPay studentEBookPay = new StudentEBookPay();
                studentEBookPay.setArrivalTime(null == objs[6] ? null : (Date) objs[6]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(studentEBookPay, jsonConfig);
                jsonObject.put("arrivalTime", "null".equals(jsonObject.get("arrivalTime").toString()) ? "" : jsonObject.get("arrivalTime"));

                BigDecimal money = new BigDecimal(objs[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                int payType = Integer.parseInt(objs[7].toString());
                String payTypeStr = "未知";
                switch (payType){
                    case 0:
                        payTypeStr = "网银";
                        break;
                    case  1:
                        payTypeStr = "银行转账";
                        break;
                    case  2:
                        payTypeStr = "现金";
                        break;
                    case  3:
                        payTypeStr = "其它";
                        break;
                    case  4:
                        payTypeStr = "微信";
                        break;
                    default:
                        break;
                }

                jsonObject.put("id", objs[0]);
                jsonObject.put("code", objs[1]);
                jsonObject.put("name", objs[2]);
                jsonObject.put("spotCode", objs[3]);
                jsonObject.put("spotName", objs[4]);
                jsonObject.put("money", money);
                jsonObject.put("payType", payTypeStr);
                jsonObject.put("remark", objs[8]);
                jsonObject.put("spec", SpecEnum.getDescn(objs[9].toString()));
                jsonObject.put("level", LevelEnum.getDescn(objs[10].toString()));
                jsonArray.add(jsonObject);

                totalPayPrice = totalPayPrice.add(money).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            pageInfo.setPageResults(jsonArray);
        }
        returnJSON.put("pageInfo", pageInfo);
        returnJSON.put("totalPayPrice", totalPayPrice);
        return returnJSON;
    }
}
