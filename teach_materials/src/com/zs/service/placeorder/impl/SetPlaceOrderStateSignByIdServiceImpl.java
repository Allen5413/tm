package com.zs.service.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.EditPlaceOrderTMByOrderIdForIsSendDAO;
import com.zs.dao.placeorder.TeachMaterialPlaceOrderDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.finance.SpotExpenseBuy;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.basic.teachmaterial.FindTeachMaterialService;
import com.zs.service.finance.spotexpensebuy.AddSpotExpenseBuyService;
import com.zs.service.placeorder.FindPlaceOrderTMListForManualByOrderCodeService;
import com.zs.service.placeorder.SetPlaceOrderStateSignByIdService;
import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 把还没有发出的预订单，设置成已发出，并扣除其费用
 * 主要用于奥鹏中心，没有账号登录系统的，由邸老师操作的中心
 * Created by Allen on 2016/1/14 0014.
 */
@Service("setPlaceOrderStateSignByIdService")
public class SetPlaceOrderStateSignByIdServiceImpl extends EntityServiceImpl<TeachMaterialPlaceOrder, TeachMaterialPlaceOrderDAO> implements SetPlaceOrderStateSignByIdService {

    @Resource
    private EditPlaceOrderTMByOrderIdForIsSendDAO editPlaceOrderTMByOrderIdForIsSendDAO;
    @Resource
    private FindPlaceOrderTMListForManualByOrderCodeService findPlaceOrderTMListForManualByOrderCodeService;
    @Resource
    private FindTeachMaterialService findTeachMaterialService;
    @Resource
    private AddSpotExpenseBuyService addSpotExpenseBuyService;

    @Override
    @Transactional
    public void set(long id, String userName) throws Exception {
        TeachMaterialPlaceOrder teachMaterialPlaceOrder = super.get(id);
        if(null == teachMaterialPlaceOrder){
            throw new BusinessException("没找到");
        }
        if(teachMaterialPlaceOrder.getSpotCode().length() < 4){
            throw new BusinessException("该中心的订单不能执行该操作");
        }

        //修改该订单的状态
        teachMaterialPlaceOrder.setIsStock(TeachMaterialPlaceOrder.ISSTOCK_YES);
        teachMaterialPlaceOrder.setOrderStatus(TeachMaterialPlaceOrder.STATE_SIGN);
        teachMaterialPlaceOrder.setOperator(userName);
        teachMaterialPlaceOrder.setOperateTime(DateTools.getLongNowTime());
        super.update(teachMaterialPlaceOrder);

        //修改明细，标记为已发书
        editPlaceOrderTMByOrderIdForIsSendDAO.edit(userName, id);

        //计算费用
        //查询订单下的明细
        List<PlaceOrderDetailShow> placeOrderDetailShowList = null;
        Map<String, Object> map = findPlaceOrderTMListForManualByOrderCodeService.findPlaceOrderTMListForManualByOrderCode(teachMaterialPlaceOrder.getOrderCode());
        if(null != map){
            placeOrderDetailShowList = (List<PlaceOrderDetailShow>)map.get("data");
        }

        if(null != placeOrderDetailShowList && 0 < placeOrderDetailShowList.size()) {
            for(PlaceOrderDetailShow placeOrderDetailShow : placeOrderDetailShowList) {
                //查询教材信息
                TeachMaterial teachMaterial = findTeachMaterialService.get(placeOrderDetailShow.getMaterialId());
                if(null != teachMaterial && teachMaterial.getState() == TeachMaterial.STATE_ENABLE && 0 < placeOrderDetailShow.getCount()) {
                    //记录中心消费信息
                    SpotExpenseBuy spotExpenseBuy = new SpotExpenseBuy();
                    spotExpenseBuy.setSpotCode(teachMaterialPlaceOrder.getSpotCode());
                    spotExpenseBuy.setSemesterId(teachMaterialPlaceOrder.getSemesterId());
                    spotExpenseBuy.setType(SpotExpenseBuy.TYPE_BUY_TM);
                    spotExpenseBuy.setDetail("购买了" + placeOrderDetailShow.getCount() + "本，[" + teachMaterial.getName() + "] 教材");
                    spotExpenseBuy.setMoney(new BigDecimal(placeOrderDetailShow.getCount()).multiply(new BigDecimal(placeOrderDetailShow.getMaterialPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                    spotExpenseBuy.setTeachMaterialId(teachMaterial.getId());
                    addSpotExpenseBuyService.addSpotExpenseBuy(spotExpenseBuy, userName);
                }
            }
        }
    }
}
