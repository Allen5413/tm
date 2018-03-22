package com.zs.service.basic.setteachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.setteachmaterial.SetTeachMaterialDAO;
import com.zs.domain.basic.SetTeachMaterial;
import com.zs.service.basic.setteachmaterial.FindSetTeachMaterialService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/4/30.
 */
@Service("findSetTeachMaterialService")
public class FindSetTeachMaterialServiceImpl extends EntityServiceImpl<SetTeachMaterial, SetTeachMaterialDAO> implements FindSetTeachMaterialService {
}
