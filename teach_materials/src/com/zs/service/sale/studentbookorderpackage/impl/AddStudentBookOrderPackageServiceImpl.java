package com.zs.service.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByCodeDAO;
import com.zs.dao.sale.studentbookorderpackage.FindStudentBookOrderPackageForMaxCodeDAO;
import com.zs.dao.sale.studentbookorderpackage.FindStudentBookOrderPackageForMaxSortNotSendDAO;
import com.zs.dao.sale.studentbookorderpackage.StudentBookOrderPackageDAO;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.sale.onceorder.EditOnceOrderForStateService;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStateService;
import com.zs.service.sale.studentbookorderpackage.AddStudentBookOrderPackageService;
import com.zs.service.sale.studentbookorderpackage.SendStudentBookOrderPackageService;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Allen on 2015/7/23.
 */
@Service("addStudentBookOrderPackageService")
public class AddStudentBookOrderPackageServiceImpl extends EntityServiceImpl<StudentBookOrderPackage, StudentBookOrderPackageDAO>
        implements AddStudentBookOrderPackageService {

    @Resource
    private FindStudentBookOrderPackageForMaxCodeDAO findStudentBookOrderPackageForMaxCodeDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private EditStudentBookOrderForStateService editStudentBookOrderForStateService;
    @Resource
    private EditOnceOrderForStateService editOnceOrderForStateService;
    @Resource
    private FindStudentBookOrderPackageForMaxSortNotSendDAO findStudentBookOrderPackageForMaxSortNotSendDAO;
    @Resource
    private FindStudentBookOrderByCodeDAO findStudentBookOrderByCodeDAO;
    @Resource
    private FindOnceOrderByCodeDAO findOnceOrderByCodeDAO;
    @Resource
    private SendStudentBookOrderPackageService sendStudentBookOrderPackageService;
    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;

    @Override
    @Transactional
    public StudentBookOrderPackage addStudentBookOrderPackage(String spotCode, String orderCodes, String logisticCode, int isOnce, String loginName) throws Exception {
        if(StringUtils.isEmpty(spotCode)){
            throw new BusinessException("没有传入学习中心编号");
        }
        if(StringUtils.isEmpty(orderCodes)){
            throw new BusinessException("没有传入订单号");
        }

        //得到当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();

        //查询当天该学习中心最大的编号
        int number = 0;
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = (calendar.get(Calendar.MONTH)+1) < 10 ? "0"+(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)+"";
        String day = calendar.get(Calendar.DATE) < 10 ? "0"+calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE)+"";
        StudentBookOrderPackage maxCodeStudentBookOrderPackage = findStudentBookOrderPackageForMaxCodeDAO.findStudentBookOrderPackageForMaxCode(year+month+day+spotCode+"%");
        if(null != maxCodeStudentBookOrderPackage){
            String maxCode = maxCodeStudentBookOrderPackage.getCode();
            number = Integer.parseInt(maxCode.substring(maxCode.length()-4, maxCode.length()));
        }
        //生成打包编号
        String code = OrderCodeTools.createStudentOrderPacageCode(spotCode, number+1);

        //查询该中心还未邮寄，已打包的最大顺序号
        int sort = 0;
        StudentBookOrderPackage maxSortStudentBookOrderPackage = findStudentBookOrderPackageForMaxSortNotSendDAO.
                findStudentBookOrderPackageForMaxSortNotSend(spotCode, semester.getId(), isOnce);
        if(null != maxSortStudentBookOrderPackage){
            sort = maxSortStudentBookOrderPackage.getSort();
        }

        StudentBookOrderPackage studentBookOrderPackage = new StudentBookOrderPackage();
        studentBookOrderPackage.setSemesterId(semester.getId());
        studentBookOrderPackage.setCode(code);
        studentBookOrderPackage.setSort(sort + 1);
        studentBookOrderPackage.setSpotCode(spotCode);
        studentBookOrderPackage.setIsSign(StudentBookOrderPackage.IS_SIGN_NOT);
        studentBookOrderPackage.setIsOnce(isOnce);
        studentBookOrderPackage.setCreator(loginName);
        studentBookOrderPackage.setOperator(loginName);
        super.save(studentBookOrderPackage);

        //添加学生订单与打包关联
        if(isOnce == StudentBookOrderPackage.IS_ONCE_NOT){
            this.addOrderAndPacage(studentBookOrderPackage, orderCodes, loginName);
        }
        if(isOnce == StudentBookOrderPackage.IS_ONCE_YES){
            this.addOnceOrderAndPacage(studentBookOrderPackage, orderCodes, loginName);
        }
        //如果录入了物流单号，就走发货流程
        if(!StringUtils.isEmpty(logisticCode)){
            Long[] ids = {studentBookOrderPackage.getId()};
            String[] logisticCodes = {logisticCode};
            sendStudentBookOrderPackageService.sendStudentBookOrderPackage(ids, logisticCodes, loginName);
        }
        return studentBookOrderPackage;
    }

    /**
     * 添加学生订单与打包关联
     * @throws Exception
     */
    protected void addOrderAndPacage(StudentBookOrderPackage studentBookOrderPackage, String orderCodes, String loginName)throws Exception{
        String[] orderCodeArray = orderCodes.split(",");
        Timestamp operateTime = DateTools.getLongNowTime();
        String address = "", phone = "", zipCode = "";
        for(String orderCode : orderCodeArray){
            StudentBookOrder studentBookOrder = findStudentBookOrderByCodeDAO.findStudentBookOrderByCode(orderCode);
            if(null == studentBookOrder){
                throw new BusinessException("订单号："+orderCode+", 没有找到");
            }
            if(null != studentBookOrder.getPackageId() && 0 < studentBookOrder.getPackageId()){
                throw new BusinessException("学号："+studentBookOrder.getStudentCode()+", 订单号："+orderCode+", 已经打包");
            }
            if(studentBookOrder.getState() != StudentBookOrder.STATE_SORTING){
                throw new BusinessException("学号："+studentBookOrder.getStudentCode()+", 订单号："+orderCode+", 状态不是分拣中");
            }
            //修改订单状态为已打包，记录状态变更日志
            editStudentBookOrderForStateService.editStudentBookOrderForState(orderCode, studentBookOrderPackage.getId(), StudentBookOrder.STATE_PACK, operateTime, loginName);
            if(StringUtils.isEmpty(address)) {
                //记录快递地址
                if (studentBookOrder.getIsSendStudent() == StudentBookOrder.IS_SEND_STUDENT_NOT) {
                    Spot spot = findSpotByCodeDAO.getSpotByCode(studentBookOrderPackage.getSpotCode());
                    address = spot.getAddress();
                    phone = spot.getPhone();
                    zipCode = spot.getPostalCode();
                }
                if (studentBookOrder.getIsSendStudent() == StudentBookOrder.IS_SEND_STUDENT_YES) {
                    Student student = findStudentByCodeDAO.getStudentByCode(studentBookOrder.getStudentCode());
                    address = student.getSendAddress();
                    phone = student.getSendPhone();
                    zipCode = student.getSendZipCode();
                }
            }
        }
        if(!StringUtils.isEmpty(address)) {
            studentBookOrderPackage.setAddress(address);
            studentBookOrderPackage.setPhone(phone);
            studentBookOrderPackage.setZipCode(zipCode);
            super.update(studentBookOrderPackage);
        }
    }

    /**
     * 添加一次性订单与打包关联
     * @throws Exception
     */
    protected void addOnceOrderAndPacage(StudentBookOrderPackage studentBookOrderPackage, String orderCodes, String loginName)throws Exception{
        String[] orderCodeArray = orderCodes.split(",");
        Timestamp operateTime = DateTools.getLongNowTime();
        String address = "", phone = "", zipCode = "";
        for(String orderCode : orderCodeArray){
            StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeDAO.find(orderCode);
            if(null == studentBookOnceOrder){
                throw new BusinessException("订单号："+orderCode+", 没有找到");
            }
            if(null != studentBookOnceOrder.getPackageId() && 0 < studentBookOnceOrder.getPackageId()){
                throw new BusinessException("学号："+studentBookOnceOrder.getStudentCode()+", 订单号："+orderCode+", 已经打包");
            }
            if(studentBookOnceOrder.getState() != StudentBookOnceOrder.STATE_SORTING){
                throw new BusinessException("学号："+studentBookOnceOrder.getStudentCode()+", 订单号："+orderCode+", 状态不是分拣中");
            }
            //修改订单状态为已打包，记录状态变更日志
            editOnceOrderForStateService.editor(orderCode, studentBookOrderPackage.getId(), StudentBookOnceOrder.STATE_PACK, operateTime, loginName);
            if(StringUtils.isEmpty(address)) {
                //记录快递地址
                if (studentBookOnceOrder.getIsSendStudent() == StudentBookOnceOrder.IS_SEND_STUDENT_NOT) {
                    Spot spot = findSpotByCodeDAO.getSpotByCode(studentBookOrderPackage.getSpotCode());
                    address = spot.getAddress();
                    phone = spot.getPhone();
                    zipCode = spot.getPostalCode();
                }
                if (studentBookOnceOrder.getIsSendStudent() == StudentBookOnceOrder.IS_SEND_STUDENT_YES) {
                    Student student = findStudentByCodeDAO.getStudentByCode(studentBookOnceOrder.getStudentCode());
                    address = student.getSendAddress();
                    phone = student.getSendPhone();
                    zipCode = student.getSendZipCode();
                }
            }
        }
        if(!StringUtils.isEmpty(address)) {
            studentBookOrderPackage.setAddress(address);
            studentBookOrderPackage.setPhone(phone);
            studentBookOrderPackage.setZipCode(zipCode);
            super.update(studentBookOrderPackage);
        }
    }
}
