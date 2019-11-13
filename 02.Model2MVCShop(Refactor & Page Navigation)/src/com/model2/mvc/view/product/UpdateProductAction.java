package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("======[ UpdateProductAction 실행]======");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("prodNo: "+request.getParameter("prodNo"));
		
		Product product = new Product();
		product.setProdName(request.getParameter("prodName"));
		System.out.println("prodName: "+request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println("manuDate: "+request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		ProductService productService = new ProductServiceImpl();
		productService.updateProduct(product);
		
		//HttpSession session=request.getSession();
		//int prodNo = Integer.parseInt(request.getParameter("prodNo"));
//		int sessionId=((Product)session.getAttribute("product")).getProdNo();
//		System.out.println("sessionId : "+sessionId);
//		System.out.println("prodNo : "+prodNo);
//		if(sessionId == prodNo){
//			session.setAttribute("product", product);
//		}

		System.out.println("======[ UpdateProductAction 종료]======");

		return "forward:/updateProductView.do?prodNo="+prodNo;
		//return "forward:/product/updateProduct.jsp";
	}

}
