package com.zs.service.finance.spotexpense.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.spotexpense.FindSpotRecordBySpotCodeDao;
import com.zs.dao.finance.spotexpense.SpotExpenseDao;
import com.zs.dao.finance.spotexpensepay.FindSumPayByCodeDAO;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.ResetSpotExpenseForPayBySpotCodeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 重新计算学习中心每学期的交费信息
 * Created by Allen on 2016/1/26.
 */
@Service("resetSpotExpenseForPayBySpotCodeService")
public class ResetSpotExpenseForPayBySpotCodeServiceImpl extends EntityServiceImpl<SpotExpense, SpotExpenseDao> implements ResetSpotExpenseForPayBySpotCodeService {

    @Resource
    private FindSpotRecordBySpotCodeDao findSpotRecordBySpotCodeDao;
    @Resource
    private FindSumPayByCodeDAO findSumPayByCodeDAO;

    @Override
    @Transactional
    public void reset(String spotCode, String userName) throws Exception {
        //查询总交费
        double totalPay = findSumPayByCodeDAO.find(spotCode);
        totalPay = totalPay*100;

        //查询每学期的财务记录
        List<SpotExpense> spotExpenseList = findSpotRecordBySpotCodeDao.getSpotEBySpotCode(spotCode);
        if(spotExpenseList != null && 0 < spotExpenseList.size()){
            for(int i=0; i<spotExpenseList.size(); i++){
                SpotExpense spotExpense = spotExpenseList.get(i);
                //消费
                double buy = null == spotExpense.getBuy() ? 0 : spotExpense.getBuy()*100;
                //折扣
                double discount = null == spotExpense.getDiscount() ? 100 : spotExpense.getDiscount();
                //实际消费
                double actualBuy = new BigDecimal(buy).multiply(new BigDecimal(discount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


                if(totalPay >= actualBuy){
                    //如果是最后一个学期，就把剩的钱全部录进去
                    if(i == spotExpenseList.size()-1){
                        spotExpense.setPay(new BigDecimal(totalPay).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }else {
                        spotExpense.setPay(new BigDecimal(actualBuy).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    if(null == spotExpense.getState() || spotExpense.getState() == SpotExpense.STATE_NO){
                        spotExpense.setState(SpotExpense.STATE_YES);
                    }
                    if(spotExpense.getClearTime() == null){
                        spotExpense.setClearTime(DateTools.getLongNowTime());
                    }
                    totalPay = new BigDecimal(totalPay).subtract(new BigDecimal(actualBuy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }else{
                    spotExpense.setPay(new BigDecimal(totalPay).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    spotExpense.setState(SpotExpense.STATE_NO);
                    spotExpense.setClearTime(null);
                    totalPay = 0;
                }
                super.update(spotExpense);
            }
        }
    }
}
