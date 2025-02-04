package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.userProfileBean;
import com.example.demo.dao.noraaAlphaDao;

@Service
public class noraaAlphaServiceImpl implements noraaAlphaService{
	
	@Autowired
	noraaAlphaDao noraaAlphaDao;

	public String login(String email,String pawd) {
		return noraaAlphaDao.login(email,pawd);
	}
	
	public String register(String name,String age,String email,String pawd) {
		return noraaAlphaDao.register(name,age,email,pawd);
	}
	
	public String score(String email,String type,String score) {
		return noraaAlphaDao.score(email,type,score);
	}
	
	public userProfileBean  getPlayerData(String email) {
		return noraaAlphaDao.getPlayerData(email);
	}
	
	public List<userProfileBean> getAllPlayerData(){
		return noraaAlphaDao.getAllPlayerData();
	}
	
	public String updatePlayerData(userProfileBean userProfileBean) {
		return noraaAlphaDao.updatePlayerData(userProfileBean);
	}

}
