package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("======[ GetProductAction 실행]======");
		
		String menu = (String)request.getParameter("menu");
		//System.out.println("GetProductAction의 menu:"+menu);
		request.setAttribute("menu", menu);
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		ProductService productService = new ProductServiceImpl();
		Product product = productService.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		System.out.println("======[ GetProductAction 종료]======");

		//return "forward:/updateProductView.do?prodNo="+prodNo;
		return "forward:/product/updateProduct.jsp";
	}

}
