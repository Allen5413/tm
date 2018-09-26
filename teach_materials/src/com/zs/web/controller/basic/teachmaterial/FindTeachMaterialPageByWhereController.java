package com.zs.web.controller.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.Press;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialType;
import com.zs.service.basic.press.FindPressService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialPageByWhereService;
import com.zs.service.basic.teachmaterialtype.FindTeachMaterialTypeService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/4/29.
 */
@Controller
@RequestMapping(value = "/findTeachMaterialPage")
public class FindTeachMaterialPageByWhereController extends LoggerController {

    private static Logger log = Logger.getLogger(FindTeachMaterialPageByWhereController.class);

    @Resource
    private FindTeachMaterialPageByWhereService findTeachMaterialPageByWhereService;
    @Resource
    private FindTeachMaterialTypeService findTeachMaterialTypeService;
    @Resource
    private FindPressService findPressService;

    @RequestMapping(value = "findTeachMaterialPageByWhere")
    public String FindPageByWhere(@RequestParam(value="isbn", required=false, defaultValue="") String isbn,
                                  @RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="typeId", required=false, defaultValue="") String typeId,
                                  @RequestParam(value="pressId", required=false, defaultValue="") String pressId,
                                  @RequestParam(value="state", required=false, defaultValue="") String state,
                                  @RequestParam(value="courseCode", required=false, defaultValue="") String courseCode,
                                  @RequestParam(value = "isGlCourse", required = false, defaultValue = "") String isGlCourse,
                                  HttpServletRequest request) {
        try {
            //获取教材类型
            List<TeachMaterialType> teachMaterialTypeList = findTeachMaterialTypeService.getAll();
            //获取出版社
            List<Press> pressList = findPressService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("isbn", isbn.trim());
            params.put("name", name.trim());
            params.put("typeId", typeId);
            params.put("pressId", pressId);
            params.put("state", state);
            params.put("courseCode", courseCode);
            params.put("isGlCourse", isGlCourse);
            PageInfo pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("t.operate_time", false);
            pageInfo = findTeachMaterialPageByWhereService.findPageByWhere(pageInfo, params, sortMap);

            request.setAttribute("teachMaterialTypeList", teachMaterialTypeList);
            request.setAttribute("pressList", pressList);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询教材信息");
            return "error";
        }
        return "teachMaterial/tmList";
    }
}
