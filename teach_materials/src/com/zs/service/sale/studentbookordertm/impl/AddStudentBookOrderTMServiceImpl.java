package com.zs.service.sale.studentbookordertm.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.FindSetTeachMaterialByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeDAO;
import com.zs.dao.sale.studentbookordertm.StudentBookOrderTmDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.sale.studentbookordertm.AddStudentBookOrderTMService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手动新增订单明细
 * Created by Allen on 2015/7/14.
 */
@Service("addStudentBookOrderTMService")
public class AddStudentBookOrderTMServiceImpl extends EntityServiceImpl<StudentBookOrderTM, StudentBookOrderTmDAO> implements AddStudentBookOrderTMService {

    @Resource
    private FindStudentBookOrderTMByOrderCodeDAO findStudentBookOrderTMByOrderCodeDAO;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private FindSetTeachMaterialByTMIdDAO findSetTeachMaterialByTMIdDAO;
    @Resource
    private FindTeachMaterialCourseByTMIdDAO findTeachMaterialCourseByTMIdDAO;

    @Override
    @Transactional
    public void AddStudentBookOrderTM(String orderCode, String idAndCounts, String loginName) throws Exception {
        if(StringUtils.isEmpty(idAndCounts)){
            throw new BusinessException("没有传入教材信息");
        }
        //查询订单明细
        List<StudentBookOrderTM> oldOrderTMList = findStudentBookOrderTMByOrderCodeDAO.findStudentBookOrderTMByOrderCode(orderCode);

        String[] idAndCountArray = idAndCounts.split(",");
        if(null != idAndCountArray && 0 < idAndCountArray.length){
            for(String idAndCount : idAndCountArray){
                long tmId = Long.parseLong(idAndCount.split("_")[0]);
                int count = Integer.parseInt(idAndCount.split("_")[1]);
                //获取教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(tmId);
                if(null == teachMaterial || teachMaterial.getId() != tmId){
                    throw new BusinessException("教材信息没有找到");
                }

                //存在的就不添加了，不存在的才会被添加
                boolean isExists = false;
                if(null != oldOrderTMList && 0 < oldOrderTMList.size()){
                    for(StudentBookOrderTM studentBookOrderTM : oldOrderTMList){
                        if(tmId == studentBookOrderTM.getTeachMaterialId()){
                            isExists = true;
                            break;
                        }
                    }
                }

                if(!isExists){
                    //查询教材关联的课程,这里是套教材，就去查购买课程；不是套教材，查关联课程
                    String courseCode = "";
                    if(teachMaterial.getIsSet() == TeachMaterial.ISSET_YES){
                        SetTeachMaterial setTeachMaterial = findSetTeachMaterialByTMIdDAO.findSetTeachMaterialByTMId(tmId);
                        courseCode = setTeachMaterial.getBuyCourseCode();
                    }else{
                        List<TeachMaterialCourse> teachMaterialCourseList = findTeachMaterialCourseByTMIdDAO.findTeachMaterialCourseByTMId(tmId);
                        if(null != teachMaterialCourseList && 0 < teachMaterialCourseList.size()){
                            courseCode = teachMaterialCourseList.get(0).getCourseCode();
                        }
                    }

                    StudentBookOrderTM studentBookOrderTM = new StudentBookOrderTM();
                    studentBookOrderTM.setOrderCode(orderCode);
                    studentBookOrderTM.setCourseCode(courseCode);
                    studentBookOrderTM.setTeachMaterialId(tmId);
                    studentBookOrderTM.setPrice(teachMaterial.getPrice());
                    studentBookOrderTM.setCount(count);
                    studentBookOrderTM.setIsSend(StudentBookOrderTM.IS_SEND_NOT);
                    studentBookOrderTM.setOperator(loginName);
                    super.save(studentBookOrderTM);
                }
            }
        }
    }
}
