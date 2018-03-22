package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.*;
import com.zs.dao.basic.teachmaterialcourse.DelTeachMaterialCourseBytmIdDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderByStudentCodeDAO;
import com.zs.dao.sale.onceordertm.BatchOnceOrderTMDAO;
import com.zs.dao.sale.onceordertm.EditOnceOrderTMByStateAndTMIdAndSemesterIdForCountDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdAndTMIdDAO;
import com.zs.dao.sale.studentbookorder.BatchStudentBookOrderDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForNotSendByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;
import com.zs.dao.sale.studentbookorderlog.BatchStudentBookOrderLogDAO;
import com.zs.dao.sale.studentbookordertm.BatchStudentBookOrderTMDAO;
import com.zs.dao.sale.studentbookordertm.EditStudentBookOrderTMByStateAndTMIdAndSemesterIdForCountDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeAndTMIdDAO;
import com.zs.dao.sync.selectedcourse.FindStudentByCourseCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.sale.*;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.EditTeachMaterialService;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2015/4/29.
 */
@Service("editTeachMaterialService")
public class EditTeachMaterialServiceImpl extends EntityServiceImpl<TeachMaterial, TeachMaterialDAO> implements EditTeachMaterialService {

    @Resource
    private FindTeachMaterialByNameDAO findTeachMaterialByNameDAO;
    @Resource
    private FindTeachMaterialByIsbnDAO findTeachMaterialByIsbnDAO;
    @Resource
    private DelTeachMaterialCourseBytmIdDAO delTeachMaterialCourseBytmIdDAO;
    @Resource
    private EditStudentBookOrderTMByStateAndTMIdAndSemesterIdForCountDAO editStudentBookOrderTMByStateAndTMIdAndSemesterIdForCountDAO;
    @Resource
    private EditOnceOrderTMByStateAndTMIdAndSemesterIdForCountDAO editOnceOrderTMByStateAndTMIdAndSemesterIdForCountDAO;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindCourseByTMIdDAO findCourseByTMIdDAO;
    @Resource
    private FindStudentByCourseCodeDAO findStudentByCourseCodeDAO;
    @Resource
    private FindStudentBookOrderForMaxCodeDAO findStudentBookOrderForMaxCodeDAO;
    @Resource
    private FindStudentBookOnceOrderByStudentCodeDAO findStudentBookOnceOrderByStudentCodeDAO;
    @Resource
    private FindOnceOrderTMByOrderIdAndTMIdDAO findOnceOrderTMByOrderIdAndTMIdDAO;
    @Resource
    private FindStudentBookOrderForNotSendByStudentCodeDAO findStudentBookOrderForNotSendByStudentCodeDAO;
    @Resource
    private FindStudentBookOrderTMByOrderCodeAndTMIdDAO findStudentBookOrderTMByOrderCodeAndTMIdDAO;
    @Resource
    private BatchStudentBookOrderDAO batchStudentBookOrderDAO;
    @Resource
    private BatchStudentBookOrderTMDAO batchStudentBookOrderTMDAO;
    @Resource
    private BatchStudentBookOrderLogDAO batchStudentBookOrderLogDAO;
    @Resource
    private BatchOnceOrderTMDAO batchOnceOrderTMDAO;
    @Resource
    private FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editTeachMaterial(TeachMaterial teachMaterial, String loginName) throws Exception {
        //是否存在
        TeachMaterial isExistsTeachMaterial = super.get(teachMaterial.getId());
        if(null == isExistsTeachMaterial){
            throw new BusinessException("教材不存在！");
        }

        //修改前的状态
        int oldState = isExistsTeachMaterial.getState();
        //修改后的状态
        int newState = teachMaterial.getState();

        //验证教材名称是否已经存在
        List<TeachMaterial> teachMaterialList = findTeachMaterialByNameDAO.getTeachMaterialByName(teachMaterial.getName());
        if(null != teachMaterialList && 0 < teachMaterialList.size()){
            for(TeachMaterial validTeachMaterial : teachMaterialList){
                if(validTeachMaterial.getName().equals(teachMaterial.getName()) && !validTeachMaterial.getName().equals(isExistsTeachMaterial.getName())){
                    throw new BusinessException("教材名称已经存在");
                }
            }

        }
        //验证教材编号是否已经存在
        if(!StringUtils.isEmpty(teachMaterial.getIsbn())) {
            List<TeachMaterial> teachMaterialList2 = findTeachMaterialByIsbnDAO.getTeachMaterialByIsbn(teachMaterial.getIsbn());
            if (null != teachMaterialList2 && 0 < teachMaterialList2.size()) {
                for (TeachMaterial validTeachMaterial : teachMaterialList2) {
                    if (validTeachMaterial.getIsbn().equals(teachMaterial.getIsbn()) && !validTeachMaterial.getIsbn().equals(isExistsTeachMaterial.getIsbn())) {
                        throw new BusinessException("教材ISBN已经存在");
                    }
                }
            }
        }

        teachMaterial.setPrice(isExistsTeachMaterial.getPrice());
        teachMaterial.setRevision(isExistsTeachMaterial.getRevision());
        teachMaterial.setOperator(loginName);
        teachMaterial.setCreator(isExistsTeachMaterial.getCreator());
        teachMaterial.setCreateTime(isExistsTeachMaterial.getCreateTime());
        super.update(teachMaterial);
        //如果被改成了套教材，就要把该教材关联的课程删掉
        if(TeachMaterial.ISSET_YES == teachMaterial.getIsSet()){
            delTeachMaterialCourseBytmIdDAO.delTeachMaterialCourseBytmId(teachMaterial.getId());
        }

        //获取当前学期
        Semester semester = findNowSemesterService.getNowSemester();

        //状态由启用改为停用
        if(isExistsTeachMaterial.getState() == TeachMaterial.STATE_ENABLE && teachMaterial.getState() == TeachMaterial.STATE_DISABLE){
            //分别修改学生订单，学生一次性订单状态为分拣前的，如果有该教材的数量改为0
            editStudentBookOrderTMByStateAndTMIdAndSemesterIdForCountDAO.edit(StudentBookOrder.STATE_SORTING, teachMaterial.getId(), semester.getId());
            editOnceOrderTMByStateAndTMIdAndSemesterIdForCountDAO.edit(StudentBookOnceOrder.STATE_SORTING, teachMaterial.getId(), semester.getId());
        }
        //状态由停用改为启用
        if(oldState == TeachMaterial.STATE_DISABLE && newState == TeachMaterial.STATE_ENABLE){
            //分别修改学生订单，学生一次性订单状态为分拣前的，如果有该教材的数量为0的改为1

            //先查询教材关联的课程
            List<String> courseList = findCourseByTMIdDAO.find(teachMaterial.getId());
            if(null != courseList && 0 < courseList.size()){
                for(String courseCode : courseList){
                    this.addCourseTeachMaterial(courseCode, teachMaterial.getId(), teachMaterial.getPrice(), loginName);
                }
            }
        }
    }

    /**
     * 添加课程教材
     *
     */
    private void addCourseTeachMaterial(String course_code, long tmId, float price, String loginName)throws Exception{
        List<StudentBookOnceOrderTM> addOnceOrderTMList = new ArrayList<StudentBookOnceOrderTM>();
        List<StudentBookOrder> addStudentBookOrderList = new ArrayList<StudentBookOrder>();
        List<StudentBookOrderTM> addStudentBookOrderTMList = new ArrayList<StudentBookOrderTM>();
        List<StudentBookOrderLog> addStudentBookOrderLogList = new ArrayList<StudentBookOrderLog>();
        //查找选有该课程的学生
        ////改为只查当前学期入学的学生（2017-12-21 邸振涛）
        List<String> studentCodeList = findStudentByCourseCodeDAO.findNewStudent(course_code);
        //获得当前学期
        Semester semester = findNowSemesterService.getNowSemester();
        //得到当前学期最大的订单号
        int num = 0;
        StudentBookOrder maxCodeStudentBookOrder = findStudentBookOrderForMaxCodeDAO.getStudentBookOrderForMaxCode(semester.getId());
        if(null != maxCodeStudentBookOrder){
            String maxOrderCode = maxCodeStudentBookOrder.getOrderCode();
            num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
        }
        if(null != studentCodeList && 0 < studentCodeList.size()){
            for(String studentCode : studentCodeList){
                //查询该学生的一次性订单
                List<StudentBookOnceOrder> studentBookOnceOrderList = findStudentBookOnceOrderByStudentCodeDAO.find(studentCode);
                if(null != studentBookOnceOrderList && 0 < studentBookOnceOrderList.size()){
                    for(StudentBookOnceOrder studentBookOnceOrder : studentBookOnceOrderList) {
                        if (studentBookOnceOrder.getState() == StudentBookOnceOrder.STATE_UNCONFIRMED) {
                            //查询订单里面是否存在该教材，如果不存在，就新增一条明细记录
                            List<StudentBookOnceOrderTM> studentBookOnceOrderTMList = findOnceOrderTMByOrderIdAndTMIdDAO.find(tmId, studentBookOnceOrder.getId());
                            if (null == studentBookOnceOrderTMList || 0 == studentBookOnceOrderTMList.size()) {
                                StudentBookOnceOrderTM studentBookOnceOrderTM = new StudentBookOnceOrderTM();
                                studentBookOnceOrderTM.setOrderId(studentBookOnceOrder.getId());
                                studentBookOnceOrderTM.setCourseCode(course_code);
                                studentBookOnceOrderTM.setTeachMaterialId(tmId);
                                studentBookOnceOrderTM.setPrice(price);
                                studentBookOnceOrderTM.setCount(1);
                                studentBookOnceOrderTM.setIsSend(StudentBookOnceOrderTM.IS_SEND_NOT);
                                studentBookOnceOrderTM.setOperator(loginName);
                                addOnceOrderTMList.add(studentBookOnceOrderTM);
                                break;
                            }
                        }
                    }
                }else{
                    //查询学生订单, 当前学期未确认的
                    List<StudentBookOrder> studentBookOrderList = findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO.find(studentCode, semester.getId());
                    String orderCode = "";
                    if(null == studentBookOrderList || 0 == studentBookOrderList.size()){
                        //新增订单

                        //生成学生订单号
                        orderCode = OrderCodeTools.createStudentOrderCodeManual(semester.getYear(), semester.getQuarter(), num + 1);

                        StudentBookOrder studentBookOrder = new StudentBookOrder();
                        studentBookOrder.setSemesterId(semester.getId());
                        studentBookOrder.setIssueChannelId(1l);
                        studentBookOrder.setOrderCode(orderCode);
                        studentBookOrder.setStudentCode(studentCode);
                        studentBookOrder.setState(StudentBookOrder.STATE_UNCONFIRMED);
                        studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
                        studentBookOrder.setIsSpotOrder(StudentBookOrder.ISSPOTORDER_NOT);
                        studentBookOrder.setCreator(loginName);
                        studentBookOrder.setOperator(loginName);
                        addStudentBookOrderList.add(studentBookOrder);
                        //填装订单日志
                        StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
                        studentBookOrderLog.setOrderCode(orderCode);
                        studentBookOrderLog.setState(StudentBookOrder.STATE_UNCONFIRMED);
                        studentBookOrderLog.setOperator(loginName);
                        addStudentBookOrderLogList.add(studentBookOrderLog);
                        num++;

                        //增加订单明细
                        StudentBookOrderTM studentBookOrderTM = new StudentBookOrderTM();
                        studentBookOrderTM.setCourseCode(course_code);
                        studentBookOrderTM.setTeachMaterialId(tmId);
                        studentBookOrderTM.setOrderCode(orderCode);
                        studentBookOrderTM.setPrice(price);
                        studentBookOrderTM.setOperator(loginName);
                        studentBookOrderTM.setCount(1);
                        addStudentBookOrderTMList.add(studentBookOrderTM);

                    }else{
                        //查询订单里面是否存在该教材，如果不存在，就新增一条明细记录
                        List<StudentBookOrderTM> studentBookOrderTMList = findStudentBookOrderTMByOrderCodeAndTMIdDAO.find(tmId, studentBookOrderList.get(0).getOrderCode());
                        if(null == studentBookOrderTMList || 0 == studentBookOrderTMList.size()){
                            //增加订单明细
                            StudentBookOrderTM studentBookOrderTM = new StudentBookOrderTM();
                            studentBookOrderTM.setCourseCode(course_code);
                            studentBookOrderTM.setTeachMaterialId(tmId);
                            studentBookOrderTM.setOrderCode(studentBookOrderList.get(0).getOrderCode());
                            studentBookOrderTM.setPrice(price);
                            studentBookOrderTM.setOperator(loginName);
                            studentBookOrderTM.setCount(1);
                            addStudentBookOrderTMList.add(studentBookOrderTM);
                        }
                    }
                }
            }

            //批量添加订单
            batchStudentBookOrderDAO.batchAdd(addStudentBookOrderList, 1000);
            //批量添加订单明细
            batchStudentBookOrderTMDAO.batchAdd(addStudentBookOrderTMList, 1000);
            //批量添加订单日志
            batchStudentBookOrderLogDAO.batchAdd(addStudentBookOrderLogList, 1000);
            //批量添加一次性订单明细
            batchOnceOrderTMDAO.batchAdd(addOnceOrderTMList, 1000);
        }
    }
}
