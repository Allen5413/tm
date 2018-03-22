package com.zs.service.basic.setteachmaterialcourse.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterialcourse.DelSetTeachMaterialCourseBystmIdDAO;
import com.zs.dao.basic.setteachmaterialcourse.SetTeachMaterialCourseDAO;
import com.zs.domain.basic.SetTeachMaterialCourse;
import com.zs.service.basic.setteachmaterialcourse.AddSetTeachMaterialCourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/20.
 */
@Service("addSetTeachMaterialCourseService")
public class AddSetTeachMaterialCourseServiceImpl
        extends EntityServiceImpl<SetTeachMaterialCourse, SetTeachMaterialCourseDAO>
        implements AddSetTeachMaterialCourseService {

    @Resource
    private DelSetTeachMaterialCourseBystmIdDAO delSetTeachMaterialCourseBystmIdDAO;

    @Override
    @Transactional
    public void addSetTeachMaterialCourse(Long setTeachMaterialId, String courseCodes, String loginName) throws Exception {
        if(null == setTeachMaterialId || 0 == setTeachMaterialId){
            throw new BusinessException("没有传入套教材ID");
        }
        //删除掉以前的关联
        delSetTeachMaterialCourseBystmIdDAO.delSetTeachMaterialCourseBystmId(setTeachMaterialId);
        if(!StringUtils.isEmpty(courseCodes)) {
            //循环增加新的关联
            String[] courseCodeArray = courseCodes.split(",");
            if(null != courseCodeArray && 0 < courseCodeArray.length) {
                for (String couserCode : courseCodeArray) {
                    SetTeachMaterialCourse setTeachMaterialCourse = new SetTeachMaterialCourse();
                    setTeachMaterialCourse.setSetTeachMaterialId(setTeachMaterialId);
                    setTeachMaterialCourse.setCourseCode(couserCode);
                    setTeachMaterialCourse.setCreator(loginName);
                    super.save(setTeachMaterialCourse);
                }
            }
        }
    }
}
