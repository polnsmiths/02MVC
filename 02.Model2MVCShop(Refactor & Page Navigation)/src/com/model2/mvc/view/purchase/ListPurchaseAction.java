package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ListPurchaseAction===");
		
		String buyerId = (String)request.getParameter("buyerId");
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.getPurchaseList(search);
		System.out.println("===ListPurchaseAction");
		return null;
	}

}
