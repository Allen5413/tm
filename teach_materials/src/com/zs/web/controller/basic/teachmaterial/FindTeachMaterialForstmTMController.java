package com.zs.web.controller.basic.teachmaterial;

import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialBystmIdService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialForSetService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
@Controller
@RequestMapping(value = "/findTeachMaterialForstmTM")
public class FindTeachMaterialForstmTMController extends LoggerController {
    private static Logger log = Logger.getLogger(FindTeachMaterialForstmTMController.class);

    @Resource
    private FindTeachMaterialForSetService findTeachMaterialForSetService;
    @Resource
    private FindTeachMaterialBystmIdService findTeachMaterialBystmIdService;

    @RequestMapping(value = "doFindTeachMaterialForstmTM")
    public String doFindTeachMaterialForstmTM(@RequestParam(value = "stmId") Long stmId,
                                      HttpServletRequest request){
        try{
            //获得套教材关联的教材
            List<TeachMaterial> stmTMList = findTeachMaterialBystmIdService.getTeachMaterialBystmId(stmId);
            //获取所有的是套教材的教材
            List<TeachMaterial> tmList = findTeachMaterialForSetService.getTeachMaterialForIsSet();

            request.setAttribute("stmTMList", stmTMList);
            request.setAttribute("tmList", tmList);
        }catch(Exception e){
            super.outputException(request, e, log, "查询套教材关联的教材信息");
        }
        return "teachMaterial/stmTMList";
    }
}
