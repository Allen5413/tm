package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.onceordertm.DelOnceOrderTMForHaveCountByOrderIdDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdDAO;
import com.zs.dao.sale.studentbookordertm.FindIsBuyCourseForStudentCodeDAO;
import com.zs.dao.sync.selectedcourse.SelectedCourseDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import com.zs.domain.sync.SelectedCourse;
import com.zs.service.sale.onceorder.ConfirmOnceOrderService;
import com.zs.service.sale.onceordertm.CopyOnceOrderTMService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/6/28.
 */
@Service("copyOnceOrderTMService")
public class CopyOnceOrderTMServiceImpl extends EntityServiceImpl<StudentBookOnceOrderTM, FindOnceOrderTMByOrderIdDAO>
                implements CopyOnceOrderTMService{
    @Resource
    private FindOnceOrderByCodeDAO findOnceOrderByCodeDAO;
    @Resource
    private FindOnceOrderTMByOrderIdDAO findOnceOrderTMByOrderIdDAO;
    @Resource
    private DelOnceOrderTMForHaveCountByOrderIdDAO delOnceOrderTMForHaveCountByOrderIdDAO;
    @Resource
    private FindIsBuyCourseForStudentCodeDAO findIsBuyCourseForStudentCodeDAO;
    @Resource
    private SelectedCourseDAO selectedCourseDAO;
    @Resource
    private ConfirmOnceOrderService confirmOnceOrderService;

    @Override
    @Transactional
    public void copy(long id, String[] ids, String loginName) throws Exception {
        StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeDAO.get(id);
        if(null == studentBookOnceOrder){
            throw new BusinessException("没有找到订单信息");
        }
        if(null == ids || 1 > ids.length){
            throw new BusinessException("没有传入要复制的学生");
        }
        List<StudentBookOnceOrderTM> studentBookOnceOrderTMList = findOnceOrderTMByOrderIdDAO.find(studentBookOnceOrder.getId());
        List<StudentBookOnceOrderTM> copyList = new ArrayList<StudentBookOnceOrderTM>();
        if(null != studentBookOnceOrderTMList && 0 < studentBookOnceOrderTMList.size()){
            for(StudentBookOnceOrderTM studentBookOnceOrderTM : studentBookOnceOrderTMList){
                if(0 < studentBookOnceOrderTM.getCount()){
                    copyList.add(studentBookOnceOrderTM);
                }
            }

        }
        if(0 < copyList.size()) {
            for (String copyId : ids) {
                StudentBookOnceOrder copyOrder = findOnceOrderByCodeDAO.get(Long.parseLong(copyId));
                if (null != copyOrder) {
                    //查询该学生的已有选课
                    List<SelectedCourse> selectedCourseList = selectedCourseDAO.findByStudentCode(copyOrder.getStudentCode());
                    //查询该学生已确认的课程
                    List<String> isBuyCourseCodeList = findIsBuyCourseForStudentCodeDAO.find(copyOrder.getStudentCode());
                    //删掉以前的订单教材明细
                    delOnceOrderTMForHaveCountByOrderIdDAO.del(copyOrder.getId());
                    for(StudentBookOnceOrderTM copyOrderTM : copyList){
                        //如果没选过该课，并且学生订单里面也没有确认过该课程，就生成订单明细
                        if(StudentBookOnceOrderTM.IS_BUY_NOT == this.isBuyCourse(isBuyCourseCodeList, copyOrderTM.getCourseCode()) &&
                                StudentBookOnceOrderTM.IS_SELECT_NOT == this.isSelectedCourse(selectedCourseList, copyOrderTM.getCourseCode())){
                            //添加被复制的教材明细
                            StudentBookOnceOrderTM newOrderTM = new StudentBookOnceOrderTM();
                            newOrderTM.setCount(copyOrderTM.getCount());
                            newOrderTM.setIsSend(StudentBookOnceOrderTM.IS_SEND_NOT);
                            newOrderTM.setCourseCode(copyOrderTM.getCourseCode());
                            newOrderTM.setOperator(loginName);
                            newOrderTM.setOrderId(copyOrder.getId());
                            newOrderTM.setPrice(copyOrderTM.getPrice());
                            newOrderTM.setTeachMaterialId(copyOrderTM.getTeachMaterialId());
                            findOnceOrderTMByOrderIdDAO.save(newOrderTM);
                        }

                    }

                    //确认订单
                    confirmOnceOrderService.confirmOnceOrder(copyOrder.getId(), "", loginName);
                }
            }
        }
    }

    protected int isBuyCourse(List<String> isBuyCourseCodeList, String courseCode){
        if(null != isBuyCourseCodeList && 0 < isBuyCourseCodeList.size()){
            for(String buyCourseCode : isBuyCourseCodeList){
                if(buyCourseCode.equals(courseCode)){
                    return StudentBookOnceOrderTM.IS_BUY_YES;
                }
            }
        }
        return StudentBookOnceOrderTM.IS_BUY_NOT;
    }

    protected int isSelectedCourse(List<SelectedCourse> selectedCourseList, String courseCode){
        if(null != selectedCourseList && 0 < selectedCourseList.size()){
            for(SelectedCourse selectedCourse : selectedCourseList){
                if(courseCode.equals(selectedCourse.getCourseCode())){
                    return StudentBookOnceOrderTM.IS_SELECT_YES;
                }
            }
        }
        return StudentBookOnceOrderTM.IS_SELECT_NOT;
    }
}
