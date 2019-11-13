package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class UpdateUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		System.out.println("====[ UpdateUserAction ����]===");

		String userId=(String)request.getParameter("userId");
		
		User user=new User();
		user.setUserId(userId); 
		user.setUserName(request.getParameter("userName"));
		user.setAddr(request.getParameter("addr"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		//���� �Է��� ���� user�� ��Ƽ�
		UserService userService=new UserServiceImpl();
		userService.updateUser(user);
		//updateUser ������ �� ��� DB�� �ְ��.
		
		HttpSession session=request.getSession();
		String sessionId=((User)session.getAttribute("user")).getUserId();
		System.out.println("sessionId : "+sessionId);
		System.out.println("userId : "+userId);
		if(sessionId.equals(userId)){
			session.setAttribute("user", user);
		}
		System.out.println("====[ UpdateUserAction ����]===");

		return "redirect:/getUser.do?userId="+userId;
	}
}