package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDao {
	
	public ProductDao() {
		
	}
	
	public void insertProduct(Product product) throws Exception{
		
		System.out.println("===<insertProduct>===");
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into PRODUCT values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setInt(2, product.getPrice());
		stmt.setString(3, product.getProdDetail());
		String ManuDate =  product.getManuDate();
		String[] str = ManuDate.split("-");
		String SplitManuDate = "";
		for (int i = 0; i < str.length; i++) {
			//System.out.println(str[i]);
			SplitManuDate += str[i];
		}
		System.out.println(str.length);
		System.out.println(product.getProdName());
		System.out.println(product.getPrice());
		System.out.println(product.getProdDetail());
		System.out.println("ManuDate: "+ManuDate);
		SplitManuDate = SplitManuDate.trim();
		System.out.println(SplitManuDate);
		
		stmt.setString(4, SplitManuDate);
		stmt.setString(5, product.getFileName());
		System.out.println(product.getFileName());
		stmt.executeUpdate();
		
		System.out.println("==<insertProduct 종료>==");
		con.close();
		
	}
	
	public Product findProduct(int prodNo) throws Exception {
		
		System.out.println("==<findProduct>==");
		Connection con = DBUtil.getConnection();

		String sql = "select"
				+ " prod_no, prod_name, image_file, prod_detail, MANUFACTURE_DAY, PRICE, REG_DATE"
				+ "  from PRODUCT where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		System.out.println("prodNo: "+prodNo);

		ResultSet rs = stmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			System.out.println("PROD_NO : "+rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			System.out.println("PROD_NAME: "+rs.getString("PROD_NAME"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			System.out.println("IMAGE_FILE: "+rs.getString("IMAGE_FILE"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			System.out.println("PROD_DETAIL: "+rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			System.out.println("MANUFACTURE_DAY: "+rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			System.out.println("PRICE: "+rs.getInt("PRICE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			System.out.println("REG_DATE : "+rs.getDate("REG_DATE"));
		}
		System.out.println("==<findProduct 종료>==");

		rs.close();
		stmt.close();
		con.close();
		
		return product;
	}
	
	public void updateProduct(Product product) throws Exception{
		
		System.out.println("==<updateProduct>==");

		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set Prod_name=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());
		stmt.executeUpdate();
		System.out.println(product.getProdName());
		
		System.out.println("==<updateProduct 종료>==");

		con.close();
		
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception{
		
		System.out.println("==<getProductList>==");
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT  prod_no, Prod_name, price, reg_date  FROM  product ";
		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE prod_no = '" + search.getSearchKeyword()+"'";
			}if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE prod_name ='" + search.getSearchKeyword()+"'";
			}else if( search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("") ){
				sql += " WHERE price ='" + search.getSearchKeyword()+"'";
			}
		}
		sql += " ORDER BY prod_name";
		
		System.out.println("ProductDao ::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("Product :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println("search: "+search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			list.add(product);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		System.out.println("==<getProductList 종료>==");
		return map;
		
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		System.out.println(">>>>>>>[ getTotalCount ]<<<<<<<");

		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		
		System.out.println(">>>>>>>[ makeCurrentPageSql ]<<<<<<<");

		
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("Product :: make SQL :: "+ sql);	
		
		return sql;
	}

}

   
