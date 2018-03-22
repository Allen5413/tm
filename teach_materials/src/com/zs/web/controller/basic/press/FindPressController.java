package com.zs.web.controller.basic.press;

import com.zs.domain.basic.Press;
import com.zs.service.basic.press.FindPressService;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 根据出版社的名称查询出版社信息
 * Created by LihongZhang on 2015/4/30.
 */
@Controller
@RequestMapping(value = "/findPress")
public class FindPressController extends LoggerController<Press,FindPressService> {
    private static Logger log = Logger.getLogger(FindPressController.class);

    @Resource
    private FindPressService findPressService;

    @RequestMapping(value = "findPressByName")
    public String findPress(HttpServletRequest request){
        try{
            String pressName = request.getParameter("pressName");
            Press press = findPressService.findPress(pressName);
            if(null != press && !StringUtils.isEmpty(press.getName())){
                request.setAttribute("press", press);
                return "/press/showPress";
            }else {
                return "../../login";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "findPressById")
    public String findPressById(@RequestParam("id") long id,HttpServletRequest request){
        try {
            Press press = findPressService.get(id);
            request.setAttribute("press", press);
            return "press/showPress";
        }catch(Exception e){
           super.outputException(request,e,log,"查看出版社详情");
            return "error";
        }
    }
}
