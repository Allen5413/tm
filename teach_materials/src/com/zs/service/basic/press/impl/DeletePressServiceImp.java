package com.zs.service.basic.press.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.press.PressDao;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialByPressIdDAO;
import com.zs.domain.basic.Press;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.press.DeletePressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 实现删除
 * Created by Administrator on 2015/5/3.
 */
@Service("deletePressService")
public class DeletePressServiceImp extends EntityServiceImpl<Press, PressDao> implements DeletePressService {

    @Resource
    private PressDao pressDao;
    @Resource
    private FindTeachMaterialByPressIdDAO findTeachMaterialByPressIdDAO;

    @Override
    public void deletePressById(Long id) throws Exception {
        Press press = pressDao.get(id);
        if (null != press){
            //判断出版社下面是否有书籍
            List<TeachMaterial> teachMaterials = findTeachMaterialByPressIdDAO.getTeachMaterialByPressId(id);
            if(null != teachMaterials && 0 < teachMaterials.size()){
                throw new BusinessException("该出版社下面还存在书籍，不能删除");
            }
            super.remove(press);
        }else {
            throw new BusinessException("要删除的出版社不存在");
        }
    }
}
