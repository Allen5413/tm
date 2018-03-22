package com.zs.web.controller.sale.onceordertm;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import com.zs.service.sale.onceorder.FindOnceOrderByCodeService;
import com.zs.service.sale.onceordertm.OnceOrderTMService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 增加学生订单明细教材数量
 * Created by Allen on 2015/7/13.
 */
@Controller
@RequestMapping(value = "/addOnceOrderTMCount")
public class AddOnceOrderTMCountController extends
        LoggerController<StudentBookOnceOrderTM, OnceOrderTMService> {

    private static Logger log = Logger.getLogger(AddOnceOrderTMCountController.class);

    @Resource
    private OnceOrderTMService onceOrderTMService;
    @Resource
    private FindOnceOrderByCodeService findOnceOrderByCodeService;


    /**
     * 新增教材数量
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(@RequestParam("id") long id,
            @RequestParam("count") int count,
            HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            StudentBookOnceOrderTM studentBookOnceOrderTM = onceOrderTMService.get(id);
            if(null == studentBookOnceOrderTM){
                throw new BusinessException("没有找到信息记录");
            }
            //查询订单信息
            StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeService.get(studentBookOnceOrderTM.getOrderId());
            if(null == studentBookOnceOrder){
                throw new BusinessException("没有找到该明细的订单信息记录");
            }
            if(1 < studentBookOnceOrderTM.getCount() + count){
                throw new BusinessException("教材数量不能大于1");
            }
            if(0 > studentBookOnceOrderTM.getCount() + count){
                throw new BusinessException("教材数量不能小于0");
            }
            studentBookOnceOrderTM.setCount(studentBookOnceOrderTM.getCount() + count);
            studentBookOnceOrderTM.setOperator(UserTools.getLoginUserForName(request));
            onceOrderTMService.update(studentBookOnceOrderTM);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改数量");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
