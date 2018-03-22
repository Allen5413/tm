package com.zs.service.ebook.spotreplacepaydetail.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.spotreplacepaydetail.SpotReplacePayDetailDAO;
import com.zs.dao.ebook.studentebookpay.FindStudentEBookPayByCodeDAO;
import com.zs.domain.ebook.SpotReplacePayDetail;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.service.ebook.spotreplacepaydetail.AddSpotReplacePayDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2018/1/3.
 */
@Service
public class AddSpotReplacePayDetailServiceImpl extends EntityServiceImpl<SpotReplacePayDetail, SpotReplacePayDetailDAO>
        implements AddSpotReplacePayDetailService {

    @Resource
    private FindStudentEBookPayByCodeDAO findStudentEBookPayByCodeDAO;

    @Override
    public void add(long srpId, String code, long money, String loginName) throws Exception {
        StudentEBookPay studentEBookPay = findStudentEBookPayByCodeDAO.find(code);
        if(null !=  studentEBookPay){
            throw new BusinessException("学号："+code+"，已经购买过电子教材");
        }

        SpotReplacePayDetail spotReplacePayDetail = new SpotReplacePayDetail();
        spotReplacePayDetail.setSrpId(srpId);
        spotReplacePayDetail.setStudentCode(code);
        spotReplacePayDetail.setMoney(money);
        spotReplacePayDetail.setCreator(loginName);
        super.save(spotReplacePayDetail);
    }
}
