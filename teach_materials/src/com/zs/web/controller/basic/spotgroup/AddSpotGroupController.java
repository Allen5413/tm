package com.zs.web.controller.basic.spotgroup;

import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.AddSpotGroupService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/3.
 */
@Controller
@RequestMapping(value = "/addSpotGroup")
public class AddSpotGroupController extends
        LoggerController<SpotGroup, AddSpotGroupService> {
    private static Logger log = Logger.getLogger(AddSpotGroupController.class);

    @Resource
    private AddSpotGroupService addSpotGroupService;

    @RequestMapping(value = "openAddSpotGroup")
    public String openAddSpotGroup(){
        return "spotGroup/spotGroupAdd";
    }

    @RequestMapping(value = "spotGroupAdd")
    public String addSpotGroup(HttpServletRequest request, SpotGroup spotGroup){
        try{
            if(null != spotGroup) {
                addSpotGroupService.addSpotGroup(spotGroup, UserTools.getLoginUserForName(request));
            }
            return "welcome";
        }catch(Exception e){
            super.outputException(request, e, log, "新增学习中心分组");
            return "error";
        }
    }
}
