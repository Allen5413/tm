package com.zs.service.sale.studentbookordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookordertm.StudentBookOrderTmDAO;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.service.sale.studentbookordertm.StudentBookOrderTMService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/7/13.
 */
@Service("studentBookOrderTMService")
public class StudentBookOrderTMServiceImpl extends EntityServiceImpl<StudentBookOrderTM, StudentBookOrderTmDAO> implements StudentBookOrderTMService {
}
