package com.zs.web.controller.sync.spot;

import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotByProvCodeService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 查询省公司下的学习中心
 * Created by Allen on 2015/6/1.
 */
@Controller
@RequestMapping(value = "/findSpotByProvCode")
public class FindSpotListByProvCodeController extends
        LoggerController<Spot, FindSpotByProvCodeService> {

    private static Logger log = Logger.getLogger(FindSpotPageByWhereController.class);

    @Resource
    public FindSpotByProvCodeService findSpotByProvCodeService;

    @RequestMapping(value = "doFindSpotByProvCode")
    @ResponseBody
    public JSONObject FindPageByWhere(@RequestParam(value="provCode", required = false, defaultValue = "") String provCode,
                                  HttpServletRequest request) {
        JSONObject result = new JSONObject();
        JSONArray spotArray = null;
        try {
            spotArray = findSpotByProvCodeService.getSpotForJSONByProvCode(provCode);
            result.put("spotArray", spotArray);
            result.put("state", 0);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "查询省中心下的学习中心信息");
            result.put("msg", msg);
            result.put("state", 1);
        }
        return result;
    }
}
