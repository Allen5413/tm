package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.statis.FindIssueChannelPayDAO;
import com.zs.service.statis.FindIssueChannelPayService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Allen on 2015/9/29.
 */
@Service("findIssueChannelPayService")
public class FindIssueChannelPayServiceImpl extends EntityServiceImpl implements FindIssueChannelPayService {

    @Resource
    private FindIssueChannelPayDAO findIssueChannelPayDAO;

    @Override
    public JSONArray findIssueChannelPay() throws Exception {
        JSONArray jsonArray = new JSONArray();
        List<Object[]> resultList = findIssueChannelPayDAO.findIssueChannelPay();
        if (null != resultList && 0 < resultList.size()) {
            for (Object[] objs : resultList) {
                String semester = objs[0] + "年" + objs[1];
                String name = objs[2].toString();
                double zk = null == objs[3] ? 0 : Double.valueOf(objs[3].toString());
                double wljc = null == objs[4] ? 0 : Double.valueOf(objs[4].toString());
                double zb = null == objs[5] ? 0 : Double.valueOf(objs[5].toString());
                double td = null == objs[6] ? 0 : Double.valueOf(objs[6].toString());

                double totalPrice = new BigDecimal(zk).add(new BigDecimal(wljc)).add(new BigDecimal(zb)).add(new BigDecimal(td)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double pay = new BigDecimal(td).multiply(new BigDecimal(0.815)).add(new BigDecimal(zb).multiply(new BigDecimal(0.07))).add(new BigDecimal(zk).multiply(new BigDecimal(1))).add(new BigDecimal(wljc).multiply(new BigDecimal(1))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("semester", semester);
                jsonObject.put("name", name);
                jsonObject.put("zk", StringTools.getFinancePrice(zk+""));
                jsonObject.put("wljc", StringTools.getFinancePrice(wljc+""));
                jsonObject.put("zb", StringTools.getFinancePrice(zb+""));
                jsonObject.put("td", StringTools.getFinancePrice(td+""));
                jsonObject.put("totalPrice", StringTools.getFinancePrice(totalPrice+""));
                jsonObject.put("pay", StringTools.getFinancePrice(pay+""));
                jsonObject.put("semesterId", Long.parseLong(objs[7].toString()));
                jsonObject.put("icId", Long.parseLong(objs[8].toString()));
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
