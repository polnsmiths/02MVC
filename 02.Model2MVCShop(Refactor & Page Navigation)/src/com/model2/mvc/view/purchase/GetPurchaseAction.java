package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class GetPurchaseAction extends Action {

	public GetPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetPurchaseAction===");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        PurchaseService purchaseService = new PurchaseServiceImpl();
        Purchase purchase =  purchaseService.getPurchase(tranNo);
        
        System.out.println("µð¹ö±ë µð¹ö±ë µð¹ö±ë......");
        User user = purchase.getBuyer();
        System.out.println(user);
        Product product = purchase.getPurchaseProd();
        System.out.println(product);
        
        request.setAttribute("purchase", purchase);
		
		System.out.println("===GetPurchaseAction");
		return "forward:/purchase.getPurchase.jsp";
	}

}
