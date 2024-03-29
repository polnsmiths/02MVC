package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RequestMapping {
	
	///Field
	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	///Constructor
	private RequestMapping(String resources) {
		
		System.out.println("-----RequestMapping 실행-------");

		map = new HashMap<String, Action>();
		
		InputStream in = null;
		try{
			in = getClass().getClassLoader().getResourceAsStream(resources);
			properties = new Properties();
			properties.load(in);
			
			System.out.println("------RequestMapping 종료------");

		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
		}finally{
			if(in != null){
				try{ 
					in.close(); 
				} catch(Exception ex){ }
			}
		}
	} 
	
	///Method
	public synchronized static RequestMapping getInstance(String resources){
		System.out.println("-----RequestMapping getInstance-----");
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		System.out.println("------RequestMapping getInstance-----");

		return requestMapping;
	}
	
	public Action getAction(String path){
		System.out.println("-----getAction 실행-----");
		Action action = map.get(path);
		
		if(action == null){
			
			String className = properties.getProperty(path);
			//System.out.println("prop : " + properties);
			System.out.println("path : " + path);			
			System.out.println("className : " + className);
			className = className.trim();
			
			try{
				Class c = Class.forName(className);
				Object obj = c.newInstance();
				if(obj instanceof Action){
					map.put(path, (Action)obj);
					action = (Action)obj;
					System.out.println("-----getAction 종료-----");

				}else{
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action;
	}
}