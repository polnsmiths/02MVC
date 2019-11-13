package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Purchase findPurchase(int tranNo)throws Exception{
		
		System.out.println("[ findPurchase]");
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT prod_no, buyer_id, payment_option, receiver_name, "
						+ " receiver_phone, demailaddr, dlvy_request, dlvy_date, order_data "
						+ " FROM transaction "
						+ " WHERE tran_no ="+tranNo;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		//User user = null;
		Purchase purchase = null;
		while(rs.next()) {
			purchase.setPurchaseProd( (Product)rs.getObject("prod_no"));
			purchase.setBuyer( (User)rs.getObject("buyer_id"));
			purchase.setPaymentOption(rs.getString("payment_option"));
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDlvyAddr(rs.getString("demailAddr"));
			purchase.setDlvyRequest(rs.getString("dlvy_request"));
			purchase.setDlvyDate(rs.getString("dlvy_date"));
			purchase.setOrderDate(rs.getDate("order_data"));
		}
		
		System.out.println("sql::"+sql);
		System.out.println("[ findPurchase END]");
		
		return purchase;
	}
	
	public void insertPurchase(Purchase purchase)throws Exception{
		
		System.out.println("[ insertPurchase ]");
		
		Connection con = DBUtil.getConnection();
		
		Product product = purchase.getPurchaseProd();
		User user = purchase.getBuyer();
		//product.getProdNo();
		String sql = "UPDATE transaction "
							+ " SET buyer_id=?, prod_no=?, payment_option=?, receiver_name=?, receiver_phone=?, "
							+ " demailAddr=?, dlvy_request=?, dlvy_date=?, tran_status_code=?"
							+ " WHERE prod_no = '"+product.getProdNo()+"'";
	    System.out.println("sql::"+sql);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1 ,user.getUserId());
		stmt.setInt(2, product.getProdNo());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDlvyAddr());
		stmt.setString(7, purchase.getDlvyRequest());
		stmt.setString(8, purchase.getDlvyDate());
		System.out.println("proTranCode::"+(product.getProTranCode() ).trim());
		stmt.setString(9, (product.getProTranCode() ).trim());
		
		stmt.executeUpdate();
		
		System.out.println("[ insertPurchase END ]");
	}

}
