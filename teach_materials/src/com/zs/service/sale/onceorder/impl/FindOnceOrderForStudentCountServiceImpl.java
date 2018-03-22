package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderForStudentCountDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.FindOnceOrderForStudentCountService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
@Service("findOnceOrderForStudentCountService")
public class FindOnceOrderForStudentCountServiceImpl extends EntityServiceImpl implements FindOnceOrderForStudentCountService {

    @Resource
    private FindOnceOrderForStudentCountDAO findOnceOrderForStudentCountDAO;

    @Override
    public List<JSONObject> find(long semesterId, int state, int countNum) throws Exception {
        List<JSONObject> list = new ArrayList<JSONObject>();
        if(state == StudentBookOnceOrder.STATE_DOING) {
            BigInteger orderCount = findOnceOrderForStudentCountDAO.findOrderCount(semesterId, state);
            int pageCountNum = orderCount.intValue() % countNum > 0 ? (orderCount.intValue() / countNum) + 1 : orderCount.intValue() / countNum;
            for (int i = 0; i < pageCountNum; i++) {
                int pageNum = i * countNum;
                Object[] objs = findOnceOrderForStudentCountDAO.find(semesterId, state, pageNum, countNum);
                if (null != objs) {
                    JSONObject json = new JSONObject();
                    json.put("orderCount", objs[0].toString());
                    json.put("tmCount", objs[1].toString());
                    json.put("priceTotal", objs[2].toString());
                    list.add(json);
                }
            }
        }else{
            //查询已打印的，需要按打印时间统计
            List<Object[]> resultList = findOnceOrderForStudentCountDAO.findPrint(semesterId, state);
            if(null != resultList && 0 < resultList.size()) {
                for(Object[] objs : resultList) {
                    JSONObject jsonObject = new JSONObject();
                    //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                    StudentBookOnceOrder studentBookOnceOrder = new StudentBookOnceOrder();
                    studentBookOnceOrder.setOperateTime((Date) objs[3]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    jsonObject = JSONObject.fromObject(studentBookOnceOrder, jsonConfig);
                    jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                    jsonObject.put("operator", objs[4]);

                    jsonObject.put("orderCount", objs[0]);
                    jsonObject.put("tmCount", objs[1]);
                    jsonObject.put("priceTotal", StringTools.getFinancePrice(objs[2].toString()));
                    list.add(jsonObject);
                }
            }
        }
        return list;
    }
}
