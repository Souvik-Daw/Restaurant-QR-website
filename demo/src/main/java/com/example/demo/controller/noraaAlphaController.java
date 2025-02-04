package com.example.demo.controller;

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
import com.example.demo.bean.scoreBean;
import com.example.demo.services.noraaAlphaService;

@RestController
@RequestMapping("/noraa")
public class noraaAlphaController {
	
	@Autowired
	noraaAlphaService NoraaAlphaService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody userProfileBean userProfileBean)
	{
		String Message=NoraaAlphaService.login(userProfileBean.getLoginId(),userProfileBean.getPswd());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody userProfileBean userProfileBean)
	{
		String Message=NoraaAlphaService.register(userProfileBean.getUserName(),userProfileBean.getUserAge(),
												  userProfileBean.getLoginId(),userProfileBean.getPswd());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/score",method=RequestMethod.POST)
	public ResponseEntity<?> score(@RequestBody scoreBean scoreBean)
	{
		String Message=NoraaAlphaService.score(scoreBean.getLoginId(),scoreBean.getType(),scoreBean.getScore());
		Map<String,String> map=new HashMap<String,String>();
		map.put("Message", Message);
		map.put("Status", "Success");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPlayerData",method=RequestMethod.POST)
	public ResponseEntity<?> getPlayerData(@RequestBody scoreBean scoreBean)
	{
		userProfileBean returnBean=NoraaAlphaService.getPlayerData(scoreBean.getLoginId());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Data", returnBean);
		map.put("Status", "Success");
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllPlayerData",method=RequestMethod.GET)
	public ResponseEntity<?> getAllPlayerData()
	{
		List<userProfileBean> returnBean=NoraaAlphaService.getAllPlayerData();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Data", returnBean);
		map.put("Status", "Success");
		return new ResponseEntity(map, HttpStatus.OK);
	}

	@RequestMapping(value="/updatePlayerData",method=RequestMethod.POST)
	public ResponseEntity<?> updatePlayerData(@RequestBody userProfileBean userProfileBean)
	{
		String updatePlayerData=NoraaAlphaService.updatePlayerData(userProfileBean);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Message", updatePlayerData);
		map.put("Status", "Success");
		return new ResponseEntity(map, HttpStatus.OK);
	}
}
