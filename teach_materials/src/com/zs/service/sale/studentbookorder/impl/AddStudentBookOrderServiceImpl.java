package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookorderlog.StudentBookOrderLogDAO;
import com.zs.dao.sale.studentbookordertm.StudentBookOrderTmDAO;
import com.zs.dao.sync.course.FindCourseByCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.*;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderLog;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.domain.sync.Course;
import com.zs.domain.sync.Student;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.sale.studentbookorder.AddStudentBookOrderService;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 手动添加学生增补教材
 * Created by Allen on 2015/5/13.
 */
@Service("addStudentBookOrderService")
public class AddStudentBookOrderServiceImpl
        extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements AddStudentBookOrderService {

    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindStudentBookOrderForMaxCodeDAO findStudentBookOrderForMaxCodeDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private StudentBookOrderTmDAO studentBookOrderTmDAO;
    @Resource
    private FindSetTeachMaterialByTMIdDAO findSetTeachMaterialByTMIdDAO;
    @Resource
    private FindTeachMaterialCourseByTMIdDAO findTeachMaterialCourseByTMIdDAO;
    @Resource
    private StudentBookOrderLogDAO studentBookOrderLogDAO;
    @Resource
    private FindCourseByCodeDAO findCourseByCodeDAO;

    @Override
    @Transactional
    public JSONObject addStudentBookOrder(String studentCode, String idAndCounts, String loginName) throws Exception {
        JSONObject returnJSON = new JSONObject();
        if(StringUtils.isEmpty(studentCode)){
            throw new BusinessException("没有传入学号");
        }
        if(StringUtils.isEmpty(idAndCounts)){
            throw new BusinessException("没有传入教材信息");
        }

        //获取学生信息
        Student student = findStudentByCodeDAO.getStudentByCode(studentCode);
        if(null == student || !student.getCode().equals(studentCode)){
            throw new BusinessException("没有找到学号："+studentCode+", 的学生信息");
        }
        //根据学生的学习中心查询关联的发行渠道
        IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(student.getSpotCode());
        Long issueChannelId = 0l;
        if(null == issueRange || !issueRange.getSpotCode().equals(student.getSpotCode())){
            throw new BusinessException("没有找到该学生所属学习中心关联的渠道信息");
        }
        issueChannelId = issueRange.getIssueChannelId();

        //获取当前学期
        Semester semester = findNowSemesterService.getNowSemester();

        //得到当前学期最大的订单号
        int num = 0;
        StudentBookOrder maxCodeStudentBookOrder = findStudentBookOrderForMaxCodeDAO.getStudentBookOrderForMaxCode(semester.getId());
        if(null != maxCodeStudentBookOrder){
            String maxOrderCode = maxCodeStudentBookOrder.getOrderCode();
            num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
        }
        //生成学生订单号
        String orderCode = OrderCodeTools.createStudentOrderCodeManual(semester.getYear(), semester.getQuarter(), num + 1);
        //添加订单信息
        StudentBookOrder studentBookOrder = new StudentBookOrder();
        studentBookOrder.setSemesterId(semester.getId());
        studentBookOrder.setIssueChannelId(issueChannelId);
        studentBookOrder.setOrderCode(orderCode);
        studentBookOrder.setStudentCode(studentCode);
        if(student.getIsForeverSnedTm() == Student.IS_FOREVER_SNEDTM_YES){
            studentBookOrder.setState(StudentBookOrder.STATE_CONFIRMED);
        }else {
            studentBookOrder.setState(StudentBookOrder.STATE_UNCONFIRMED);
        }
        studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
        studentBookOrder.setIsSpotOrder(StudentBookOrder.ISSPOTORDER_NOT);
        studentBookOrder.setStudentSign(StudentBookOrder.STUDENTSIGN_NOT);
        if(Student.IS_SEND_STUDENT_NOT == student.getIsSendStudent()){
            studentBookOrder.setIsSendStudent(StudentBookOrder.IS_SEND_STUDENT_NOT);
        }
        if(Student.IS_SEND_STUDENT_YES == student.getIsSendStudent()){
            studentBookOrder.setIsSendStudent(StudentBookOrder.IS_SEND_STUDENT_YES);
            studentBookOrder.setSendPhone(student.getSendPhone());
            studentBookOrder.setSendZipCode(student.getSendZipCode());
            studentBookOrder.setSendAddress(student.getSendAddress());
        }
        studentBookOrder.setCreator(loginName);
        studentBookOrder.setOperator(loginName);
        super.save(studentBookOrder);

        //添加订单日志信息
        StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
        studentBookOrderLog.setOrderCode(orderCode);
        studentBookOrderLog.setState(studentBookOrder.getState());
        studentBookOrderLog.setOperator(loginName);
        studentBookOrderLogDAO.save(studentBookOrderLog);


        //添加订单教材明细
        JSONArray jsonArray = new JSONArray();
        String[] idAndCountArray = idAndCounts.split(",");
        int totalCount = 0;
        double totalPrice = 0;
        if(null != idAndCountArray && 0 < idAndCountArray.length){
            for(String idAndCount : idAndCountArray){
                JSONObject jsonObject = new JSONObject();
                long tmId = Long.parseLong(idAndCount.split("_")[0]);
                int count = Integer.parseInt(idAndCount.split("_")[1]);
                String courseCode = idAndCount.split("_")[2];
                //获取教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(tmId);
                if(null == teachMaterial || teachMaterial.getId() != tmId){
                    throw new BusinessException("教材信息没有找到");
                }

                StudentBookOrderTM studentBookOrderTM = new StudentBookOrderTM();
                studentBookOrderTM.setOrderCode(orderCode);
                studentBookOrderTM.setCourseCode(courseCode);
                studentBookOrderTM.setTeachMaterialId(tmId);
                studentBookOrderTM.setPrice(teachMaterial.getPrice());
                studentBookOrderTM.setCount(count);
                studentBookOrderTM.setOperator(loginName);
                studentBookOrderTmDAO.save(studentBookOrderTM);

                Course course = findCourseByCodeDAO.find(courseCode);
                jsonObject.put("courseCode", courseCode);
                jsonObject.put("courseName", null == course ? "" : course.getName());
                jsonObject.put("tmName", teachMaterial.getName());
                jsonObject.put("price", new BigDecimal(studentBookOrderTM.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                jsonObject.put("count", studentBookOrderTM.getCount());
                jsonObject.put("totalPrice", new BigDecimal(studentBookOrderTM.getPrice()).multiply(new BigDecimal(studentBookOrderTM.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                jsonObject.put("orderTMId", studentBookOrderTM.getId());
                jsonArray.add(jsonObject);

                totalCount += studentBookOrderTM.getCount();
                totalPrice = new BigDecimal(totalPrice).add(new BigDecimal(studentBookOrderTM.getPrice()).multiply(new BigDecimal(studentBookOrderTM.getCount()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        returnJSON.put("studentBookOrder", studentBookOrder);
        returnJSON.put("orderTMArray", jsonArray);
        returnJSON.put("totalCount", totalCount);
        returnJSON.put("totalPrice", new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return returnJSON;
    }
}
