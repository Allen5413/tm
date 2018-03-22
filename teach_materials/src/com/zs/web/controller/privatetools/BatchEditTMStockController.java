package com.zs.web.controller.privatetools;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.service.privatetools.BatchEditTMStockService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 批量修改教材库存，需要先把要修改的数据导入到临时表
 * Created by Allen on 2015/8/9.
 */
@Controller
@RequestMapping(value = "/batchEditTMStock")
public class BatchEditTMStockController extends LoggerController {
    private static Logger log = Logger.getLogger(BatchEditTMStockController.class);

    @Resource
    private BatchEditTMStockService batchEditTMStockService;

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest request){
        try{
            if(!"admin".equals(UserTools.getLoginUserForLoginName(request))){
                throw new BusinessException("该操作只能由管理员操作");
            }
            List<JSONObject> list = batchEditTMStockService.updateStock();
            request.setAttribute("list", list);
        }catch(Exception e){
            super.outputException(request, e, log, "批量修改教材库存");
            return "error";
        }
        return "privateTools/batchEditTMStock";
    }
}
