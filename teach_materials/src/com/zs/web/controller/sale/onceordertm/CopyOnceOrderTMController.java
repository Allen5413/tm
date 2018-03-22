package com.zs.web.controller.sale.onceordertm;

import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Student;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.sale.onceorder.FindOrderStudentForCopyService;
import com.zs.service.sale.onceordertm.CopyOnceOrderTMService;
import com.zs.service.sync.level.FindLevelService;
import com.zs.service.sync.spec.FindSpecService;
import com.zs.service.sync.student.FindStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/6/28.
 */
@Controller
@RequestMapping(value = "/copyOnceOrderTM")
public class CopyOnceOrderTMController extends LoggerController {

    private static Logger log = Logger.getLogger(CopyOnceOrderTMController.class);

    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindOrderStudentForCopyService findOrderStudentForCopyService;
    @Resource
    private FindSpecService findSpecService;
    @Resource
    private FindLevelService findLevelService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private CopyOnceOrderTMService copyOnceOrderTMService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request,
                       @RequestParam("id") long id,
                       @RequestParam("studentCode") String studentCode) {
        try {
            Student student = findStudentByCodeService.getStudentByCode(studentCode);
            request.setAttribute("id", id);
            request.setAttribute("spotCode", student.getSpotCode());
            request.setAttribute("specList", findSpecService.getAll());
            request.setAttribute("levelList", findLevelService.getAll());
        } catch (Exception e) {
            super.outputException(request, e, log, "查询学生信息");
            return "error";
        }
        return "onceOrder/copyOrder";
    }

    @RequestMapping(value = "find")
    @ResponseBody
    public JSONObject find(@RequestParam(value="copy_spotCode", required=false, defaultValue="") String spotCode,
            @RequestParam(value="copy_year", required=false, defaultValue="") String year,
            @RequestParam(value="copy_quarter", required=false, defaultValue="") String quarter,
            @RequestParam(value="copy_specCode", required=false, defaultValue="") String specCode,
            @RequestParam(value="copy_levelCode", required=false, defaultValue="") String levelCode,
            @RequestParam(value="copy_code", required=false, defaultValue="") String code,
            @RequestParam(value="copy_name", required=false, defaultValue="") String name,
            HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Semester semester = findNowSemesterService.getNowSemester();
            spotCode = StringUtils.isEmpty(spotCode) ? UserTools.getLoginUserForSpotCode(request) : spotCode;
            Map<String, String> params = new HashMap<String, String>();
            params.put("spotCode", spotCode);
            params.put("year", year);
            params.put("quarter", quarter);
            params.put("specCode", specCode);
            params.put("levelCode", levelCode);
            params.put("studentCode", code);
            params.put("name", name);
            params.put("semesterID", semester.getId()+"");
            List<JSONObject> list = findOrderStudentForCopyService.find(params);
            json.put("state", 0);
            json.put("list", list);
        } catch (Exception e) {
            String msg = super.outputException(request, e, log, "查询学生信息");
            json.put("state", 1);
            json.put("msg", msg);
        }
        return json;
    }

    @RequestMapping(value = "copy")
    @ResponseBody
    public JSONObject copy(HttpServletRequest request,
                           @RequestParam("id") long id,
                           @RequestParam("ids") String ids){
        JSONObject jsonObject = new JSONObject();
        try{
            copyOnceOrderTMService.copy(id, ids.split(","), UserTools.getLoginUserForName(request));
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "复制订单");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
