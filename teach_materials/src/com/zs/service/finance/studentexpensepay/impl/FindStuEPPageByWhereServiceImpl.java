package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.studentexpensepay.StudentExpensePayDao;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensepay.FindStuEPPageByWhereService;
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
 * 实现分页查询学生入账明细的接口
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEPPageByWhereService")
public class FindStuEPPageByWhereServiceImpl extends EntityServiceImpl<StudentExpensePay,StudentExpensePayDao> implements FindStuEPPageByWhereService {

    @Resource
    private FindPageByWhereDAO findStuEPPageByWhereDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONObject findPage(PageInfo pageInfo,Map<String,String> map,Map<String,Boolean> sortMap) throws Exception {
        JSONObject returnJSON = new JSONObject();
        pageInfo = findStuEPPageByWhereDao.findPageByWhere(pageInfo, map, sortMap);
        BigDecimal totalPayPrice = new BigDecimal(0);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StudentExpensePay studentExpensePay = new StudentExpensePay();
                studentExpensePay.setCreateTime((Date) objs[6]);
                studentExpensePay.setArrivalTime((Date) objs[7]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(studentExpensePay, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("arrivalTime", "null".equals(jsonObject.get("arrivalTime").toString()) ? "" : jsonObject.get("arrivalTime"));

                BigDecimal money = new BigDecimal(objs[4].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                int payType = Integer.parseInt(objs[5].toString());
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
                        payTypeStr = "红冲";
                        break;
                    case  5:
                        payTypeStr = "微信";
                        break;
                    default:
                        break;
                }
                jsonObject.put("code", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("spec", SpecEnum.getDescn(objs[2].toString()));
                jsonObject.put("level", LevelEnum.getDescn(objs[3].toString()));
                jsonObject.put("money", money);
                jsonObject.put("payType", payTypeStr);
                jsonObject.put("remark", objs[8]);
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
