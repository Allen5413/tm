package com.zs.web.controller.basic.press;

import com.zs.domain.basic.Press;
import com.zs.service.basic.press.EditPressService;
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
 * 修改出版社信息
 * Created by LihongZhang on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/editPress")
public class EditPressController extends LoggerController<Press,EditPressService> {
    private static Logger log = Logger.getLogger(EditPressController.class);

    @Resource
    private EditPressService editPressService;

    /**
     * 打开编辑出版社信息的页面
     * @param id 出版社对象的id
     * @param request
     * @return
     */
    @RequestMapping(value = "openEditPressPage")
    public String openEditPressPage(@RequestParam("id") Long id,HttpServletRequest request){
        Press press = editPressService.get(id);
        request.setAttribute("press",press);
        return "press/pressEdit";
    }

    @RequestMapping(value = "pressEdit")
    @ResponseBody
    public JSONObject editPress(HttpServletRequest request,Press press){
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != press){
                editPressService.editPress(press, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"修改出版社");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
