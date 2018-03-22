package com.zs.service.basic.spotteachmaterialstock.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotteachmaterialstock.FindStockByTeachMaterialIdAndSpotCodeDao;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialByNameDAO;
import com.zs.dao.basic.teachmaterial.TeachMaterialDAO;
import com.zs.domain.basic.SpotTeachMaterialStock;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.spotteachmaterialstock.FindStockNumByTeachMaterialIdAndSpotCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现了根据教材的名称查询书籍库存的接口
 * Created by LihongZhang on 2015/5/9.
 */
@Service("findStockNumByTeachMaterialNameService")
public class FindStockNumByTeachMaterialIdAndSpotCodeServiceImpl extends EntityServiceImpl<SpotTeachMaterialStock,FindStockByTeachMaterialIdAndSpotCodeDao> implements FindStockNumByTeachMaterialIdAndSpotCodeService {

    @Resource
    private FindStockByTeachMaterialIdAndSpotCodeDao findStockByTeachMaterialIdAndSpotCodeDao;

    @Resource
    private TeachMaterialDAO teachMaterialDAO;

    @Override
    public int getStockByTeachMaterialIdAndSpotCode(Long teachMaterialId, String spotCode) throws Exception {
        int teachMaterialNum = 0;
        //对参数进行验证
        if (spotCode.equals("")) {
            throw new BusinessException("学习中心的编号不能为空");
        }
        //判断教材是否存在
        TeachMaterial teachMaterial = teachMaterialDAO.get(teachMaterialId);
        if (null == teachMaterial){
            throw new BusinessException("教材不存在");
        }
        teachMaterialNum = findStockByTeachMaterialIdAndSpotCodeDao.getStockByTeachMaterialIdAndSpotCode(teachMaterialId,spotCode).getStock();
        return teachMaterialNum;
    }
}
