package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseAction===");

		/////product정보 가져오기///////////////
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		ProductService productService = new ProductServiceImpl();
		Product product = productService.getProduct(prodNo);
		product.setProTranCode("1");
		////Session에서 User정보 가져오기////////////
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		///////purchase에 ::product와 user정보 담아주기+입력한정보 담아주기///////
		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDlvyAddr(request.getParameter("receiverAddr"));
		purchase.setDlvyRequest(request.getParameter("receiverRequest"));
		purchase.setDlvyDate(request.getParameter("receiverDate"));
		
		//purchase.setPurchaseProd(prodNo);
		PurchaseService purchaseService =new PurchaseServiceImpl();
		purchaseService.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		System.out.println("===AddPurchaseAction");
		return "forward:/purchase/addPurchase.jsp";
	}

}
