package com.ftsafe.iccd.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;

@Controller
public class BaseController {

	private final static LogWrapper LOG = Log.get();
	
	private final static String VIEW_INDEX = "index";
	private final static String VIEW_WARN = "warning";
	private HttpServletRequest request;  
    private HttpServletResponse response;  
    private HttpSession session;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    } 
    
	@RequestMapping(value="/")
	public String index(){
		LOG.info("成功访问到根路径。");
		return VIEW_INDEX;
	}
	@RequestMapping(value="/warning", method=RequestMethod.GET)
	public String warning() {
		return VIEW_WARN;
	}
}
