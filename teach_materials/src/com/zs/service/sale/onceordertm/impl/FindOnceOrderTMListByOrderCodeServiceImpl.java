package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.service.sale.onceordertm.FindOnceOrderTMListByOrderCodeService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/26.
 */
@Service("findOnceOrderTMListByOrderCodeService")
public class FindOnceOrderTMListByOrderCodeServiceImpl
        extends EntityServiceImpl implements FindOnceOrderTMListByOrderCodeService {

    @Resource
    private FindListByWhereDAO findOnceOrderListByOrderIdDAO;

    @Override
    @Transactional
    public JSONArray find(long orderId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", orderId+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sbotm.teach_material_id", true);
        sortMap.put("sbotm.is_buy", true);
        sortMap.put("sbotm.operate_time", true);
        List<Object[]> resultList = findOnceOrderListByOrderIdDAO.findListByWhere(params, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StudentBookOrderTM studentBookOrderTM = new StudentBookOrderTM();
                studentBookOrderTM.setOperateTime((Date) objs[7]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(studentBookOrderTM, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("id", objs[0]);
                jsonObject.put("courseCode", objs[1]);
                jsonObject.put("courseName", objs[2]);
                jsonObject.put("name", objs[3]);
                jsonObject.put("price", objs[4]);
                jsonObject.put("count", objs[5]);
                jsonObject.put("operator", objs[6]);
                jsonObject.put("packageId", objs[8]);
                jsonObject.put("isMust", objs[9]);
                jsonObject.put("isBuy", objs[10]);
                jsonObject.put("xf", objs[11]);
                jsonObject.put("isSelect", objs[12]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
