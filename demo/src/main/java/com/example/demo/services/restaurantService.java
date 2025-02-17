package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.bean.alert;
import com.example.demo.bean.bill;
import com.example.demo.bean.coupon;
import com.example.demo.bean.customerReview;
import com.example.demo.bean.inventory;
import com.example.demo.bean.menu;
import com.example.demo.bean.orderDetails;
import com.example.demo.bean.orders;
import com.example.demo.bean.restaurant;

public interface restaurantService {
	
	public String test();
	
	public String register(String userName,String pswd,String companyId,String email,
			   String totalTables,String bankAccountNumber,String ifscCode,
			   String gstNumber,String address,String phoneNumber);

	public String login(String email,String pswd);
	
	public String createMenu(String itemName,String itemCode,String itemStatus,String itemPrice,String category,String companyId,String effectiveFromDate);
	
	public String updateMenu(int id,String itemName,String itemCode,String itemStatus,String itemPrice,String category,String companyId,String effectiveFromDate,String delFlag);
	
	public List<menu> readMenu(String CompanyId);
	
	public String createOrder(orders orders);
	
	public String updateOrder(orders orders);
	
	public List<orders> readOrder(orders orders);
	
	public String updateUserProfile(restaurant restaurant);
	
	public String createCustomerReview(customerReview customerReview);
	
	public String updateCustomerReview(customerReview customerReview);
	
	public List<customerReview> readCustomerReview(customerReview customerReview);
	
	public String createInventory(inventory inventory);
	
	public String updateInventory(inventory inventory);
	
	public List<inventory> readInventory(inventory inventory);
	
	public String createBill(bill bill);
	
	public String updateBill(bill bill);
	
	public List<bill> readBill(bill bill);
	
	public String createCoupon(coupon coupon);
	
	public String updateCoupon(coupon coupon);
	
	public List<coupon> readCoupon(coupon coupon);
	
	public String createAlert(alert alert);
	
	public String updateAlert(alert alert);
	
	public List<alert> readAlert(alert alert);
	
	public List<orders> totalSales(orderDetails orderDetails);
	
	public String aov(orderDetails orderDetails);
	
	public Map<String,List<String>> bestLeastSellingDish(orderDetails orderDetails);

}
