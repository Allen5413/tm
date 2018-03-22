package com.zs.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.tools.HttpRequestTools;
import com.zs.tools.ZsUrlSplice;

@Controller
@RequestMapping(value = "/getZsDataController")
public class GetZsDataController {
	
	@RequestMapping(value = "doGetData",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String doGetData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String result = HttpRequestTools.sendGet("http://www.attop.com/dangport/mod.do" + ZsUrlSplice.splieceGetUserUrl(request.getParameter("zz")));
		return result;
	}
}
