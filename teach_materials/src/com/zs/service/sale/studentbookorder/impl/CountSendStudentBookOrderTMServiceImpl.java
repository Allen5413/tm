package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.service.sale.studentbookorder.CountSendStudentBookOrderTMService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
@Service("countSendStudentBookOrderTMService")
public class CountSendStudentBookOrderTMServiceImpl extends EntityServiceImpl implements CountSendStudentBookOrderTMService {

    @Resource
    private FindPageByWhereDAO countSendStudentBookOrderTMDAO;

    @Override
    @Transactional
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = countSendStudentBookOrderTMDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tmId", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("isbn", objs[2]);
                jsonObject.put("author", objs[3]);
                jsonObject.put("price", objs[4]);
                jsonObject.put("count", objs[5]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
