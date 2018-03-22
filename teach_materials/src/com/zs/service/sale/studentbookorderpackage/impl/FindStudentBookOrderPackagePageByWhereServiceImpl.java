package com.zs.service.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.kuaidi.push.FindKuaidiPushByNuDAO;
import com.zs.dao.sale.studentbookorderpackage.StudentBookOrderPackageDAO;
import com.zs.domain.kuaidi.KuaidiPush;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.service.sale.studentbookorderpackage.FindStudentBookOrderPackagePageByWhereService;
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
 * Created by Allen on 2015/7/22.
 */
@Service("findStudentBookOrderPackagePageByWhereService")
public class FindStudentBookOrderPackagePageByWhereServiceImpl extends EntityServiceImpl<StudentBookOrderPackage, StudentBookOrderPackageDAO> implements FindStudentBookOrderPackagePageByWhereService {

    @Resource
    private FindPageByWhereDAO findStudentBookOrderPackagePageByWhereDAO;
    @Resource
    private FindKuaidiPushByNuDAO findKuaidiPushByNuDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findStudentBookOrderPackagePageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                String logisticCode = objs[7].toString();
                //得到快递信息
                JSONArray kuaidiJSON = null;
                if(null != objs[1]){
                    kuaidiJSON = JSONArray.fromObject(objs[1]);
                }
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StudentBookOrderPackage studentBookOrderPackage = new StudentBookOrderPackage();
                studentBookOrderPackage.setSendTime((Date) objs[9]);
                studentBookOrderPackage.setCreateTime((Date) objs[11]);
                studentBookOrderPackage.setOperateTime((Date) objs[13]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(studentBookOrderPackage, jsonConfig);
                jsonObject.put("sendTime", "null".equals(jsonObject.get("sendTime").toString()) ? "" : jsonObject.get("sendTime"));
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("spotName", objs[0]);
                if(null != kuaidiJSON && 0 < kuaidiJSON.size()) {
                    jsonObject.put("kuaidiJSON", kuaidiJSON.get(0));
                }else{
                    if(!StringUtils.isEmpty(logisticCode)) {
                        String[] nus = logisticCode.split(",");
                        if(null != nus && 0 < nus.length) {
                            KuaidiPush kuaidiPush = findKuaidiPushByNuDAO.find(nus[0]);
                            if(null != kuaidiPush){
                                kuaidiJSON = JSONArray.fromObject(kuaidiPush.getData());
                                if(null != kuaidiJSON && 0 < kuaidiJSON.size()){
                                    jsonObject.put("kuaidiJSON", kuaidiJSON.get(0));
                                }
                            }
                        }
                    }
                }
                jsonObject.put("id", objs[2]);
                jsonObject.put("semesterId", objs[3]);
                jsonObject.put("code", objs[4]);
                jsonObject.put("spotCode", objs[5]);
                jsonObject.put("sort", objs[6]);
                jsonObject.put("logisticCode", logisticCode);
                jsonObject.put("isSign", objs[8]);
                jsonObject.put("creator", objs[10]);
                jsonObject.put("operator", objs[12]);
                jsonObject.put("version", objs[14]);
                jsonObject.put("orderCount", objs[19]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
