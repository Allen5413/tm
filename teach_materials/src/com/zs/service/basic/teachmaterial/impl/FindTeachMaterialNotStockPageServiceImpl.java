package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.placeorder.FindPlaceOrderByTMIdForStateDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByTMIdForStateDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.basic.teachmaterial.FindTeachMaterialNotStockPageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/21.
 */
@Service("findTeachmaterialNotStockPageService")
public class FindTeachMaterialNotStockPageServiceImpl extends EntityServiceImpl implements FindTeachMaterialNotStockPageService {
    @Resource
    private FindPageByWhereDAO findTeachmaterialNotStockPageDAO;
    @Resource
    private FindStudentBookOrderByTMIdForStateDAO findStudentBookOrderByTMIdForStateDAO;
    @Resource
    private FindPlaceOrderByTMIdForStateDAO findPlaceOrderByTMIdForStateDAO;

    @Override
    @Transactional
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findTeachmaterialNotStockPageDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //查询该教材还有没有没打印的学生订单
                List<StudentBookOrder> studentBookOrderList = findStudentBookOrderByTMIdForStateDAO.findStudentBookOrderByTMIdForState(StudentBookOrder.STATE_CONFIRMED, StudentBookOrder.STATE_CONFIRMED, Long.parseLong(objs[0].toString()));
                //查询该教材还有没有没打印的预订单
                List<TeachMaterialPlaceOrder> teachMaterialPlaceOrderList = findPlaceOrderByTMIdForStateDAO.findPlaceOrderByTMIdForStateDAO(Long.parseLong(objs[0].toString()), TeachMaterialPlaceOrder.STATE_CONFIRMED, TeachMaterialPlaceOrder.STATE_CONFIRMED);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", objs[0]);
                jsonObject.put("name", objs[1]);
                jsonObject.put("isbn", objs[2]);
                jsonObject.put("author", objs[3]);
                jsonObject.put("price", objs[4]);
                jsonObject.put("icId", objs[5]);
                jsonObject.put("icName", objs[6]);
                jsonObject.put("orderCount", objs[7]);
                jsonObject.put("pName", objs[8]);
                jsonObject.put("stock", objs[9]);
                jsonObject.put("stockD", objs[10]);
                if(null != studentBookOrderList && 0 < studentBookOrderList.size() && null != teachMaterialPlaceOrderList && 0 < teachMaterialPlaceOrderList.size()){
                    jsonObject.put("isExistsOrder", true);
                }else{
                    jsonObject.put("isExistsOrder", false);
                }
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
