package com.zs.web.controller.basic.issuechannel;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.AddIssueChannelService;
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
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/addIssueChannel")
public class AddIssueChannelController extends LoggerController<IssueChannel, AddIssueChannelService> {
    private static Logger log = Logger.getLogger(AddIssueChannelController.class);

    @Resource
    private AddIssueChannelService addIssueChannelService;

    @RequestMapping(value = "openAddIssueChannel")
    public String openAddIssueChannel(){
        return "issueChannel/channelAdd";
    }

    @RequestMapping(value = "issueChannelAdd")
    @ResponseBody
    public JSONObject addIssueChannel(HttpServletRequest request, IssueChannel issueChannel){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != issueChannel) {
                addIssueChannelService.addIssueChannel(issueChannel, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "新增发行渠道");
            jsonObject.put("msg", msg);
            jsonObject.put("state", 1);
        }
        return jsonObject;
    }
}
