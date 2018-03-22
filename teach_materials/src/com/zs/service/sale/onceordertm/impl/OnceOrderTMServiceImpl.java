package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdDAO;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import com.zs.service.sale.onceordertm.OnceOrderTMService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/7/13.
 */
@Service("onceOrderTMService")
public class OnceOrderTMServiceImpl extends EntityServiceImpl<StudentBookOnceOrderTM, FindOnceOrderTMByOrderIdDAO> implements OnceOrderTMService {
}
