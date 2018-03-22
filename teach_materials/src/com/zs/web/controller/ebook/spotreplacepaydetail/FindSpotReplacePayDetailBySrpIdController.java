package com.zs.web.controller.ebook.spotreplacepaydetail;

import com.zs.domain.ebook.SpotReplacePay;
import com.zs.service.ebook.spotreplacepay.FindSpotReplacePayByIdService;
import com.zs.service.ebook.spotreplacepaydetail.FindSpotReplacePayDetailBySrpIdService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2016/1/4.
 */
@Controller
@RequestMapping(value = "/findSpotReplacePayDetailBySrpId")
public class FindSpotReplacePayDetailBySrpIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindSpotReplacePayDetailBySrpIdController.class);

    @Resource
    private FindSpotReplacePayDetailBySrpIdService findSpotReplacePayDetailBySrpIdService;
    @Resource
    private FindSpotReplacePayByIdService findSpotReplacePayByIdService;


    @RequestMapping(value = "find")
    public String find(@RequestParam("id") long id, HttpServletRequest request){
        try{
            SpotReplacePay spotReplacePay = findSpotReplacePayByIdService.find(id);
            List<JSONObject> list = findSpotReplacePayDetailBySrpIdService.find(id);
            request.setAttribute("spotReplacePay", spotReplacePay);
            request.setAttribute("list", list);
            return "ebook/spotreplacepaydetail/detail";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询电子教材交费明细");
            return "error";
        }
    }
}
