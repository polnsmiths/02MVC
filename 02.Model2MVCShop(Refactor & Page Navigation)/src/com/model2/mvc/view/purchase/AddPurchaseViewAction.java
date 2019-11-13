package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseViewAction extends Action {

	public AddPurchaseViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseAction===");
		
	    int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	    System.out.println("prodNo:"+prodNo);
	    
	    ProductService productService = new ProductServiceImpl();
	    Product product = productService.getProduct(prodNo);
	    
	    request.setAttribute("product", product);

		System.out.println("===AddPurchaseAction");
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
