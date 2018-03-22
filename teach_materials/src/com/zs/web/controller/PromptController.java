package com.zs.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页各种消息的提醒
 * Created by Allen on 2015/5/16.
 */
@Controller
@RequestMapping(value = "/prompt")
public class PromptController extends LoggerController {
    private static Logger log = Logger.getLogger(PromptController.class);

    @RequestMapping("openPromptPage")
    public String openPromptPage(HttpServletRequest request){
        return "prompt";
    }
}
