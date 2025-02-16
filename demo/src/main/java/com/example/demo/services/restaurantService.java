package com.example.demo.services;

import java.util.List;

import com.example.demo.bean.menu;

public interface restaurantService {
	
	public String test();
	
	public String register(String userName,String pswd,String companyId,String email,
			   String totalTables,String bankAccountNumber,String ifscCode,
			   String gstNumber,String address,String phoneNumber);

	public String login(String email,String pswd);
	
	public String createMenu(String itemName,String itemCode,String itemStatus,String itemPrice,String category,String companyId,String effectiveFromDate);
	
	public String updateMenu(int id,String itemName,String itemCode,String itemStatus,String itemPrice,String category,String companyId,String effectiveFromDate,String delFlag);
	
	public List<menu> readMenu(String CompanyId);

}
