package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.example.demo.bean.menu;
import com.example.demo.bean.restaurant;
import com.example.demo.bean.userProfileBean;
import com.example.demo.entity.m_menu;
import com.example.demo.entity.m_restaurant;
import com.example.demo.entity.m_user_profile;

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
			queryStr.append("select * from m_restaurant where email=:email and company_id=:company_id and del_flag= 'N'");
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
			queryStr.append("select * from m_restaurant where email=:email and pswd=:pswd and del_flag= 'N'");
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

	@Override
	public String createMenu(String itemName, String itemCode, String itemStatus, String itemPrice, String category,
			String companyId, String effectiveFromDate) {
		
		logger.info("Start of createMenu function");
		Session session=this.sessionFactory.openSession();
		String status="Menu Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				m_menu menuEntity=new m_menu();
				int id=getMaxIDFromTable("m_menu");
				
				menuEntity.setId(id);
				menuEntity.setItemName(itemName);
				menuEntity.setItemCode(itemCode);
				menuEntity.setItemStatus(itemStatus);
				menuEntity.setItemPrice(itemPrice);
				menuEntity.setCategory(category);
				menuEntity.setCompanyid(companyId);
				menuEntity.setEffectiveFromDate(effectiveFromDate);
				
				menuEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				String date= currentDate+" ";
				menuEntity.setRecordInsertDate(date);
				session.save(menuEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createMenu function,save method "+e);
		}
		logger.info("end of createMenu function");
		return status;
	}

	@Override
	public String updateMenu(int id,String itemName, String itemCode, String itemStatus, String itemPrice, String category,
			String companyId, String effectiveFromDate, String delFlag) 
	{
		logger.info("start of updateMenu function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = id; 
		try {
				session.beginTransaction();
				m_menu test2=session.load(m_menu.class, seq_no);
				
				if(itemCode!=null)
					test2.setItemCode(itemCode);
				if(itemName!=null)
					test2.setItemName(itemName);
				if(itemStatus!=null)
					test2.setItemStatus(itemStatus);
				if(itemPrice!=null)
					test2.setItemPrice(itemPrice);
				if(category!=null)
					test2.setCategory(category);
				if(companyId!=null)
					test2.setCompanyid(companyId);
				if(effectiveFromDate!=null)
					test2.setEffectiveFromDate(effectiveFromDate);
				if(delFlag!=null)
					test2.setDelFlag(delFlag);
				
				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
			}
	
		logger.info("end of updateMenu function");
		return status;
	}

	@Override
	public List<menu> readMenu(String CompanyId) {
		
		logger.info("start of readMenu function");
		System.out.println(CompanyId);
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<menu> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_menu where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",CompanyId);
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					menu testBean=new menu();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setItemName(obj[1]+"");
					if(obj[2]!=null)
						testBean.setItemCode(obj[2]+"");
					if(obj[3]!=null)
						testBean.setCompanyId(obj[3]+"");
					if(obj[4]!=null)
						testBean.setItemStatus(obj[4]+"");
					if(obj[5]!=null)
						testBean.setItemPrice(obj[5]+"");
					if(obj[6]!=null)
						testBean.setDelFlag(obj[6]+"");
					if(obj[7]!=null)
						testBean.setCategory(obj[7]+"");
					if(obj[8]!=null)
						testBean.setRecordInsertDate(obj[8]+"");
					if(obj[9]!=null)
						testBean.setEffectiveFromDate(obj[9]+"");
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
			logger.error("exception at readMenu function "+e);
		}
		logger.info("end of readMenu function");
		return responseList;
	}
	
	

}
