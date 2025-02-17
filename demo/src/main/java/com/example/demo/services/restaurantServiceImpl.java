package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.alert;
import com.example.demo.bean.bill;
import com.example.demo.bean.coupon;
import com.example.demo.bean.customerReview;
import com.example.demo.bean.inventory;
import com.example.demo.bean.menu;
import com.example.demo.bean.orderDetails;
import com.example.demo.bean.orders;
import com.example.demo.bean.restaurant;
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

	@Override
	public String createOrder(orders orders) {
		return restaurantDao.createOrder(orders);
	}

	@Override
	public String updateOrder(orders orders) {
		return restaurantDao.updateOrder(orders);
	}

	@Override
	public List<orders> readOrder(orders orders) {
		return restaurantDao.readOrder(orders);
	}

	@Override
	public String updateUserProfile(restaurant restaurant) {
		return restaurantDao.updateUserProfile(restaurant);
	}

	@Override
	public String createCustomerReview(customerReview customerReview) {
		return restaurantDao.createCustomerReview(customerReview);
	}

	@Override
	public String updateCustomerReview(customerReview customerReview) {
		return restaurantDao.updateCustomerReview(customerReview);
	}

	@Override
	public List<customerReview> readCustomerReview(customerReview customerReview) {
		return restaurantDao.readCustomerReview(customerReview);
	}

	@Override
	public String createInventory(inventory inventory) {
		return restaurantDao.createInventory(inventory);
	}

	@Override
	public String updateInventory(inventory inventory) {
		return restaurantDao.updateInventory(inventory);
	}

	@Override
	public List<inventory> readInventory(inventory inventory) {
		return restaurantDao.readInventory(inventory);
	}

	@Override
	public String createBill(bill bill) {
		return restaurantDao.createBill(bill);
	}

	@Override
	public String updateBill(bill bill) {
		return restaurantDao.updateBill(bill);
	}

	@Override
	public List<bill> readBill(bill bill) {
		return restaurantDao.readBill(bill);
	}

	@Override
	public String createCoupon(coupon coupon) {
		return restaurantDao.createCoupon(coupon);
	}

	@Override
	public String updateCoupon(coupon coupon) {
		return restaurantDao.updateCoupon(coupon);
	}

	@Override
	public List<coupon> readCoupon(coupon coupon) {
		return restaurantDao.readCoupon(coupon);
	}

	@Override
	public String createAlert(alert alert) {
		return restaurantDao.createAlert(alert);
	}

	@Override
	public String updateAlert(alert alert) {
		return restaurantDao.updateAlert(alert);
	}

	@Override
	public List<alert> readAlert(alert alert) {
		return restaurantDao.readAlert(alert);
	}

	@Override
	public List<orders> totalSales(orderDetails orderDetails) {
		return restaurantDao.totalSales(orderDetails);
	}

	@Override
	public String aov(orderDetails orderDetails) {
		return restaurantDao.aov(orderDetails);
	}

	@Override
	public Map<String, List<String>> bestLeastSellingDish(orderDetails orderDetails) {
		return restaurantDao.bestLeastSellingDish(orderDetails);
	}

}
