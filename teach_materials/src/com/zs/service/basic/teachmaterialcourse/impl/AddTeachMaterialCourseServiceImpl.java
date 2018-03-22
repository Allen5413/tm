package com.zs.service.basic.teachmaterialcourse.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.teachmaterialcourse.DelTeachMaterialCourseBytmIdAndCourseCodeDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.TeachMaterialCourseDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderByStudentCodeDAO;
import com.zs.dao.sale.onceordertm.BatchOnceOrderTMDAO;
import com.zs.dao.sale.onceordertm.DelOnceOrderTMByTMIdAndCourseCodeDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdAndTMIdDAO;
import com.zs.dao.sale.studentbookorder.BatchStudentBookOrderDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForNotSendByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;
import com.zs.dao.sale.studentbookorderlog.BatchStudentBookOrderLogDAO;
import com.zs.dao.sale.studentbookordertm.BatchStudentBookOrderTMDAO;
import com.zs.dao.sale.studentbookordertm.DelStudentBookOrderTMByTMIdAndCourseCode;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeAndTMIdDAO;
import com.zs.dao.sync.selectedcourse.FindStudentByCourseCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.domain.sale.*;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.basic.teachmaterialcourse.AddTeachMaterialCourseService;
import com.zs.tools.OrderCodeTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
@Service("addTeachMaterialCourseService")
public class AddTeachMaterialCourseServiceImpl extends EntityServiceImpl<TeachMaterialCourse, TeachMaterialCourseDAO> implements AddTeachMaterialCourseService {

    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindTeachMaterialCourseByTMIdDAO findTeachMaterialCourseByTMIdDAO;
    @Resource
    private DelTeachMaterialCourseBytmIdAndCourseCodeDAO delTeachMaterialCourseBytmIdAndCourseCodeDAO;
    @Resource
    private BatchStudentBookOrderDAO batchStudentBookOrderDAO;
    @Resource
    private BatchStudentBookOrderTMDAO batchStudentBookOrderTMDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindStudentBookOrderForMaxCodeDAO findStudentBookOrderForMaxCodeDAO;
    @Resource
    private BatchStudentBookOrderLogDAO batchStudentBookOrderLogDAO;
    @Resource
    private DelStudentBookOrderTMByTMIdAndCourseCode delStudentBookOrderTMByTMIdAndCourseCode;
    @Resource
    private DelOnceOrderTMByTMIdAndCourseCodeDAO delOnceOrderTMByTMIdAndCourseCodeDAO;
    @Resource
    private FindStudentByCourseCodeDAO findStudentByCourseCodeDAO;
    @Resource
    private FindStudentBookOnceOrderByStudentCodeDAO findStudentBookOnceOrderByStudentCodeDAO;
    @Resource
    private FindOnceOrderTMByOrderIdAndTMIdDAO findOnceOrderTMByOrderIdAndTMIdDAO;
    @Resource
    private FindStudentBookOrderForNotSendByStudentCodeDAO findStudentBookOrderForNotSendByStudentCodeDAO;
    @Resource
    private BatchOnceOrderTMDAO batchOnceOrderTMDAO;
    @Resource
    private FindStudentBookOrderTMByOrderCodeAndTMIdDAO findStudentBookOrderTMByOrderCodeAndTMIdDAO;
    @Resource
    private FindStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO findStudentBookOrderForNowSemesterUnconfirmedByStudentCodeDAO;


    @Override
    @Transactional
    public void addTeachMaterialCourse(Long teachMaterialId, String courseCodes, String loginName) throws Exception {
        List<String> addCourseCodeList = new ArrayList<String>();
        List<String> delCourseCodeList = new ArrayList<String>();
        if(null == teachMaterialId || 0 == teachMaterialId){
            throw new BusinessException("没有传入教材ID");
        }
        TeachMaterial teachMaterial = findTeachMaterialService.get(teachMaterialId);
        if(null == teachMaterial){
            throw new BusinessException("没有找到教材信息");
        }
        //得到以前的关联
        List<TeachMaterialCourse> oldteachMaterialCourseList = findTeachMaterialCourseByTMIdDAO.findTeachMaterialCourseByTMId(teachMaterialId);
        //判断本次更新，哪些旧的关联是要删除的
        if(null != oldteachMaterialCourseList && 0 < oldteachMaterialCourseList.size()){
            for (TeachMaterialCourse teachMaterialCourse : oldteachMaterialCourseList){
                boolean isExists = false;
                if(!StringUtils.isEmpty(courseCodes)) {
                    //循环新的关联
                    String[] courseCodeArray = courseCodes.split(",");
                    if(null != courseCodeArray && 0 < courseCodeArray.length) {
                        for (String couserCode : courseCodeArray) {
                            if(teachMaterialCourse.getCourseCode().equals(couserCode)){
                                isExists = true;
                                break;
                            }
                        }
                    }
                }
                if(!isExists){
                    delCourseCodeList.add(teachMaterialCourse.getCourseCode());
                }
            }
        }
        //判断本次更新，哪些关联是要新增的
        if(!StringUtils.isEmpty(courseCodes)) {
            //循环新的关联
            String[] courseCodeArray = courseCodes.split(",");
            if(null != courseCodeArray && 0 < courseCodeArray.length) {
                for (String couserCode : courseCodeArray) {
                    boolean isExists = false;
                    if(null != oldteachMaterialCourseList && 0 < oldteachMaterialCourseList.size()){
                        for (TeachMaterialCourse teachMaterialCourse : oldteachMaterialCourseList){
                            if(teachMaterialCourse.getCourseCode().equals(couserCode)){
                                isExists = true;
                                break;
                            }
                        }
                    }
                    if(!isExists){
                        addCourseCodeList.add(couserCode);
                    }
                }
            }
        }

        //删除旧的关联，还要删除学生订单中的关联信息，这里不删除中心预订单中的关联信息是因为预订单是在发书的时候才确定课程教材，之前都是只关联课程
        if(null != delCourseCodeList && 0 < delCourseCodeList.size()){
            for(String courseCode : delCourseCodeList){
                //删除教材课程关联
                delTeachMaterialCourseBytmIdAndCourseCodeDAO.delTeachMaterialCourseBytmIdAndCourseCode(teachMaterialId, courseCode);
                //删除学生订单明细，状态是分拣以前的
                delStudentBookOrderTMByTMIdAndCourseCode.delStudentBookOrderTMByTMIdAndCourseCode(StudentBookOrder.STATE_SORTING, teachMaterialId, courseCode);
                //删除学生一次性订单明细，状态是分拣以前的
                delOnceOrderTMByTMIdAndCourseCodeDAO.del(StudentBookOnceOrder.STATE_SORTING, teachMaterialId, courseCode);
            }
        }

        //新增新的教材课程的关联
        if(null != addCourseCodeList && 0 < addCourseCodeList.size()){
            for(String courseCode : addCourseCodeList){
                TeachMaterialCourse teachMaterialCourse = new TeachMaterialCourse();
                teachMaterialCourse.setTeachMaterialId(teachMaterialId);
                teachMaterialCourse.setCourseCode(courseCode);
                teachMaterialCourse.setCreator(loginName);
                super.save(teachMaterialCourse);
                //添加学生订单信息
                this.addCourseTeachMaterial(courseCode, teachMaterialId, teachMaterial.getPrice(), loginName);
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
        //改为只查当前学期入学的学生（2017-12-21 邸振涛）
        List<String> studentCodeList = findStudentByCourseCodeDAO.findNewStudent(course_code);
        //获得当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();
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
