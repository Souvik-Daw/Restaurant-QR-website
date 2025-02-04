package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.userProfileBean;

public interface noraaAlphaDao {
	
	public String login(String email,String pawd);
	
	public String register(String name,String age,String email,String pawd);
	
	public String score(String email,String type,String score);
	
	public userProfileBean getPlayerData(String email);

	public List<userProfileBean> getAllPlayerData();
	
	public String updatePlayerData(userProfileBean userProfileBean);

}
