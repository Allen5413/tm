package com.zs.service.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorderpackage.StudentBookOrderPackageDAO;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.service.sale.studentbookorderpackage.EditStudentBookOrderPackageForLogisticCodeService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/3/23.
 */
@Service("editStudentBookOrderPackageForLogisticCodeService")
public class EditStudentBookOrderPackageForLogisticCodeServiceImpl
        extends EntityServiceImpl<StudentBookOrderPackage, StudentBookOrderPackageDAO>
        implements EditStudentBookOrderPackageForLogisticCodeService {


    @Override
    public void edit(long id, String logisticCodes, String loginName) {
        StudentBookOrderPackage studentBookOrderPackage = super.get(id);
        if(null == studentBookOrderPackage){
            throw new BusinessException("没有找到该包裹");
        }
        if(StringUtils.isEmpty(logisticCodes)){
            throw new BusinessException("没有传入快递单号");
        }
        studentBookOrderPackage.setLogisticCode(logisticCodes.replaceAll("，", ",").trim());
        studentBookOrderPackage.setOperator(loginName);
        studentBookOrderPackage.setOperateTime(DateTools.getLongNowTime());
        super.update(studentBookOrderPackage);
    }
}
