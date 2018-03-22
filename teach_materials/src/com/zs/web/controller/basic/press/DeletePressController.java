package com.zs.web.controller.basic.press;

import com.zs.domain.basic.Press;
import com.zs.service.basic.press.DeletePressService;
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
 * 删除出版社
 * Created by LihongZhang on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/deletePress")
public class DeletePressController extends LoggerController<Press,DeletePressService> {
    private static Logger log = Logger.getLogger(DeletePressController.class);

    @Resource
    private DeletePressService deletePressService;

    @RequestMapping(value = "pressDelete")
    @ResponseBody
    public JSONObject deletePress(@RequestParam("id") Long id,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            deletePressService.deletePressById(id);
            jsonObject.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"删除出版社");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
