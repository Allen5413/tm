package com.zs.service.basic.setteachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.setteachmaterial.SetTeachMaterialDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.FindSetTeachMaterialByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/4/30.
 */
@Service("findSetTeachMaterialByWhereService")
public class FindSetTeachMaterialByWhereServiceImpl extends EntityServiceImpl
        implements FindSetTeachMaterialByWhereService {
    @Resource
    public FindPageByWhereDAO findSetTeachMaterialPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findSetTeachMaterialPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SetTeachMaterial setTeachMaterial = new SetTeachMaterial();
                setTeachMaterial.setOperateTime((Date) objs[6]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(setTeachMaterial, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("id", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("buyCourseCode", objs[2]);
                jsonObject.put("courseName", objs[3]);
                jsonObject.put("remark", objs[4]);
                jsonObject.put("operator", objs[5]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
