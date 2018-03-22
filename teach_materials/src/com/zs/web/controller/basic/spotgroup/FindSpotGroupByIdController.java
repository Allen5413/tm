package com.zs.web.controller.basic.spotgroup;

import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.FindSpotGroupService;
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
@RequestMapping(value = "/findSpotGroup")
public class FindSpotGroupByIdController extends
        LoggerController<SpotGroup, FindSpotGroupService> {
    private static Logger log = Logger.getLogger(FindSpotGroupByIdController.class);

    @Resource
    private FindSpotGroupService findSpotGroupService;

    @RequestMapping(value = "findSpotGroupByID")
    public String findMenuByID(@RequestParam("id") long id, HttpServletRequest request){
        try{
            SpotGroup spotGroup = findSpotGroupService.get(id);
            request.setAttribute("spotGroup", spotGroup);
        }catch(Exception e){
            super.outputException(request, e, log, "查询学习中心分组明细");
        }
        return "user/userAdd";
    }
}
