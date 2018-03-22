package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.user.UserDAO;
import com.zs.dao.statis.FindIndexPageDAO;
import com.zs.domain.basic.User;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.statis.FindIndexPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/11/1.
 */
@Service("findIndexPageService")
public class FindIndexPageServiceImpl extends EntityServiceImpl<User, UserDAO> implements FindIndexPageService {

    @Resource
    private FindIndexPageDAO findIndexPageDAO;


    @Override
    public int findWaitAuditPayCount() {
        return findIndexPageDAO.findWaitAuditPayCount().intValue();
    }

    @Override
    public int findNotPrintSpotOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOrderCount(StudentBookOrder.STATE_CONFIRMED, semesterId, StudentBookOrder.IS_SEND_STUDENT_NOT).intValue();
    }

    @Override
    public int findNotPrintStudentOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOrderCount(StudentBookOrder.STATE_CONFIRMED, semesterId, StudentBookOrder.IS_SEND_STUDENT_YES).intValue();
    }

    @Override
    public int findNotPackageSpotOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOrderCount(StudentBookOrder.STATE_SORTING, semesterId, StudentBookOrder.IS_SEND_STUDENT_NOT).intValue();
    }

    @Override
    public int findNotPackageStudentOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOrderCount(StudentBookOrder.STATE_SORTING, semesterId, StudentBookOrder.IS_SEND_STUDENT_YES).intValue();
    }

    @Override
    public int findNotPrintSpotOnceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOnceOrderCount(StudentBookOnceOrder.STATE_CONFIRMED, semesterId, StudentBookOnceOrder.IS_SEND_STUDENT_NOT).intValue();
    }

    @Override
    public int findNotPrintStudentOnceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOnceOrderCount(StudentBookOnceOrder.STATE_CONFIRMED, semesterId, StudentBookOnceOrder.IS_SEND_STUDENT_YES).intValue();
    }

    @Override
    public int findNotPackageSpotOnceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOnceOrderCount(StudentBookOnceOrder.STATE_SORTING, semesterId, StudentBookOnceOrder.IS_SEND_STUDENT_NOT).intValue();
    }

    @Override
    public int findNotPackageStudentOnceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintOnceOrderCount(StudentBookOnceOrder.STATE_SORTING, semesterId, StudentBookOnceOrder.IS_SEND_STUDENT_YES).intValue();
    }

    @Override
    public int findNotPrintSpotPlaceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintPlaceOrderCount(semesterId).intValue();
    }

    @Override
    public int findNotPackageSpotPlaceOrderCount(long semesterId) {
        return findIndexPageDAO.findNotPrintPlaceOrderCount(TeachMaterialPlaceOrder.STATE_SORTING, semesterId).intValue();
    }
}
