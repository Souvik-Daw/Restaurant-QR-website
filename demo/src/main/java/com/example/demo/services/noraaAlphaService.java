package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.userProfileBean;


public interface noraaAlphaService {
	
	public String login(String email,String pawd);
	
	public String register(String name,String age,String email,String pawd);
	
	public String score(String email,String type,String score);
	
	public userProfileBean  getPlayerData(String email);
	
	public List<userProfileBean> getAllPlayerData();
	
	public String updatePlayerData(userProfileBean userProfileBean);

}
