package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("======[ UpdateProductViewAction 실행]======");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		
		ProductService productService = new ProductServiceImpl();
		Product product = productService.getProduct(prodNo);
		System.out.println("prodNo: "+prodNo);
		request.setAttribute("product", product);
		
		System.out.println("======[ UpdateProductViewAction 종료]======");

		return "forward:/product/updateProductView.jsp";
		
	}

}
