package com.myStyle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myStyle.entity.User;
import com.myStyle.service.UserService;
import com.myStyle.util.Common;
import com.myStyle.util.CookieUtils;
import com.myStyle.util.Md5Tool;



@Controller
@RequestMapping("/LoginSys/")
public class LoginSysController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	private final static Logger log = Logger
			.getLogger(LoginSysController.class);
	
	/**
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model, HttpServletRequest request) {
		log.info("enter LoginSysController类  login函数");
		// 重新登录时销毁该用户的Session
		Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (null != o) {
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		log.info("exit LoginSysController类  login函数");
		return "/background/framework/login";
	}

	@RequestMapping("timeOut")
	public String timeOut() {
		log.info("enter LoginSysController类  timeOut函数");
		return "/background/framework/timeOut";
	}
	
	@RequestMapping("loginCheck")	
	public String loginCheck(String username, String password,
			HttpServletRequest request,HttpServletResponse response, Model model) {
		log.info("enter LoginSysController类  loginCheck函数");
		User users = null;		
		try {
			request.getSession().setAttribute("userName", username);
			
			
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error","支持POST方法提交！");
			}
			if (Common.isEmpty(username) || Common.isEmpty(password)) {
				request.setAttribute("error","用户名或密码不能为空！");
				return "/background/framework/login";
			}
			// 验证用户账号与密码是否正确
			users =userService.querySingleUser1(username);			
			if (users == null || !users.getPassWord().equals(Md5Tool.getMD5Code(password))) {
				request.setAttribute("error", "用户名或密码不正确！");
			    return "/background/framework/login";
			    
			}
			else{ 
				String rememberMe = request.getParameter("loginkeeping");
				// 判断是不是勾选了“记住我”功能
				if (rememberMe != null) {
					// 把用户名和md5加密过的密码放入cookie,cookie默认保存7天
					CookieUtils.setCookie(request, response, "username", username, 7 * 24 * 60 * 60, true);
					CookieUtils.setCookie(request, response, "psw",users.getPassWord(), 7 * 24 * 60 * 60, true);
				}
				
				Authentication authentication = myAuthenticationManager
    					.authenticate(new UsernamePasswordAuthenticationToken(username,Md5Tool.getMD5Code(password)));
    			SecurityContext securityContext = SecurityContextHolder.getContext();
    			securityContext.setAuthentication(authentication);
    			HttpSession session = request.getSession(true);  
    		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    		    // 当验证都通过后，把用户信息放在session里				
    			request.getSession().setAttribute("userSession", users);    			
    			request.getSession().setAttribute("id", users.getId());   			      			
    			log.info("exit LoginSysController类  loginCheck函数");
    			return  "/background/framework/welcome"; 
			}
		} catch (Exception ae) {  
			request.setAttribute("error", "登录异常，请联系管理员！");
			log.error("error LoginSysController类  loginCheck函数"+ae.toString());
		    return "/background/framework/login";
		}	
	}

}
