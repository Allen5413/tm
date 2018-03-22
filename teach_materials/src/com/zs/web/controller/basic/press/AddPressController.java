package com.zs.web.controller.basic.press;

import com.zs.domain.basic.Press;
import com.zs.service.basic.press.AddPressService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 新增出版社
 * Created by LihongZhang on 2015/5/3.
 */
@Controller
@RequestMapping(value = "/addPress")
public class AddPressController extends LoggerController<Press,AddPressService> {
    private static Logger log = Logger.getLogger(AddPressController.class);

    @Resource
    private AddPressService addPressService;

    /**
     * 打开新增出版社的页面
     * @return
     */
    @RequestMapping(value = "openAddPressPage")
    public String openAddPressPage(){
        return "press/pressAdd";
    }

    /**
     * 新增出版社的方法
     * @param request
     * @param press
     * @return
     */
    @RequestMapping(value = "pressAdd")
    @ResponseBody
    public JSONObject addPress(HttpServletRequest request,Press press){
        JSONObject jsonObject = new JSONObject();
        try{
            if (null != press){
                addPressService.addPress(press,press.getName(), UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch (Exception e){
            String msg = super.outputException(request,e,log,"新增出版社");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;

    }

}
