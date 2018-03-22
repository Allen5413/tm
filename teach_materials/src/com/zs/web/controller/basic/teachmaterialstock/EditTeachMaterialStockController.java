package com.zs.web.controller.basic.teachmaterialstock;

import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.EditTeachMaterialStockService;
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
 * 修改教材库存
 * Created by Allen on 2015/8/11.
 */
@Controller
@RequestMapping(value = "/editTeachMaterialStock")
public class EditTeachMaterialStockController extends
        LoggerController<TeachMaterialStock, EditTeachMaterialStockService> {

    private static Logger log = Logger.getLogger(EditTeachMaterialStockController.class);

    @Resource
    private EditTeachMaterialStockService editTeachMaterialStockService;

    /**
     * 打开编辑页面
     * @return
     */
    @RequestMapping(value = "open")
    public String openEditMenuPage(@RequestParam("id") long id, HttpServletRequest request){
        TeachMaterialStock teachMaterialStock = editTeachMaterialStockService.get(id);
        request.setAttribute("teachMaterialStock", teachMaterialStock);
        return "teachMaterial/tmStockEdit";
    }

    /**
     * 编辑菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "stockEdit")
    @ResponseBody
    public JSONObject stockEdit(HttpServletRequest request, @RequestParam("id") long id, @RequestParam("stock") long stock){
        JSONObject jsonObject = new JSONObject();
        try{
            TeachMaterialStock teachMaterialStock = editTeachMaterialStockService.get(id);
            teachMaterialStock.setStock(stock);
            editTeachMaterialStockService.edit(teachMaterialStock, UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改教材库存");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
