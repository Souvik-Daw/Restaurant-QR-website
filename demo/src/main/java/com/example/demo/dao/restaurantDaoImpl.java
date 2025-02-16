package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate; 
import java.time.LocalDateTime; 
import java.time.LocalTime; 
import com.example.demo.bean.restaurant;
import com.example.demo.entity.m_restaurant;

@Repository
@Transactional
public class restaurantDaoImpl implements restaurantDao {
	
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

	//Testing function
	@Override
	public String test() {
		return "Working";
	}

	//Register user function
	@Override
	public String register(String userName, String pswd, String companyId, String email, String totalTables,
			String bankAccountNumber, String ifscCode, String gstNumber, String address, String phoneNumber) 
	{
		logger.info("Start of register function");
		Session session=this.sessionFactory.openSession();
		String status="User registered";
		StringBuilder queryStr=new StringBuilder(0);
		boolean saveData=false;
		try {
			List<?> list=null;
			queryStr.append("select * from m_restaurant where email=:email and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("email",email).setParameter("company_id", companyId);
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
				m_restaurant restaurantEntity=new m_restaurant();
				int id=getMaxIDFromTable("m_restaurant");
				
				restaurantEntity.setId(id);
				if(userName!=null)
					restaurantEntity.setUserName(userName);
				if(pswd!=null)
					restaurantEntity.setPswd(pswd);
				if(companyId!=null)
					restaurantEntity.setCompanyId(companyId);
				if(email!=null)
					restaurantEntity.setEmail(email);
				if(totalTables!=null)
					restaurantEntity.setTotalTables(totalTables);
				if(bankAccountNumber!=null)
					restaurantEntity.setBankAccountNumber(bankAccountNumber);
				if(ifscCode!=null)
					restaurantEntity.setIfscCode(ifscCode);
				if(gstNumber!=null)
					restaurantEntity.setGstNumber(gstNumber);
				if(address!=null)
					restaurantEntity.setAddress(address);
				if(phoneNumber!=null)
					restaurantEntity.setPhoneNumber(phoneNumber);
				
				restaurantEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				restaurantEntity.setRecordInsertDate(dateAndTime);
				
				session.save(restaurantEntity);
				session.getTransaction().commit();
			    session.close();
			}
			else
			{
				status="Email/Company ID already present";
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

	//Login user function
	@Override
	public String login(String email, String pswd) {
		Session session=this.sessionFactory.openSession();
		List list=null;
		session.beginTransaction();
		String result="Login Failed";
		StringBuilder queryStr=new StringBuilder(0);
		try
		{	
			queryStr.append("select * from m_restaurant where email=:email and pswd=:pswd");
			Query query=session.createSQLQuery(queryStr.toString()).setParameter("email",email).setParameter("pswd",pswd);
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				result="Login Success";
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception at login function");
		}
		return result;
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
			queryStr.append("select max(id) from "+tableName);
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

}
