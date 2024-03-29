package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class UpdateUserViewAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("===[ UpdateUserViewAction ����]===");

		String userId=request.getParameter("userId");
		
		UserService userService=new UserServiceImpl();
		User user=userService.getUser(userId);
		
		request.setAttribute("user", user);
		System.out.println("===[ UpdateUserViewAction ����]===");

		return "forward:/user/updateUser.jsp";
	}
}