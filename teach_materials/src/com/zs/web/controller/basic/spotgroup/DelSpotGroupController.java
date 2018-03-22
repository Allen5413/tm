package com.zs.web.controller.basic.spotgroup;

import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.DelSpotGroupService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/3.
 */
@Controller
@RequestMapping(value = "/delSpotGroup")
public class DelSpotGroupController extends
        LoggerController<SpotGroup, DelSpotGroupService> {
    private static Logger log = Logger.getLogger(DelSpotGroupController.class);

    @Resource
    private DelSpotGroupService delSpotGroupService;

    @RequestMapping(value = "spotGroupDel")
    public String delMenu(@RequestParam("id") long id, HttpServletRequest request){
        try{
            delSpotGroupService.delSpotGroup(id);
            return "welcome";
        } catch (Exception e){
            super.outputException(request, e, log, "删除学习中心分组");
            return "error";
        }
    }
}
