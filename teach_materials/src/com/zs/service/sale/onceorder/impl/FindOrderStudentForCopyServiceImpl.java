package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.sale.onceorder.FindOrderStudentForCopyService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/6/28.
 */
@Service("findOrderStudentForCopyService")
public class FindOrderStudentForCopyServiceImpl extends EntityServiceImpl
        implements FindOrderStudentForCopyService {

    @Resource
    private FindListByWhereDAO findOrderStudentForCopyDAO;

    @Override
    @Transactional
    public List<JSONObject> find(Map<String, String> paramsMap) {
        List<JSONObject> returnList = new ArrayList<JSONObject>();
        List<Object[]> resultList = findOrderStudentForCopyDAO.findListByWhere(paramsMap, null);
        if(null != resultList && 0 < resultList.size()){
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", objs[0]);
                jsonObject.put("code", objs[1]);
                jsonObject.put("name", objs[2]);
                returnList.add(jsonObject);
            }
        }
        return returnList;
    }
}
