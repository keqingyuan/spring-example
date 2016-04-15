package com.ftsafe.iccd.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;
import com.ftsafe.iccd.core.dao.impl.HuiPanTablesManagerDAOImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final static LogWrapper LOG = Log.get();
	
	@ModelAttribute
	public void setResponse(HttpServletResponse response) {
		// 设置Ajax跨域访问
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView admin(Model model, HttpSession session) {
		LOG.debug("Hi," + session.getId());
		String result = null;
		result = "空中充值测试接口";
		Map<String, String> map = new HashMap<String,String>();
		map.put("api", result);
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	@RequestMapping(value = "/create/project", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView createProject(Model model, HttpServletRequest request) {
		int code;
		String msg = null;
		// 获取参数token
		String token = request.getParameter("token");
		// 获取项目名称
		String projectName = request.getParameter("projectName");
		// 获取表名称
		String tableName = request.getParameter("tableName");
		// 状态
		int status = Integer.valueOf(request.getParameter("status"));
		
		if (!token.equals("keqingyuan")){
			msg = "fail access";
			code = 10001;
		}else{
			
			HuiPanTablesManagerDAOImpl hpmd = new HuiPanTablesManagerDAOImpl();
			int ret = hpmd.create(tableName, projectName, status);
			if (ret == 0){
				msg = "success";
				code = 9000; 
			}else{
				msg = "fail";
				code = 1000;
			}
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
		LOG.debug("code {} msg {}", code, msg);
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	@RequestMapping(value = "/modify/status/{token}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView modifyStatus(Model model) {
		String result = null;
		result = "空中充值测试接口";
		Map<String, String> map = new HashMap<String,String>();
		map.put("api", result);
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	@RequestMapping(value = "/view/huipan/{token}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView ViewHuipan(Model model, HttpSession session) {
		LOG.debug("Hi," + session.getId());
		String result = null;
		result = "空中充值测试接口";
		Map<String, String> map = new HashMap<String,String>();
		map.put("api", result);
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
}
