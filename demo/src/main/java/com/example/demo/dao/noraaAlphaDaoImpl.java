package com.example.demo.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.userProfileBean;
import com.example.demo.entity.m_user_profile;


@Repository
@Transactional
public class noraaAlphaDaoImpl implements noraaAlphaDao{
	
	Logger logger 
    = LoggerFactory.getLogger(noraaAlphaDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionfactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public String login(String email,String pawd) {
        logger.info("Start of login function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		String result="Wrong Username/Password";
		StringBuilder queryStr=new StringBuilder(0);
		try
		{	
			queryStr.append("select * from m_user_profile where login_id=:login_id and pswd=:pswd");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",email).setParameter("pswd",pawd);
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				logger.info("Inside list validation block");
				result="Welcome to NORAA";
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e)
		{
			logger.warn("Exception at login function");
	        logger.error("Exception at login function"+e);
		}
		logger.info("End of login function");
		return result;
	}
	
	public String register(String name,String age,String email,String pawd) {
		logger.info("Start of register function");
		Session session=this.sessionFactory.openSession();
		String status="User registeres";
		StringBuilder queryStr=new StringBuilder(0);
		boolean saveData=false;
		try {
			List<?> list=null;
			queryStr.append("select * from m_user_profile where login_id=:login_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",email);
			list =  query.getResultList();
			int count = list.size();
			logger.info("user count "+count);
			if(count==0)
			{
				saveData=true;
			}
		}
		catch(Exception e){
			status="Something went wrong, try again later";
			logger.error("Exception at register function,login id check method"+e);
		}
		try 
		{
			if(saveData)
			{
				session.beginTransaction();
				m_user_profile profileEntity=new m_user_profile();
				int id=getMaxIDFromTable("m_user_profile");
				profileEntity.setSeqNo(id);
				profileEntity.setLoginId(email);
				profileEntity.setUserAge(age);
				profileEntity.setUserName(name);
				profileEntity.setPswd(pawd);
				profileEntity.setScore("0");
				session.save(profileEntity);
				session.getTransaction().commit();
			    session.close();
			}
			else
			{
				status="Email ID already present";
			}
		}
		catch(Exception e)
		{
			status="Something went wrong, try again later";
			logger.error("Exception at register function,save method "+e);
		}
		logger.info("end of register function");
		return status;
	}
	
	public int getMaxIDFromTable(String tableName)
	{
		logger.info("start of getMaxIDFromTable function");
		Session session=this.sessionFactory.openSession();
		List list=null;
		int maxNum=0;
		session.beginTransaction();
		StringBuilder queryStr=new StringBuilder(0);
		try
		{	
			queryStr.append("select max(seq_no) from "+tableName);
			Query query=session.createSQLQuery(queryStr.toString());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				maxNum=(Integer) list.get(0);
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e)
		{
			logger.info("Exception at getMaxIDFromTable function "+e);
		}
		logger.info("end of getMaxIDFromTable function");
		return maxNum+1;
	}
	
	
	public String score(String email,String type,String score) {
		logger.info("start of score function");
		String status="Updation request failed";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		queryStr.append("select seq_no from m_user_profile where login_id=:login_id");
		Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",email);
		list=query.getResultList();
		int seq_no = (int) list.get(0); 
		logger.info("seq_no at score function "+seq_no);
		try {
			queryStr=new StringBuilder(0);
			queryStr.append("select score from m_user_profile where login_id=:login_id");
			query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",email);
			list=query.getResultList();
			String Databasescore=(String) list.get(0);
			logger.info("Databasescore at score function "+Databasescore);
			switch(type)
			{
				case "Increment":
					logger.info("inside score increment function");
					session.beginTransaction();
					m_user_profile test=session.load(m_user_profile.class, seq_no);
					test.setSeqNo(seq_no);
					int finalScore=Integer.parseInt(Databasescore)+Integer.parseInt(score);
					String finalRank=userRankDetails(finalScore);
					test.setUserRank(finalRank);
					test.setScore(finalScore+"");
					session.save(test);
					session.getTransaction().commit();
				    session.close();
					status="Operation success";
				break;
				case "Decrement":
					logger.info("inside score decrement function");
					session.beginTransaction();
					m_user_profile test2=session.load(m_user_profile.class, seq_no);
					test2.setSeqNo(seq_no);
					int finalScore2=Integer.parseInt(Databasescore)-Integer.parseInt(score);
					String finalRank2=userRankDetails(finalScore2);
					test2.setUserRank(finalRank2);
					test2.setScore(finalScore2+"");
					session.save(test2);
					session.getTransaction().commit();
				    session.close();
					status="Operation success";
				break;
				default:
					logger.info("inside score default function");
					status="Operation success";
					
			}
		}
		catch(Exception e) {
			status="Updation request failed";
			logger.error("Exception at score function "+e);
		}
		logger.info("end of score function");
		return status;
	}
	
	public String userRankDetails(int finalScore)
	{
//		String finalRank="";
//		if(finalScore<1)
//		{
//			finalRank="Level 0";
//		}
//		else if(finalScore>=1 && finalScore<10)
//		{
//			finalRank="Level 1";
//		}
//		else if(finalScore>=10 && finalScore<20)
//		{
//			finalRank="Level 2";
//		}
//		else if(finalScore>=20 && finalScore<30)
//		{
//			finalRank="Level 3";
//		}
//		else if(finalScore>=30 && finalScore<40)
//		{
//			finalRank="Level 4";
//		}
//		else if(finalScore>=40 && finalScore<50)
//		{
//			finalRank="Level 5";
//		}	
//		else
//		{
//			finalRank="Special Grade";
//		}
		
		if(finalScore<1)
			return "Level 0";
		else if(finalScore>49)
			return "Specil Level";
		
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		queryStr.append("select user_rank from m_rank where "+finalScore+" between low_score and high_score;");
		Query<?> query=session.createSQLQuery(queryStr.toString());
		list=query.getResultList();
		String rank = (String) list.get(0); 
		return rank;
	}
	
	public userProfileBean getPlayerData(String email) {
		logger.info("start of getPlayerData function");
		Session session=this.sessionFactory.openSession();
		//Session session=DBConnect.getCurrentSession();
		List<?> list=null;
		session.beginTransaction();
		List<userProfileBean> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_user_profile where login_id=:login_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",email);
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					logger.info("in loop of getPlayerData function");
					Object[] obj=(Object[])it.next();
					userProfileBean testBean=new userProfileBean();
					testBean.setSeqNo(Integer.parseInt(obj[0]+""));
					testBean.setLoginId(obj[1]+"");
					testBean.setUserName(obj[3]+"");
					testBean.setUserAge(obj[4]+"");
					testBean.setUserRank(obj[5]+"");
					testBean.setKillDeathRatio(obj[6]+"");
					testBean.setMatchPlayed(obj[7]+"");
					testBean.setMatchWon(obj[8]+"");
					testBean.setTotalHeadshot(obj[9]+"");
					testBean.setHighestKills(obj[10]+"");
					testBean.setAccuracy(obj[11]+"");
					testBean.setClan(obj[12]+"");
					testBean.setScore(obj[13]+"");
					responseList.add(testBean);
				}
			}
			else
			{
				return null;
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e){
			logger.error("exception at getPlayerData function "+e);
		}
		logger.info("end of getPlayerData function");
		return responseList.get(0);
	}
	
	public List<userProfileBean> getAllPlayerData()
	{
		logger.info("start of getAllPlayerData function");
		Session session=this.sessionFactory.openSession();
		//Session session=DBConnect.getCurrentSession();
		List<?> list=null;
		session.beginTransaction();
		List<userProfileBean> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_user_profile");
			Query<?> query=session.createSQLQuery(queryStr.toString());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					logger.info("in loop of getAllPlayerData function");
					Object[] obj=(Object[])it.next();
					userProfileBean testBean=new userProfileBean();
					testBean.setSeqNo(Integer.parseInt(obj[0]+""));
					testBean.setLoginId(obj[1]+"");
					testBean.setUserName(obj[3]+"");
					testBean.setUserAge(obj[4]+"");
					testBean.setUserRank(obj[5]+"");
					testBean.setKillDeathRatio(obj[6]+"");
					testBean.setMatchPlayed(obj[7]+"");
					testBean.setMatchWon(obj[8]+"");
					testBean.setTotalHeadshot(obj[9]+"");
					testBean.setHighestKills(obj[10]+"");
					testBean.setAccuracy(obj[11]+"");
					testBean.setClan(obj[12]+"");
					testBean.setScore(obj[13]+"");
					responseList.add(testBean);
				}
			}
			else
			{
				return null;
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e){
			logger.error("exception at getAllPlayerData function "+e);
		}
		logger.info("end of getAllPlayerData function");
		return responseList;
	}
	
	public String updatePlayerData(userProfileBean userProfileBean)
	{
		logger.info("start of updatePlayerData function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		queryStr.append("select seq_no from m_user_profile where login_id=:login_id");
		Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("login_id",userProfileBean.getLoginId());
		list=query.getResultList();
		if(list.size()==0)
		{
			status="User is not present";
		}
		else
		{
			int seq_no = (int) list.get(0); 
			logger.info("seq_no at updatePlayerData function "+seq_no);
			try {
				session.beginTransaction();
				m_user_profile test2=session.load(m_user_profile.class, seq_no);
				test2.setLoginId(userProfileBean.getLoginId());
				test2.setUserName(userProfileBean.getUserName());
				test2.setUserRank(userProfileBean.getUserRank());
				test2.setKillDeathRatio(userProfileBean.getKillDeathRatio());
				test2.setMatchPlayed(userProfileBean.getMatchPlayed());
				test2.setMatchWon(userProfileBean.getMatchWon());
				test2.setTotalHeadshot(userProfileBean.getTotalHeadshot());
				test2.setHighestKills(userProfileBean.getHighestKills());
				test2.setAccuracy(userProfileBean.getAccuracy());
				test2.setClan(userProfileBean.getClan());
				test2.setScore(userProfileBean.getScore());
				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Player data updated";
			}
			catch(Exception e){
				status="Failed to update data";
			}
		}
		logger.info("end of updatePlayerData function");
		return status;
	}
}
