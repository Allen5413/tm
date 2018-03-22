package com.zs.web.controller.basic.teachmaterialstock;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.teachmaterialstock.AddTeachMaterialStockService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新增教材库存
 * Created by Allen on 2015/8/11.
 */
@Controller
@RequestMapping(value = "/addTeachMaterialStock")
public class AddTeachMaterialStockController extends
        LoggerController<TeachMaterialStock, AddTeachMaterialStockService> {

    private static Logger log = Logger.getLogger(AddTeachMaterialStockController.class);

    @Resource
    private AddTeachMaterialStockService addTeachMaterialStockService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    /**
     * 打开编辑页面
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request){
        List<IssueChannel> issueChannelList = findIssueChannelService.getAll();
        request.setAttribute("issueChannelList", issueChannelList);
        return "teachMaterial/tmStockAdd";
    }

    /**
     * 编辑菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "stockAdd")
    @ResponseBody
    public JSONObject stockAdd(HttpServletRequest request, TeachMaterialStock teachMaterialStock){
        JSONObject jsonObject = new JSONObject();
        try{
            addTeachMaterialStockService.add(teachMaterialStock, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增教材库存");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
