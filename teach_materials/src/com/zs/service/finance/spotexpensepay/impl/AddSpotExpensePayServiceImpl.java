package com.zs.service.finance.spotexpensepay.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.UserTypeEnum;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.finance.spotexpense.FindSpotRecordBySpotCodeDao;
import com.zs.dao.finance.spotexpensepay.SpotExpensePayDao;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.SpotExpense;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.service.finance.spotexpensepay.AddSpotExpensePayService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by LihongZhang on 2015/5/17.
 */
@Service("addSpotExpensePayService")
public class AddSpotExpensePayServiceImpl extends EntityServiceImpl<SpotExpensePay,SpotExpensePayDao> implements AddSpotExpensePayService {

    @Resource
    private SpotExpensePayDao spotExpensePayDao;
    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;
    @Resource
    private FindSpotRecordBySpotCodeDao findSpotRecordBySpotCodeDao;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addSpotEP(SpotExpensePay spotExpensePay, String userName) throws Exception {
        //业务逻辑
        if (null != spotExpensePay){
            Date operateTime = DateTools.getLongNowTime();
            //得到当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            //不能为负数
            if(0 >= spotExpensePay.getMoney()){
                throw new BusinessException("请输入正确的缴费金额");
            }
            //验证学习中心编号是否存在
            if (null == findSpotByCodeDAO.getSpotByCode(spotExpensePay.getSpotCode())){
                throw new BusinessException("学习中心不存在");
            }
            //增加创建人
            spotExpensePay.setCreator(userName);
            //新增入账记录
            spotExpensePayDao.save(spotExpensePay);

            List<SpotExpense> spotExpenseList = findSpotRecordBySpotCodeDao.getSpotEBySpotCode(spotExpensePay.getSpotCode());
            if (null == spotExpenseList || 0 >= spotExpenseList.size()){
                //新增学习中心费用记录
                SpotExpense spotExpense = new SpotExpense();
                spotExpense.setSpotCode(spotExpensePay.getSpotCode());
                spotExpense.setBuy(0d);
                spotExpense.setPay(new BigDecimal(spotExpensePay.getMoney()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                spotExpense.setCreator(userName);
                spotExpense.setOperator(userName);
                spotExpense.setSemesterId(semester.getId());
                spotExpense.setState(SpotExpense.STATE_YES);
                spotExpense.setDiscount(100);
                spotExpense.setClearTime(operateTime);
                //添加
                findSpotRecordBySpotCodeDao.save(spotExpense);
            }else {
                //当前缴费剩的钱
                double tempMoney = spotExpensePay.getMoney();
                //每学期多的钱
                double moreMoney = 0;
                //是否当前学期
                boolean isNowSemester = false;
                for(SpotExpense spotExpense : spotExpenseList) {
                    double pay = null == spotExpense.getPay() ? 0 : spotExpense.getPay();
                    double buy = null == spotExpense.getBuy() ? 0 : spotExpense.getBuy();
                    int discount = null == spotExpense.getDiscount() ? 100 : spotExpense.getDiscount(); //折扣
                    float discountPoint = new BigDecimal(discount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); //折扣百分比
                    float actualBuy = new BigDecimal(buy).multiply(new BigDecimal(discountPoint)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();    //实际应该交的钱
                    //是否当前学期
                    if (semester.getId() == spotExpense.getSemesterId()) {
                        isNowSemester = true;
                        spotExpense.setPay(new BigDecimal(tempMoney).add(new BigDecimal(moreMoney)).add(new BigDecimal(pay)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        if(spotExpense.getPay() >= actualBuy){
                            spotExpense.setState(SpotExpense.STATE_YES);
                            spotExpense.setClearTime(operateTime);
                        }else{
                            spotExpense.setState(SpotExpense.STATE_NO);
                            spotExpense.setClearTime(null);
                        }
                        spotExpense.setOperator(userName);
                        spotExpense.setOperateTime(operateTime);
                        findSpotRecordBySpotCodeDao.update(spotExpense);
                    } else {
                        double temp = new BigDecimal(actualBuy).subtract(new BigDecimal(pay)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        if (actualBuy > pay) {
                            if (tempMoney >= temp) {
                                spotExpense.setPay(new BigDecimal(spotExpense.getPay()).add(new BigDecimal(temp)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                                tempMoney = new BigDecimal(tempMoney).subtract(new BigDecimal(temp)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                spotExpense.setState(SpotExpense.STATE_YES);
                                spotExpense.setClearTime(operateTime);
                            } else {
                                spotExpense.setPay(new BigDecimal(spotExpense.getPay()).add(new BigDecimal(tempMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                                tempMoney = 0;
                                spotExpense.setState(SpotExpense.STATE_NO);
                                spotExpense.setClearTime(null);
                            }
                        }
                        if (pay > actualBuy) {
                            moreMoney = new BigDecimal(moreMoney).subtract(new BigDecimal(temp)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            spotExpense.setPay(new BigDecimal(spotExpense.getPay()).add(new BigDecimal(temp)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            spotExpense.setState(SpotExpense.STATE_YES);
                            spotExpense.setClearTime(operateTime);
                        }
                        spotExpense.setOperator(userName);
                        spotExpense.setOperateTime(operateTime);
                        findSpotRecordBySpotCodeDao.update(spotExpense);
                    }
                }
                if(!isNowSemester) {
                    //新增学习中心费用记录
                    SpotExpense spotExpense = new SpotExpense();
                    spotExpense.setSpotCode(spotExpensePay.getSpotCode());
                    spotExpense.setBuy(0d);
                    spotExpense.setPay(new BigDecimal(tempMoney).add(new BigDecimal(moreMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    spotExpense.setCreator(userName);
                    spotExpense.setOperator(userName);
                    spotExpense.setClearTime(operateTime);
                    spotExpense.setSemesterId(semester.getId());
                    spotExpense.setDiscount(100);
                    //添加
                    findSpotRecordBySpotCodeDao.save(spotExpense);
                }
            }
        }else {
            throw new BusinessException("将添加的数据为空，不能执行添加");
        }
    }
}
