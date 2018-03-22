package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderPageByWhereService;
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
 * Created by Allen on 2015/5/26.
 */
@Service("findStudentBookOrderPageByWhereService")
public class FindStudentBookOrderPageByWhereServiceImpl extends EntityServiceImpl implements FindStudentBookOrderPageByWhereService {

    @Resource
    private FindPageByWhereDAO findStudentBookOrderPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findStudentBookOrderPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StudentBookOrder studentBookOrder = new StudentBookOrder();
                studentBookOrder.setOperateTime((Date) objs[11]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(studentBookOrder, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("specName", SpecEnum.getDescn(objs[5].toString()));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[1].toString()));
                jsonObject.put("sort", objs[0]);
                jsonObject.put("id", objs[1]);
                jsonObject.put("orderCode", objs[2]);
                jsonObject.put("studentCode", objs[3]);
                jsonObject.put("name", objs[4]);
                jsonObject.put("specCode", objs[5]);
                jsonObject.put("levelCode", objs[6]);
                jsonObject.put("homeTel", objs[7]);
                jsonObject.put("tmCount", objs[8]);
                jsonObject.put("totalPrice", objs[9]);
                jsonObject.put("operator", objs[10]);
                jsonObject.put("state", objs[12]);
                jsonObject.put("packageId", objs[13]);
                jsonObject.put("printSort", objs[14]);
                jsonObject.put("studentSign", objs[15]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
