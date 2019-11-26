package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/4/29.
 */
@Service("findTeachMaterialPageByWhereService")
public class FindTeachMaterialPageByWhereServiceImpl extends EntityServiceImpl implements FindTeachMaterialPageByWhereService {
    @Resource
    public FindPageByWhereDAO findTeachMaterialPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        map.put("isSelectCourseCode", "1");
        return this.findPage(pageInfo, map, sortMap);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere2(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        map.put("isSelectCourseCode", "0");
        return this.findPage(pageInfo, map, sortMap);
    }

    private PageInfo findPage(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap){
        pageInfo = findTeachMaterialPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                TeachMaterial teachMaterial = new TeachMaterial();
                teachMaterial.setOperateTime((Date) objs[9]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(teachMaterial, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("id", objs[0]);
                jsonObject.put("isbn", objs[1]);
                jsonObject.put("name", objs[2]);
                jsonObject.put("author", objs[3]);
                jsonObject.put("revision", objs[4]);
                jsonObject.put("price", objs[5]);
                jsonObject.put("state", objs[6]);
                jsonObject.put("isSet", objs[7]);
                jsonObject.put("operator", objs[8]);
                jsonObject.put("isSpotSend", objs[10]);
                jsonObject.put("pressName", objs[11]);
                jsonObject.put("tmTypeName", objs[12]);

                String courseCode = map.get("courseCode");
                if(!StringUtils.isEmpty(courseCode)) {
                    jsonObject.put("courseCode", objs[13]);
                    jsonObject.put("courseName", null == objs[13] ? "" : CourseEnum.getDescn(objs[13].toString()));
                }
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
