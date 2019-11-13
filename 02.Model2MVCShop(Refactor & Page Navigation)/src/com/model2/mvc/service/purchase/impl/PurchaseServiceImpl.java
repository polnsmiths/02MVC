package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServiceImpl implements PurchaseService {

	PurchaseDao purchaseDao = new PurchaseDao();
	
	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.findPurchase(tranNo);

	}

	@Override
	public Map<String, Object> getPurchaseList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getSaleList(String tranCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);

	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
