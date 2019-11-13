package com.model2.mvc.common.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HttpUtil {
	
	///Field
	
	///Constructor
	
	///Method
	public static void forward	(	HttpServletRequest request , 
														HttpServletResponse response, 
														String path){
		try{
			System.out.println("[ forward ]");
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			System.out.println("////////////////////////////////////////");
		}catch(Exception ex){
			System.out.println("forward ���� : " + ex);
			throw new RuntimeException("forward ���� : " + ex);
		}
	}
	
	public static void redirect( HttpServletResponse response , String path ){
		try{
			System.out.println("[ redirect ]");
			response.sendRedirect(path);
			System.out.println("////////////////////////////////////////");

		}catch(Exception ex){
			System.out.println("redirect ���� : " + ex);
			throw new RuntimeException("redirect ����  : " + ex);
		}
	}
}