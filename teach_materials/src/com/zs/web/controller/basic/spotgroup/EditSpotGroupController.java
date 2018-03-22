package com.zs.web.controller.basic.spotgroup;

import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.EditSpotGroupService;
import com.zs.tools.UserTools;
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
@RequestMapping(value = "/editSpotGroup")
public class EditSpotGroupController extends
        LoggerController<SpotGroup, EditSpotGroupService> {
    private static Logger log = Logger.getLogger(EditSpotGroupController.class);

    @Resource
    private EditSpotGroupService editSpotGroupService;

    @RequestMapping(value = "openEditSpotGroupPage")
    public String openEditMenuPage(@RequestParam("id") long id, HttpServletRequest request){
        SpotGroup spotGroup = editSpotGroupService.get(id);
        request.setAttribute("spotGroup", spotGroup);
        return "user/userAdd";
    }

    @RequestMapping(value = "spotGroupEdit")
    public String editMenu(HttpServletRequest request, SpotGroup spotGroup){
        try{
            if(null != editSpotGroupService) {
                editSpotGroupService.editSpotGroup(spotGroup, UserTools.getLoginUserForName(request));
            }
            return "welcome";
        }catch(Exception e){
            super.outputException(request, e, log, "编辑学习中心分组");
            return "error";
        }
    }
}
