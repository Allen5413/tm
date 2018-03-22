package com.zs.service.basic.courseteachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.CourseTeachMaterial;
import com.zs.service.basic.courseteachmaterial.FindPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/12/12 0012.
 */
@Service("findCourseTeachMaterialPageByWhereService")
public class FindPageByWhereServiceImpl extends EntityServiceImpl implements FindPageByWhereService {

    @Resource
    private FindPageByWhereDAO findCourseTeachMaterialPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findCourseTeachMaterialPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONObject courseJSON = new JSONObject();
            String beforeCourseCode = "";
            for(Object[] objs : list){
                String courseCode = objs[0].toString();
                String courseName = objs[1].toString();

                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                CourseTeachMaterial courseTeachMaterial = new CourseTeachMaterial();
                courseTeachMaterial.setOperateTime((Date) objs[7]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(courseTeachMaterial, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));

                jsonObject.put("tmName", objs[2]);
                jsonObject.put("author", objs[3]);
                jsonObject.put("price", objs[4]);
                jsonObject.put("pName", objs[5]);
                jsonObject.put("operator", objs[6]);

                JSONArray jsonArray = null;
                if(!beforeCourseCode.equals(courseCode)){
                    jsonArray = new JSONArray();
                    jsonArray.add(jsonObject);

                }else{
                    jsonArray = (JSONArray) courseJSON.get("["+courseCode+"]"+courseName);
                    jsonArray.add(jsonObject);
                }
                courseJSON.put("["+courseCode+"]"+courseName, jsonArray);
                beforeCourseCode = courseCode;
            }
            List<JSONObject> resultList = new ArrayList<JSONObject>();
            resultList.add(courseJSON);
            pageInfo.setPageResults(resultList);
        }
        return pageInfo;
    }
}
