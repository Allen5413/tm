//package com.zs.web.controller.bank;
//
//import com.zs.epaysdk.util.Signature;
//import com.zs.service.bank.notify.BankNotifyService;
//import com.zs.web.controller.LoggerController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Created by Allen on 2015/10/28.
// */
//@Controller
//@RequestMapping(value = "/bankNotify")
//public class BankNotifyController extends LoggerController {
//
//    private static Logger log = Logger.getLogger(BankNotifyController.class);
//
//    @Resource
//    private BankNotifyService bankNotifyService;
//
//    @RequestMapping(value = "notify")
//    public String notify(HttpServletRequest request){
//        try{
//
//            String method = request.getMethod();
//            Map<String,String> params = new HashMap<String,String>();
//            Map<?, ?> reqParams = request.getParameterMap();
//            Iterator<?> iter = reqParams.keySet().iterator();
//            while (iter.hasNext()) {
//                String name = (String) iter.next();
//                String[] values = (String[]) reqParams.get(name);
//                if("get".equalsIgnoreCase(method))
//                    params.put(name, new String(values[0].getBytes("ISO-8859-1"), "UTF-8"));
//                else
//                    params.put(name, values[0]);
//            }
//            //验签成功
//            if(Signature.verifyMAC(params)) {
//                //前台通知
//                //商户可以在这边进行 [前台] 回调通知的业务逻辑处理
//                //注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
//                //前台跳转回来的通知，需要显示内容，如支付成功等
//                String result = bankNotifyService.notify(params, method, "true");
//                if ("get".equalsIgnoreCase(method)) {
//                    request.setAttribute("tranStat", result);
//                    return "studentPages/payResult";
//                }
//                //后台通知
//                if ("post".equalsIgnoreCase(method)) {
//                    return null;
//                }
//            }else{
//                //验签失败
//                //不应当进行业务逻辑处理，即把该通知当无效的处理
//                //商户可以在此记录日志等
//                bankNotifyService.notify(params, method, "false");
//            }
//        }catch(Exception e){
//            try {
//                String eTxt = e.toString()+ "\r\n";
//                StackTraceElement[] stackTraceElements = e.getStackTrace();
//                if(null != stackTraceElements){
//                    for(StackTraceElement stackTraceElement : stackTraceElements){
//                        eTxt += "\tat " + stackTraceElement + "\r\n";
//                    }
//                }
//                bankNotifyService.notify(null, "", eTxt);
//            } catch (Exception e1) {
//                super.outputException(request, e1, log, "缴费");
//                return "erroe";
//            }
//            super.outputException(request, e, log, "缴费");
//            return "erroe";
//        }
//        return null;
//    }
//}
