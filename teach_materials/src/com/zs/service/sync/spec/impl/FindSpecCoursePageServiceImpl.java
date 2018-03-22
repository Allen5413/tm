package com.zs.service.sync.spec.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.service.sync.spec.FindSpecCoursePageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
@Service("findSpecCoursePageService")
public class FindSpecCoursePageServiceImpl extends EntityServiceImpl
        implements FindSpecCoursePageService {

    @Resource
    public FindPageByWhereDAO findSpecCoursePageDAO;

    @Override
    @Transactional
    public PageInfo findPageByWhere(PageInfo pageInfo) throws Exception {
        pageInfo = findSpecCoursePageDAO.findPageByWhere(pageInfo, null, null);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("specCode", objs[0].toString());
                jsonObject.put("specName", SpecEnum.getDescn(objs[0].toString()));
                jsonObject.put("levelCode", objs[1].toString());
                jsonObject.put("levelName", LevelEnum.getDescn(objs[1].toString()));
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}