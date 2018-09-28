package com.zs.web.controller.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.basic.setteachmaterialtm.FindSetTeachMaterialTMByCourseCodeDAO;
import com.zs.dao.basic.setteachmaterialtm.FindSetTeachMaterialTMByTMIdDAO;
import com.zs.dao.basic.teachmaterialcourse.FindTeachMaterialCourseByTMIdDAO;
import com.zs.domain.basic.SetTeachMaterialTM;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialCourse;
import com.zs.service.basic.teachmaterial.FindTeachMaterialPageByWhereService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/11.
 */
@Controller
@RequestMapping(value = "/findTeachMaterialByNameOrCourseCode")
public class FindTeachMaterialByNameOrCourseCodeController extends LoggerController {

    private static Logger log = Logger.getLogger(FindTeachMaterialByNameOrCourseCodeController.class);

    @Resource
    private FindTeachMaterialPageByWhereService findTeachMaterialPageByWhereService;
    @Resource
    private FindTeachMaterialCourseByTMIdDAO findTeachMaterialCourseByTMIdDAO;
    @Resource
    private FindSetTeachMaterialTMByTMIdDAO findSetTeachMaterialTMByTMIdDAO;

    @RequestMapping(value = "find")
    @ResponseBody
    public JSONObject find(@RequestParam(value="name", required=false, defaultValue="") String name,
                           @RequestParam(value="courseCode", required=false, defaultValue="") String courseCode,
                           HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("state", TeachMaterial.STATE_ENABLE+"");
            params.put("courseCode", courseCode);
            PageInfo pageInfo = getPageInfo(request);
            pageInfo.setCountOfCurrentPage(9999);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("t.operate_time", false);
            pageInfo = findTeachMaterialPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            List<JSONObject> returnList = new ArrayList<JSONObject>();
            if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
                List list = pageInfo.getPageResults();
                for(int i=0; i<list.size(); i++){
                    JSONObject json = (JSONObject) list.get(i);
                    long tmId = Long.parseLong(json.get("id").toString());
                    List<TeachMaterialCourse> teachMaterialCourseList = findTeachMaterialCourseByTMIdDAO.findTeachMaterialCourseByTMId(tmId);
                    List<SetTeachMaterialTM> setTeachMaterialTMList = findSetTeachMaterialTMByTMIdDAO.find(tmId);
                     if((null != teachMaterialCourseList && 0 < teachMaterialCourseList.size()) ||
                             (null != setTeachMaterialTMList && 0 < setTeachMaterialTMList.size())){
                        returnList.add(json);
                    }
                 }
            }

            jsonObject.put("state", 0);
            jsonObject.put("resultList", returnList);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "查询教材");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
