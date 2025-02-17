package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.userProfileBean;
import com.example.demo.bean.alert;
import com.example.demo.bean.bill;
import com.example.demo.bean.coupon;
import com.example.demo.bean.customerReview;
import com.example.demo.bean.inventory;
import com.example.demo.bean.menu;
import com.example.demo.bean.orderDetails;
import com.example.demo.bean.orders;
import com.example.demo.bean.restaurant;
import com.example.demo.services.noraaAlphaService;
import com.example.demo.services.restaurantService;

@RestController
@RequestMapping("/restaurant")
public class restaurantController {
	
	@Autowired
	restaurantService restaurantService;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public ResponseEntity<?> test()
	{
		String Message=restaurantService.test();
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody restaurant restaurant)
	{
		String Message=restaurantService.login(restaurant.getEmail(),restaurant.getPswd());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody restaurant restaurant)
	{
		String Message=restaurantService.register(restaurant.getUserName(),restaurant.getPswd(),
				restaurant.getCompanyId(),restaurant.getEmail(),restaurant.getTotalTables(),
				restaurant.getBankAccountNumber(),restaurant.getIfscCode(),
				restaurant.getGstNumber(),restaurant.getAddress(),
				restaurant.getPhoneNumber());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createMenu",method=RequestMethod.POST)
	public ResponseEntity<?> createMenu(@RequestBody menu menu)
	{
		String Message=restaurantService.createMenu(menu.getItemName(),menu.getItemCode(),menu.getItemStatus(),menu.getItemPrice(),
				 menu.getCategory(),menu.getCompanyId(),menu.getEffectiveFromDate());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateMenu",method=RequestMethod.POST)
	public ResponseEntity<?> updateMenu(@RequestBody menu menu)
	{
		String Message=restaurantService.updateMenu(menu.getId(),menu.getItemName(),menu.getItemCode(),menu.getItemStatus(),menu.getItemPrice(),
				 menu.getCategory(),menu.getCompanyId(),menu.getEffectiveFromDate(),menu.getDelFlag());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readMenuData",method=RequestMethod.POST)
	public ResponseEntity<?> readMenuData(@RequestBody menu menu)
	{
		List<menu> menuList = new ArrayList<menu>();
		menuList=restaurantService.readMenu(menu.getCompanyId());
		Map map=new HashMap<>();
		map.put("Message", menuList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createOrder",method=RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody orders orders)
	{
		String Message=restaurantService.createOrder(orders);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateOrder",method=RequestMethod.POST)
	public ResponseEntity<?> updateOrder(@RequestBody orders orders)
	{
		String Message=restaurantService.updateOrder(orders);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readOrder",method=RequestMethod.POST)
	public ResponseEntity<?> readOrder(@RequestBody orders orders)
	{
		List<orders> orderList = new ArrayList<orders>();
		orderList=restaurantService.readOrder(orders);
		Map map=new HashMap<>();
		map.put("Message", orderList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateUserProfile",method=RequestMethod.POST)
	public ResponseEntity<?> updateUserProfile(@RequestBody restaurant restaurant)
	{
		String status=restaurantService.updateUserProfile(restaurant);
		Map map=new HashMap<>();
		map.put("Message", status);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createCustomerReview",method=RequestMethod.POST)
	public ResponseEntity<?> createCustomerReview(@RequestBody customerReview customerReview)
	{
		String Message=restaurantService.createCustomerReview(customerReview);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateCustomerReview",method=RequestMethod.POST)
	public ResponseEntity<?> updateCustomerReview(@RequestBody customerReview customerReview)
	{
		String Message=restaurantService.updateCustomerReview(customerReview);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readCustomerReview",method=RequestMethod.POST)
	public ResponseEntity<?> readCustomerReview(@RequestBody customerReview customerReview)
	{
		List<customerReview> customerReviewList = new ArrayList<customerReview>();
		customerReviewList=restaurantService.readCustomerReview(customerReview);
		Map map=new HashMap<>();
		map.put("Message", customerReviewList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@RequestMapping(value="/createInventory",method=RequestMethod.POST)
	public ResponseEntity<?> createInventory(@RequestBody inventory inventory)
	{
		String Message=restaurantService.createInventory(inventory);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateInventory",method=RequestMethod.POST)
	public ResponseEntity<?> updateCustomerReview(@RequestBody inventory inventory)
	{
		String Message=restaurantService.updateInventory(inventory);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readInventory",method=RequestMethod.POST)
	public ResponseEntity<?> readInventory(@RequestBody inventory inventory)
	{
		List<inventory> inventoryList = new ArrayList<inventory>();
		inventoryList=restaurantService.readInventory(inventory);
		Map map=new HashMap<>();
		map.put("Message", inventoryList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createBill",method=RequestMethod.POST)
	public ResponseEntity<?> createBill(@RequestBody bill  bill)
	{
		String Message=restaurantService.createBill(bill);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateBill",method=RequestMethod.POST)
	public ResponseEntity<?> updateBill(@RequestBody bill bill)
	{
		String Message=restaurantService.updateBill(bill);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readBill",method=RequestMethod.POST)
	public ResponseEntity<?> readBill(@RequestBody bill bill)
	{
		List<bill> billList = new ArrayList<bill>();
		billList=restaurantService.readBill(bill);
		Map map=new HashMap<>();
		map.put("Message", billList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createCoupon",method=RequestMethod.POST)
	public ResponseEntity<?> createCoupon(@RequestBody coupon  coupon)
	{
		String Message=restaurantService.createCoupon(coupon);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateCoupon",method=RequestMethod.POST)
	public ResponseEntity<?> updateCoupon(@RequestBody coupon coupon)
	{
		String Message=restaurantService.updateCoupon(coupon);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readCoupon",method=RequestMethod.POST)
	public ResponseEntity<?> readCoupon(@RequestBody coupon coupon)
	{
		List<coupon> couponList = new ArrayList<coupon>();
		couponList=restaurantService.readCoupon(coupon);
		Map map=new HashMap<>();
		map.put("Message", couponList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createAlert",method=RequestMethod.POST)
	public ResponseEntity<?> createAlert(@RequestBody alert  alert)
	{
		String Message=restaurantService.createAlert(alert);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateAlert",method=RequestMethod.POST)
	public ResponseEntity<?> updateAlert(@RequestBody alert alert)
	{
		String Message=restaurantService.updateAlert(alert);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readAlert",method=RequestMethod.POST)
	public ResponseEntity<?> readAlert(@RequestBody alert alert)
	{
		List<alert> alertList = new ArrayList<alert>();
		alertList=restaurantService.readAlert(alert);
		Map map=new HashMap<>();
		map.put("Message", alertList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/totalSales",method=RequestMethod.POST)
	public ResponseEntity<?> totalSales(@RequestBody orderDetails orderDetails)
	{
		List<orders> orderList = new ArrayList<>();
		orderList=restaurantService.totalSales(orderDetails);
		Map map=new HashMap<>();
		map.put("Message", orderList);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/aov",method=RequestMethod.POST)
	public ResponseEntity<?> aov(@RequestBody orderDetails  orderDetails)
	{
		String Message=restaurantService.aov(orderDetails);
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/bestLeastSellingDish",method=RequestMethod.POST)
	public ResponseEntity<?> bestLeastSellingDish(@RequestBody orderDetails  orderDetails)
	{
		Map<String,List<String>> Message=restaurantService.bestLeastSellingDish(orderDetails);
		Map map=new HashMap<>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
