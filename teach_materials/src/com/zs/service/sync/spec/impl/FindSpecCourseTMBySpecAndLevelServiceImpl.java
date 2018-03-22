package com.zs.service.sync.spec.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spec.FindSpecCourseTMBySpecAndLevelDAO;
import com.zs.dao.sync.spec.SpecCourseDAO;
import com.zs.domain.sync.SpecCourse;
import com.zs.service.sync.spec.FindSpecCourseTMBySpecAndLevelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/10/20.
 */
@Service("findSpecCourseTMBySpecAndLevelService")
public class FindSpecCourseTMBySpecAndLevelServiceImpl extends EntityServiceImpl<SpecCourse, SpecCourseDAO>
        implements FindSpecCourseTMBySpecAndLevelService{

    @Resource
    private FindSpecCourseTMBySpecAndLevelDAO findSpecCourseTMBySpecAndLevelDAO;

    @Override
    @Transactional
    public JSONObject find(String specCode, String levelCode) {
        JSONObject json = new JSONObject();
        List<Object[]> tmList = findSpecCourseTMBySpecAndLevelDAO.findTMBySpecLevel(specCode, levelCode);
        List<Object[]> stmList = findSpecCourseTMBySpecAndLevelDAO.findSTMBySpecLevel(specCode, levelCode);

        this.groupData(tmList, json);
        this.groupData(stmList, json);
        return json;
    }

    protected void groupData(List<Object[]> dataList, JSONObject json){
        if(null != dataList && 0 < dataList.size()){
            for(Object[] objs : dataList){
                String courseCode = objs[0].toString();
                String courseName = null == objs[1] ? "" : objs[1].toString();
                String key = courseCode+"_"+courseName;

                JSONArray jsonArray = this.isExists(json, key);

                String tmName = null == objs[2] ? "" : objs[2].toString();
                String isbn = null == objs[3] ? "" : objs[3].toString();
                String author = null == objs[4] ? "" : objs[4].toString();
                String pName = null == objs[5] ? "" : objs[5].toString();
                String price = null == objs[6] ? "" : objs[6].toString();

                if(!StringUtils.isEmpty(tmName)){
                    JSONObject tmJSON = new JSONObject();
                    tmJSON.put("tmName", tmName);
                    tmJSON.put("isbn", isbn);
                    tmJSON.put("author", author);
                    tmJSON.put("pName", pName);
                    tmJSON.put("price", price);
                    jsonArray.add(tmJSON);
                }
                json.put(key, jsonArray);
            }
        }
    }

    protected JSONArray isExists(JSONObject json, String key){
        if (null != json.get(key)) {
            return (JSONArray) json.get(key);
        }
        return new JSONArray();
    }
}
