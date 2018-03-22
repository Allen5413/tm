package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderForMaxCodeDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdDAO;
import com.zs.dao.sync.course.FindCourseByCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.sale.*;
import com.zs.domain.sync.Course;
import com.zs.domain.sync.Student;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.sale.onceorder.AddOnceOrderService;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 手动添加学生增补教材
 * Created by Allen on 2015/5/13.
 */
@Service("addOnceOrderService")
public class AddOnceOrderServiceImpl
        extends EntityServiceImpl<StudentBookOnceOrder, FindStudentBookOnceOrderForMaxCodeDAO>
        implements AddOnceOrderService {

    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindStudentBookOnceOrderForMaxCodeDAO findStudentBookOnceOrderForMaxCodeDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindOnceOrderTMByOrderIdDAO findOnceOrderTMByOrderIdDAO;
    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;
    @Resource
    private FindCourseByCodeDAO findCourseByCodeDAO;

    @Override
    public JSONObject add(String studentCode, String idAndCounts, String loginName) throws Exception {
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
        StudentBookOnceOrder maxCodeOnceOrder = findStudentBookOnceOrderForMaxCodeDAO.find(semester.getId());
        if(null != maxCodeOnceOrder && !StringUtils.isEmpty(maxCodeOnceOrder.getOrderCode())){
            String maxOrderCode = maxCodeOnceOrder.getOrderCode();
            num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
        }
        //生成学生订单号
        String orderCode = OrderCodeTools.createStudentOnceOrderCodeForManual(semester.getYear(), semester.getQuarter(), num + 1);
        //添加订单信息
        StudentBookOnceOrder studentBookOnceOrder = new StudentBookOnceOrder();
        studentBookOnceOrder.setSemesterId(semester.getId());
        studentBookOnceOrder.setOrderCode(orderCode);
        studentBookOnceOrder.setIssueChannelId(issueChannelId);
        studentBookOnceOrder.setStudentCode(studentCode);
        studentBookOnceOrder.setState(StudentBookOnceOrder.STATE_CONFIRMED);
        studentBookOnceOrder.setStudentSign(StudentBookOnceOrder.STUDENTSIGN_NOT);
        if(Student.IS_SEND_STUDENT_NOT == student.getIsSendStudent()){
            studentBookOnceOrder.setIsSendStudent(StudentBookOnceOrder.IS_SEND_STUDENT_NOT);
        }
        if(Student.IS_SEND_STUDENT_YES == student.getIsSendStudent()) {
            studentBookOnceOrder.setIsSendStudent(StudentBookOnceOrder.IS_SEND_STUDENT_YES);
            studentBookOnceOrder.setSendPhone(student.getSendPhone());
            studentBookOnceOrder.setSendZipCode(student.getSendZipCode());
            studentBookOnceOrder.setSendAddress(student.getSendAddress());
        }
        studentBookOnceOrder.setCreator("管理员");
        studentBookOnceOrder.setOperator("管理员");
        findStudentBookOnceOrderForMaxCodeDAO.save(studentBookOnceOrder);

        //添加订单日志信息
        StudentBookOnceOrderLog studentBookOnceOrderLog = new StudentBookOnceOrderLog();
        studentBookOnceOrderLog.setOrderId(studentBookOnceOrder.getId());
        studentBookOnceOrderLog.setState(StudentBookOnceOrder.STATE_CONFIRMED);
        studentBookOnceOrderLog.setOperator("管理员");
        onceOrderLogDAO.save(studentBookOnceOrderLog);


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

                StudentBookOnceOrderTM studentBookOnceOrderTM = new StudentBookOnceOrderTM();
                studentBookOnceOrderTM.setOrderId(studentBookOnceOrder.getId());
                studentBookOnceOrderTM.setCourseCode(courseCode);
                studentBookOnceOrderTM.setTeachMaterialId(teachMaterial.getId());
                studentBookOnceOrderTM.setPrice(teachMaterial.getPrice());
                studentBookOnceOrderTM.setCount(1);
                studentBookOnceOrderTM.setIsSend(StudentBookOnceOrderTM.IS_SEND_NOT);
                studentBookOnceOrderTM.setOperator("管理员");
                findOnceOrderTMByOrderIdDAO.save(studentBookOnceOrderTM);

                Course course = findCourseByCodeDAO.find(courseCode);
                jsonObject.put("courseCode", courseCode);
                jsonObject.put("courseName", null == course ? "" : course.getName());
                jsonObject.put("tmName", teachMaterial.getName());
                jsonObject.put("price", new BigDecimal(studentBookOnceOrderTM.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                jsonObject.put("count", studentBookOnceOrderTM.getCount());
                jsonObject.put("totalPrice", new BigDecimal(studentBookOnceOrderTM.getPrice()).multiply(new BigDecimal(studentBookOnceOrderTM.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                jsonObject.put("orderTMId", studentBookOnceOrderTM.getId());
                jsonArray.add(jsonObject);

                totalCount += studentBookOnceOrderTM.getCount();
                totalPrice = new BigDecimal(totalPrice).add(new BigDecimal(studentBookOnceOrderTM.getPrice()).multiply(new BigDecimal(studentBookOnceOrderTM.getCount()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        returnJSON.put("studentBookOnceOrder", studentBookOnceOrder);
        returnJSON.put("orderTMArray", jsonArray);
        returnJSON.put("totalCount", totalCount);
        returnJSON.put("totalPrice", new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return returnJSON;
    }
}
