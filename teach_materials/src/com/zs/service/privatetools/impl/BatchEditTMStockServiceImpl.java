package com.zs.service.privatetools.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterial.FindTeachMaterialPageByWhereService;
import com.zs.service.privatetools.BatchEditTMStockService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/9.
 */
@Service("batchEditTMStockService")
public class BatchEditTMStockServiceImpl extends EntityServiceImpl implements BatchEditTMStockService {

    @Resource
    private FindListByWhereDAO findBatchTMStockDAO;
    @Resource
    private FindTeachMaterialPageByWhereService findTeachMaterialPageByWhereService;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;

    @Override
    @Transactional
    public List updateStock() throws Exception {
        List<Object[]> resultList = findBatchTMStockDAO.findListByWhere(null, null);
        List<JSONObject> errorList = new ArrayList<JSONObject>();
        if(null != resultList && 0 < resultList.size()){
            for(Object[] objs : resultList){
                String tmName = objs[0].toString();
                String author = objs[1].toString();
                String isbn = objs[2].toString();
                Float price = Float.parseFloat(objs[3].toString());
                Long issureChannelId = Long.parseLong(objs[4].toString());
                int count = Integer.parseInt(objs[5].toString());

                //查询教材是否存在
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", tmName);
                params.put("isExactName", "1");
                params.put("author", author);
                params.put("price", price+"");
                params.put("state", "0");
                PageInfo pageInfo = new PageInfo();
                pageInfo = findTeachMaterialPageByWhereService.findPageByWhere(pageInfo, params, null);
                if(null == pageInfo || null == pageInfo.getPageResults() || 1 > pageInfo.getPageResults().size()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tmName", tmName);
                    jsonObject.put("author", author);
                    jsonObject.put("isbn", isbn);
                    jsonObject.put("price", price);
                    jsonObject.put("count", count);
                    jsonObject.put("detail", "没找到");
                    errorList.add(jsonObject);
                }else{
                    if(1 < pageInfo.getPageResults().size()){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("tmName", tmName);
                        jsonObject.put("author", author);
                        jsonObject.put("isbn", isbn);
                        jsonObject.put("price", price);
                        jsonObject.put("count", count);
                        jsonObject.put("detail", "找到"+pageInfo.getPageResults().size()+"个相同的");
                        errorList.add(jsonObject);
                    }else{
                        JSONObject tmJSON = (JSONObject) pageInfo.getPageResults().get(0);
                        //查询教材渠道库存
                        TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(Long.parseLong(tmJSON.get("id").toString()), issureChannelId);
                        if(null == teachMaterialStock){
                            teachMaterialStock = new TeachMaterialStock();
                            teachMaterialStock.setIssueChannelId(issureChannelId);
                            teachMaterialStock.setTeachMaterialId(Long.parseLong(tmJSON.get("id").toString()));
                            teachMaterialStock.setStock(Long.parseLong(count+""));
                            teachMaterialStock.setOperator("admin");
                            findTeachMaterialStockBytmIdAndChannelIdDAO.save(teachMaterialStock);
                        }else{
                            teachMaterialStock.setStock(teachMaterialStock.getStock()+count);
                            findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
                        }
                    }
                }
            }
        }
        return errorList;
    }
}
