package com.zs.web.controller;

import com.zs.domain.basic.Semester;
import com.zs.domain.basic.UserGroupUser;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.basic.usergroupuser.FindUserGroupUserByUserNameService;
import com.zs.service.statis.FindIndexPageService;
import com.zs.tools.DateTools;
import com.zs.tools.UserTools;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/16.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends LoggerController {
    private static Logger log = Logger.getLogger(IndexController.class);

    @Resource
    private FindUserGroupUserByUserNameService findUserGroupUserByUserNameService;
    @Resource
    private FindIndexPageService findIndexPageService;
    @Resource
    private FindNowSemesterService findNowSemesterService;

    @RequestMapping(value = "main")
    public String index(HttpServletRequest request){
        try {
            //获取当前年月日星期
            String year = DateTools.getThisYear();
            String month = DateTools.getThisMonth();
            String day = DateTools.getThisDay();
            String week = DateTools.getThisWeek();
            request.setAttribute("year", year);
            request.setAttribute("month", month);
            request.setAttribute("day", day);
            request.setAttribute("week", week);
            request.setAttribute("loginName", UserTools.getLoginUserForLoginName(request));
            request.setAttribute("name", UserTools.getLoginUserForName(request));
            request.setAttribute("menu", UserTools.getLoginUserForMenu(request));
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "findTotal")
    @ResponseBody
    public JSONObject findTotal(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            //获取登录用户所属用户组
            List<UserGroupUser> userGroupUserList = findUserGroupUserByUserNameService.find(UserTools.getLoginUserForLoginName(request));
            /**
             * isShow 1：所有权限；2：只查财务；3只查订单
             */
            int isShow = 0, isFinace = 0, isOrder = 0;
            if(null != userGroupUserList && 0 < userGroupUserList.size()){
                for(UserGroupUser userGroupUser : userGroupUserList){
                    if(userGroupUser.getUserGroupId() == 1){
                        isShow = 1;
                        break;
                    }
                    if(userGroupUser.getUserGroupId() == 14){
                        isFinace = 1;
                    }
                    if(userGroupUser.getUserGroupId() == 15 || userGroupUser.getUserGroupId() == 17){
                        isOrder = 1;
                    }
                }
            }
            if(isShow == 0) {
                if (isFinace == 1 && isOrder == 1) {
                    isShow = 1;
                } else {
                    if (isFinace == 1) {
                        isShow = 2;
                    }
                    if (isOrder == 1) {
                        isShow = 3;
                    }
                }
            }

            Semester semester = findNowSemesterService.getNowSemester();
            int waitAuditCount = -1;
            int notPrintSpotOrderCount = -1;
            int notPrintStudentOrderCount = -1;
            int notPackageSpotOrderCount = -1;
            int notPackageStudentOrderCount = -1;
            int notPrintSpotOnceOrderCount = -1;
            int notPrintStudentOnceOrderCount = -1;
            int notPackageSpotOnceOrderCount = -1;
            int notPackageStudentOnceOrderCount = -1;
            int notPrintSpotPlaceOrderCount = -1;
            int notPackageSpotPlaceOrderCount = -1;
            if(isShow > 0){
                if(isShow == 1 || isShow == 2) {
                    waitAuditCount = findIndexPageService.findWaitAuditPayCount();
                }
                if(isShow == 1 || isShow == 3) {
                    notPrintSpotOrderCount = findIndexPageService.findNotPrintSpotOrderCount(semester.getId());
                    notPrintStudentOrderCount = findIndexPageService.findNotPrintStudentOrderCount(semester.getId());
                    notPackageSpotOrderCount = findIndexPageService.findNotPackageSpotOrderCount(semester.getId());
                    notPackageStudentOrderCount = findIndexPageService.findNotPackageStudentOrderCount(semester.getId());

                    notPrintSpotOnceOrderCount = findIndexPageService.findNotPrintSpotOnceOrderCount(semester.getId());
                    notPrintStudentOnceOrderCount = findIndexPageService.findNotPrintStudentOnceOrderCount(semester.getId());
                    notPackageSpotOnceOrderCount = findIndexPageService.findNotPackageSpotOnceOrderCount(semester.getId());
                    notPackageStudentOnceOrderCount = findIndexPageService.findNotPackageStudentOnceOrderCount(semester.getId());

                    notPrintSpotPlaceOrderCount = findIndexPageService.findNotPrintSpotPlaceOrderCount(semester.getId());
                    notPackageSpotPlaceOrderCount = findIndexPageService.findNotPackageSpotPlaceOrderCount(semester.getId());
                }
            }

            jsonObject.put("isShow", isShow);
            jsonObject.put("waitAuditCount", waitAuditCount);
            jsonObject.put("notPrintSpotOrderCount", notPrintSpotOrderCount);
            jsonObject.put("notPrintStudentOrderCount", notPrintStudentOrderCount);
            jsonObject.put("notPackageSpotOrderCount", notPackageSpotOrderCount);
            jsonObject.put("notPackageStudentOrderCount", notPackageStudentOrderCount);
            jsonObject.put("notPrintSpotOnceOrderCount", notPrintSpotOnceOrderCount);
            jsonObject.put("notPrintStudentOnceOrderCount", notPrintStudentOnceOrderCount);
            jsonObject.put("notPackageSpotOnceOrderCount", notPackageSpotOnceOrderCount);
            jsonObject.put("notPackageStudentOnceOrderCount", notPackageStudentOnceOrderCount);
            jsonObject.put("notPrintSpotPlaceOrderCount", notPrintSpotPlaceOrderCount);
            jsonObject.put("notPackageSpotPlaceOrderCount", notPackageSpotPlaceOrderCount);
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "首页统计");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
