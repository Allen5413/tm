package com.zs.web.controller.basic.issuechannel;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.web.AbstractJQueryEntityController;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.EditIssueChannelService;
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
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/editIssueChannel")
public class EditIssueChannelController extends LoggerController<IssueChannel, EditIssueChannelService> {
    private static Logger log = Logger.getLogger(EditIssueChannelController.class);

    @Resource
    private EditIssueChannelService editIssueChannelService;

    @RequestMapping(value = "openEditIssueChannelPage")
    public String openEditIssueChannelPage(@RequestParam("id") long id, HttpServletRequest request){
        IssueChannel issueChannel = editIssueChannelService.get(id);
        request.setAttribute("issueChannel", issueChannel);
        return "issueChannel/channelEdit";
    }

    @RequestMapping(value = "issueChannelEdit")
    @ResponseBody
    public JSONObject editIssueChannel(HttpServletRequest request, IssueChannel issueChannel){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != issueChannel) {
                editIssueChannelService.editIssueChannel(issueChannel, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改发行渠道");
            jsonObject.put("msg", msg);
            jsonObject.put("state", 1);
        }
        return jsonObject;
    }
}
