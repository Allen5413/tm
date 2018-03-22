package com.zs.service.ebook.spotreplacepay.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.spotreplacepay.SpotReplacePayDAO;
import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.AddSpotReplacePayService;
import com.zs.service.ebook.spotreplacepaydetail.AddSpotReplacePayDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Allen on 2018/1/3.
 */
@Service("addSpotReplacePayService")
public class AddSpotReplacePayServiceImpl extends EntityServiceImpl<SpotReplacePay, SpotReplacePayDAO>
    implements AddSpotReplacePayService{

    @Resource
    private AddSpotReplacePayDetailService addSpotReplacePayDetailService;

    @Override
    @Transactional
    public void add(String[] codes, float money, SpotReplacePay spotReplacePay) throws Exception {
        if(null == codes || 1 > codes.length){
            throw new BusinessException("请选择要交费的学生");
        }
        long moneyL = new BigDecimal(money*100).longValue();
        long sumMoney = new BigDecimal(moneyL).multiply(new BigDecimal(codes.length)).longValue();
        spotReplacePay.setState(SpotReplacePay.STATE_WAIT);
        spotReplacePay.setMoney(sumMoney);
        super.save(spotReplacePay);
        for(String code : codes){
            addSpotReplacePayDetailService.add(spotReplacePay.getId(), code, moneyL, spotReplacePay.getCreator());
        }
    }
}
