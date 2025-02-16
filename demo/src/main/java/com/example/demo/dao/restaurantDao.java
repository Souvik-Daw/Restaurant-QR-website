package com.example.demo.dao;

public interface restaurantDao {

	public String test();
	
	public String register(String userName,String pswd,String companyId,String email,
						   String totalTables,String bankAccountNumber,String ifscCode,
						   String gstNumber,String address,String phoneNumber);
	
	public String login(String email,String pswd);
	
	
}
