package com.zs.service.sync.spec.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spec.FindSpecAllDAO;
import com.zs.dao.sync.spec.SpecDAO;
import com.zs.domain.sync.Spec;
import com.zs.service.sync.spec.FindSpecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/26.
 */
@Service("findSpecService")
public class FindSpecServiceImpl extends EntityServiceImpl<Spec, SpecDAO> implements FindSpecService {

    @Resource
    private FindSpecAllDAO findSpecAllDAO;

    @Override
    public List<Spec> getAll(){
        return findSpecAllDAO.getSpecAll();
    }
    
    
}
