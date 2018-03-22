package com.zs.web.controller.orderbook.teachmaterialratio;

import com.alibaba.fastjson.JSONObject;
import com.zs.domain.basic.Semester;
import com.zs.domain.orderbook.TeachMaterialRatio;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.orderbook.teachmaterialratio.AddTeachMaterialRatioService;
import com.zs.service.orderbook.teachmaterialratio.FindTeachMaterialRatioBySemesterIdService;
import com.zs.service.orderbook.teachmaterialratio.FindTotalPriceBySemesterService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2015/5/5.
 */
@Controller
@RequestMapping(value = "/addTeachMaterialRatio")
public class AddTeachMaterialRatioController extends
        LoggerController<TeachMaterialRatio, AddTeachMaterialRatioService> {
    private static Logger log = Logger.getLogger(AddTeachMaterialRatioController.class);

    @Resource
    private AddTeachMaterialRatioService addTeachMaterialRatioService;
    @Resource
    private FindTeachMaterialRatioBySemesterIdService findTeachMaterialRatioBySemesterIdService;
    @Resource
    private FindTotalPriceBySemesterService findTotalPriceBySemesterService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "openAddTeachMaterialRatioPage")
    public String openAddTeachMaterialRatioPage(HttpServletRequest request){
        //查询当前学期自动生成订单信息
        Semester semester = findNowSemesterService.getNowSemester();
        TeachMaterialRatio teachMaterialRatio = findTeachMaterialRatioBySemesterIdService.getTeachMaterialRatioBySemesterId(semester.getId());
        //计算总码洋
        String totalPrice = findTotalPriceBySemesterService.getTotalPriceBySemester(semester.getId());
        request.setAttribute("teachMaterialRatio", teachMaterialRatio);
        request.setAttribute("totalPrice", totalPrice);
        return "orderBook/teachMaterialRatioCreate";
    }


    @RequestMapping(value = "teachMaterialRatioAdd")
    @ResponseBody
    public JSONObject addTeachMaterialRatio(HttpServletRequest request, HttpServletResponse response, TeachMaterialRatio teachMaterialRatio){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != teachMaterialRatio) {
                addTeachMaterialRatioService.addTeachMaterialRatio(teachMaterialRatio, UserTools.getLoginUserForName(request));
                jsonObject.put("teachMaterialRatio", teachMaterialRatio);

                //查询当前学期自动生成订单信息
                Semester semester = findNowSemesterService.getNowSemester();
                //计算总码洋
                String totalPrice = findTotalPriceBySemesterService.getTotalPriceBySemester(semester.getId());
                jsonObject.put("totalPrice", totalPrice);
            }
            jsonObject.put("state", 0);
        }
        catch(Exception e){
            String msg = super.outputException(request, e, log, "生成采购单");
            jsonObject.put("msg", msg);
            jsonObject.put("state", 1);
        }
        return jsonObject;
    }
}
