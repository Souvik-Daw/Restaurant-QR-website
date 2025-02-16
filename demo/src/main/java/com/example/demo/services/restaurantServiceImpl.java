package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
