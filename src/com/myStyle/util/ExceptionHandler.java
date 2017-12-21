package com.myStyle.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	private final static Logger log=Logger.getLogger(ExceptionHandler.class);
	public ModelAndView resolveException( HttpServletRequest request, 
	        HttpServletResponse response, 
	        Object handler, 
	        Exception ex) {
		// TODO Auto-generated method stub
			log.error("Catch Exception: ",ex);//把漏网的异常信息记入日志  
	        Map<String,Object> map = new HashMap<String,Object>();  
	        StringPrintWriter strintPrintWriter = new StringPrintWriter();  
	        ex.printStackTrace(strintPrintWriter);  
	        map.put("errorMsg", strintPrintWriter.getString());//将错误信息传递给view  
	        return new ModelAndView("redirect:/error.jsp");  
	}

}
