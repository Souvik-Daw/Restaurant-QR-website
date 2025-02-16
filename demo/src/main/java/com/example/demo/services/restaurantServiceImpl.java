package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.menu;
import com.example.demo.dao.restaurantDao;;

@Service("restaurantService")
public class restaurantServiceImpl implements restaurantService{
	
	@Autowired
	restaurantDao restaurantDao;

	@Override
	public String test() {
		return restaurantDao.test();
	}

	@Override
	public String register(String userName, String pswd, String companyId, String email, String totalTables,
			String bankAccountNumber, String ifscCode, String gstNumber, String address, String phoneNumber) {
		return restaurantDao.register(userName, pswd, companyId, email, totalTables, 
				bankAccountNumber, ifscCode, gstNumber, address, phoneNumber);
	}

	@Override
	public String login(String email, String pswd) {
		return restaurantDao.login(email, pswd);
	}

	@Override
	public String createMenu(String itemName, String itemCode, String itemStatus, String itemPrice, String category,
			String companyId, String effectiveFromDate) {
		return restaurantDao.createMenu(itemName, itemCode, itemStatus, itemPrice, category, companyId, effectiveFromDate);
	}

	@Override
	public String updateMenu(int id,String itemName, String itemCode, String itemStatus, String itemPrice, String category,
			String companyId, String effectiveFromDate, String delFlag) {
		return restaurantDao.updateMenu(id,itemName, itemCode, itemStatus, itemPrice, category, companyId, effectiveFromDate, delFlag);
	}

	@Override
	public List<menu> readMenu(String CompanyId) {
		return restaurantDao.readMenu(CompanyId);
	}

}
