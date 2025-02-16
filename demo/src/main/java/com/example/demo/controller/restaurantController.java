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
import com.example.demo.bean.menu;
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

}
