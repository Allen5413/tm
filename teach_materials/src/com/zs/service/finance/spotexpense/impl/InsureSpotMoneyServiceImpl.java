package com.zs.service.finance.spotexpense.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.spotexpense.FindSpotRecordBySpotCodeDao;
import com.zs.dao.finance.spotexpense.SpotExpenseDao;
import com.zs.dao.finance.spotinsurelog.SpotInsureLogDao;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.finance.SpotExpense;
import com.zs.domain.finance.SpotInsureLog;
import com.zs.service.finance.spotexpense.InsureSpotMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 确认金额的实现类
 * Created by LihongZhang on 2015/5/17.
 */
@Service("insureSpotMoneyService")
public class InsureSpotMoneyServiceImpl extends EntityServiceImpl<SpotExpense,SpotExpenseDao> implements InsureSpotMoneyService {

    @Resource
    private SpotInsureLogDao spotInsureLogDao;

    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;

    @Resource
    private FindSpotRecordBySpotCodeDao findSpotRecordBySpotCodeDao;

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public void insureMoney(Float insureMoney, String spotCode, String userName) throws Exception {
//        //验证确认金额
//        if (0 > insureMoney){
//            throw new BusinessException("确认金额不能为负");
//        }
//        //确认学习中心是否存在
//        if (null == findSpotByCodeDAO.getSpotByCode(spotCode)){
//            throw new BusinessException("学习中心不存在");
//        }
//        //验证学习中心费用表中是否存在该学习中心的记录
//        SpotExpense spotExpense = findSpotRecordBySpotCodeDao.getSpotEBySpotCode(spotCode);
//        if (null == spotExpense){
//            throw new BusinessException("不存在该学习中心的记录");
//        }else {
//            //存在则确认金额
//            spotExpense.setPay(spotExpense.getPay()+insureMoney);
//            spotExpense.setConfirmedMoney(spotExpense.getConfirmedMoney()-insureMoney);
//            //创建人和创建时间不能变
//            spotExpense.setCreator(spotExpense.getCreator());
//            spotExpense.setCreateTime(spotExpense.getCreateTime());
//            //记录操作人和操作时间
//            spotExpense.setOperator(userName);
//            //修改
//            findSpotRecordBySpotCodeDao.update(spotExpense);
//            //写入日志
//            SpotInsureLog spotInsureLog = new SpotInsureLog();
//            spotInsureLog.setSpotCode(spotCode);
//            spotInsureLog.setInsureMoney(insureMoney);
//            spotInsureLog.setCreator(userName);
//            //保存日志
//            spotInsureLogDao.save(spotInsureLog);
//        }
    }
}
