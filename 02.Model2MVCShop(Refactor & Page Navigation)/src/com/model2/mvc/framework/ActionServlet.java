package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	///Field
	private RequestMapping requestMapping;
	
	///Method
	@Override
	public void init() throws ServletException {
		System.out.println(">=============INIT() 실행====================<");
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		System.out.println("resources : "+resources);
		requestMapping=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																						throws ServletException, IOException {
		System.out.println("////////////////////////////////////////////");
		System.out.println(">==========SERVICE() 실행================<");
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String reqeustPath = url.substring(contextPath.length());
		System.out.println("\nActionServlet.service() RequestURI : "+reqeustPath);
		
		try{
			Action action = requestMapping.getAction(reqeustPath);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);
			String path=resultPage.substring(resultPage.indexOf(":")+1);
			System.out.println("resultPage :: "+resultPage);
			System.out.println("path :: "+path);
			System.out.println(">===========SERVICE() 종료===============<");

			if(resultPage.startsWith("forward:")){
				HttpUtil.forward(request, response, path);
			}else{
				HttpUtil.redirect(response, path);

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}