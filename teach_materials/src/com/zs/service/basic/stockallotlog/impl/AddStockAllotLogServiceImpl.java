package com.zs.service.basic.stockallotlog.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.stockallotlog.StockAllotLogDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.basic.teachmaterialstock.TeachMaterialStockDAO;
import com.zs.domain.basic.StockAllotLog;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.stockallotlog.AddStockAllotLogService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/29.
 */
@Service("addStockAllotLogService")
public class AddStockAllotLogServiceImpl extends EntityServiceImpl<StockAllotLog, StockAllotLogDAO>
    implements AddStockAllotLogService{

    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private TeachMaterialStockDAO teachMaterialStockDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addStockAllotLog(StockAllotLog stockAllotLog, String loginName) throws Exception {
        if(null != stockAllotLog) {
            if(null == stockAllotLog){
                throw new BusinessException("没有提交调拨信息");
            }
            if(stockAllotLog.getOldIssueChannelId() == stockAllotLog.getNewIssueChannelId()){
                throw new BusinessException("发行渠道不能调拨给自己");
            }
            if(null == stockAllotLog.getOldIssueChannelId() || 0 >= stockAllotLog.getOldIssueChannelId()){
                throw new BusinessException("没有传入被调拨的发行渠道");
            }
            if(null == stockAllotLog.getTeachMaterialId() || 0 >= stockAllotLog.getTeachMaterialId()){
                throw new BusinessException("没有传入教材id");
            }
            //查询以前渠道库存
            TeachMaterialStock oldTeachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(stockAllotLog.getTeachMaterialId(), stockAllotLog.getOldIssueChannelId());
            if(null == oldTeachMaterialStock){
                throw new BusinessException("没有找到该渠道的库存信息");
            }
            if(oldTeachMaterialStock.getStock() < stockAllotLog.getStock()){
                throw new BusinessException("库存不够");
            }
            //减掉以前渠道的库存
            oldTeachMaterialStock.setStock(oldTeachMaterialStock.getStock() - stockAllotLog.getStock());
            oldTeachMaterialStock.setOperator(loginName);
            oldTeachMaterialStock.setOperateTime(DateTools.getLongNowTime());
            teachMaterialStockDAO.update(oldTeachMaterialStock);
            //增加调拨后渠道的库存
            TeachMaterialStock newTeachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(stockAllotLog.getTeachMaterialId(), stockAllotLog.getNewIssueChannelId());
            if(null == newTeachMaterialStock){
                newTeachMaterialStock = new TeachMaterialStock();
                newTeachMaterialStock.setTeachMaterialId(stockAllotLog.getTeachMaterialId());
                newTeachMaterialStock.setIssueChannelId(stockAllotLog.getNewIssueChannelId());
                newTeachMaterialStock.setStock(stockAllotLog.getStock());
                newTeachMaterialStock.setOperator(loginName);
                teachMaterialStockDAO.save(newTeachMaterialStock);
            }else{
                newTeachMaterialStock.setStock(newTeachMaterialStock.getStock() + stockAllotLog.getStock());
                newTeachMaterialStock.setOperator(loginName);
                newTeachMaterialStock.setOperateTime(DateTools.getLongNowTime());
                teachMaterialStockDAO.update(newTeachMaterialStock);
            }
            //记录调拨日志
            stockAllotLog.setOperator(loginName);
            super.save(stockAllotLog);
        }
    }
}
