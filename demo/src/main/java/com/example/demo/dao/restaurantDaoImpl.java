package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import com.example.demo.bean.alert;
import com.example.demo.bean.bill;
import com.example.demo.bean.coupon;
import com.example.demo.bean.customerReview;
import com.example.demo.bean.inventory;
import com.example.demo.bean.menu;
import com.example.demo.bean.orderDetails;
import com.example.demo.bean.orders;
import com.example.demo.bean.restaurant;
import com.example.demo.bean.userProfileBean;
import com.example.demo.entity.m_customerReview;
import com.example.demo.entity.m_inventory;
import com.example.demo.entity.m_menu;
import com.example.demo.entity.m_orders;
import com.example.demo.entity.m_restaurant;
import com.example.demo.entity.m_user_profile;
import com.example.demo.entity.t_alert;
import com.example.demo.entity.t_bill;
import com.example.demo.entity.t_coupon;

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
			session.close();
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
			session.close();
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
			session.close();
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
			session.close();
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
				session.close();
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
			session.close();
		}
		logger.info("end of readMenu function");
		return responseList;
	}

	@Override
	public String createOrder(orders orders) {
		logger.info("Start of createOrder function");
		Session session=this.sessionFactory.openSession();
		String status="Order Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				m_orders orderEntity=new m_orders();
				int id=getMaxIDFromTable("m_orders");
				
				orderEntity.setId(id);
				
				orderEntity.setTableNumber(orders.getTableNumber());
				orderEntity.setItemsName(orders.getItemsName());
				orderEntity.setCompanyID(orders.getCompanyID());
				orderEntity.setItemsCode(orders.getItemsCode());
				orderEntity.setItemsPrice(orders.getItemsPrice());
				orderEntity.setPaymentStatus(orders.getPaymentStatus());
				orderEntity.setProcessStatus(orders.getProcessStatus());
				orderEntity.setPaymentMode(orders.getPaymentMode());
				
				orderEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				orderEntity.setDateAndTime(dateAndTime);
				
				session.save(orderEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createOrder function,save method "+e);
			session.close();
		}
		logger.info("end of createOrder function");
		return status;
	}

	@Override
	public String updateOrder(orders orders) {
		logger.info("start of updateOrder function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = orders.getId(); 
		try {
				session.beginTransaction();
				m_orders test2=session.load(m_orders.class, seq_no);
				
				if(orders.getTableNumber()!=null)
					test2.setTableNumber(orders.getTableNumber());
				if(orders.getCompanyID()!=null)
					test2.setCompanyID(orders.getCompanyID());
				if(orders.getItemsName()!=null)
					test2.setItemsName(orders.getItemsName());
				if(orders.getItemsCode()!=null)
					test2.setItemsCode(orders.getItemsCode());
				if(orders.getItemsPrice()!=null)
					test2.setItemsPrice(orders.getItemsPrice());
				if(orders.getPaymentMode()!=null)
					test2.setPaymentMode(orders.getPaymentMode());
				if(orders.getPaymentStatus()!=null)
					test2.setPaymentStatus(orders.getPaymentStatus());
				if(orders.getDelFlag()!=null)
					test2.setDelFlag(orders.getDelFlag());
				if(orders.getDateAndTime()!=null)
					test2.setDateAndTime(orders.getDateAndTime());
				if(orders.getProcessStatus()!=null)
					test2.setProcessStatus(orders.getProcessStatus());
				
				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateMenu function");
		return status;
	}

	@Override
	public List<orders> readOrder(orders orders) {
		logger.info("start of readOrder function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<orders> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_orders where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",orders.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					orders testBean=new orders();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setTableNumber(obj[1]+"");
					if(obj[2]!=null)
						testBean.setCompanyID(obj[2]+"");
					if(obj[3]!=null)
						testBean.setItemsName(obj[3]+"");
					if(obj[4]!=null)
						testBean.setItemsCode(obj[4]+"");
					if(obj[5]!=null)
						testBean.setItemsPrice(obj[5]+"");
					if(obj[6]!=null)
						testBean.setDelFlag(obj[6]+"");
					if(obj[7]!=null)
						testBean.setPaymentStatus(obj[7]+"");
					if(obj[8]!=null)
						testBean.setPaymentMode(obj[8]+"");
					if(obj[9]!=null)
						testBean.setDateAndTime(obj[9]+"");
					if(obj[10]!=null)
						testBean.setProcessStatus(obj[10]+"");
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
			logger.error("exception at readOrder function "+e);
			session.close();
		}
		logger.info("end of readOrder function");
		return responseList;
	}

	@Override
	public String updateUserProfile(restaurant restaurant) {
		logger.info("start of updateUserProfile function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = restaurant.getId(); 
		try {
				session.beginTransaction();
				m_restaurant test2=session.load(m_restaurant.class, seq_no);
				
				if (restaurant.getUserName() != null) 
					test2.setUserName(restaurant.getUserName());
				if (restaurant.getPswd() != null) 
					test2.setPswd(restaurant.getPswd());
				if (restaurant.getCompanyId() != null) 
					test2.setCompanyId(restaurant.getCompanyId());
				if (restaurant.getEmail() != null) 
					test2.setEmail(restaurant.getEmail());
				if (restaurant.getLastLogin() != null) 
					test2.setLastLogin(restaurant.getLastLogin());
				if (restaurant.getDelFlag() != null) 
					test2.setDelFlag(restaurant.getDelFlag());
				if (restaurant.getRecordInsertDate() != null) 
					test2.setRecordInsertDate(restaurant.getRecordInsertDate());
				if (restaurant.getTotalTables() != null) 
					test2.setTotalTables(restaurant.getTotalTables());
				if (restaurant.getBankAccountNumber() != null) 
					test2.setBankAccountNumber(restaurant.getBankAccountNumber());
				if (restaurant.getIfscCode() != null) 
					test2.setIfscCode(restaurant.getIfscCode());
				if (restaurant.getGstNumber() != null) 
					test2.setGstNumber(restaurant.getGstNumber());
				if (restaurant.getAddress() != null) 
					test2.setAddress(restaurant.getAddress());
				if (restaurant.getPhoneNumber() != null) 
					test2.setPhoneNumber(restaurant.getPhoneNumber());
				
				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateUserProfile function");
		return status;
	}

	@Override
	public String createCustomerReview(customerReview customerReview) {
		logger.info("Start of createCustomerReview function");
		Session session=this.sessionFactory.openSession();
		String status="Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				m_customerReview customerReviewEntity=new m_customerReview();
				int id=getMaxIDFromTable("m_customer_review");
				
				customerReviewEntity.setId(id);
				
				if (customerReview.getTableNumber() != null) {
					customerReviewEntity.setTableNumber(customerReview.getTableNumber());
				}

				if (customerReview.getCompanyID() != null) {
					customerReviewEntity.setCompanyID(customerReview.getCompanyID());
				}

				if (customerReview.getMessage() != null) {
					customerReviewEntity.setMessage(customerReview.getMessage());
				}

				if (customerReview.getStars() != null) {
					customerReviewEntity.setStars(customerReview.getStars());
				}

				customerReviewEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				customerReviewEntity.setDateAndTime(dateAndTime);
				
				session.save(customerReviewEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createCustomerReview function,save method "+e);
			session.close();
		}
		logger.info("end of createCustomerReview function");
		return status;
	}

	@Override
	public String updateCustomerReview(customerReview customerReview) {
		logger.info("start of updateCustomerReview function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = customerReview.getId(); 
		try {
				session.beginTransaction();
				m_customerReview test2=session.load(m_customerReview.class, seq_no);
				
				if (customerReview.getTableNumber() != null) {
				    test2.setTableNumber(customerReview.getTableNumber());
				}

				if (customerReview.getCompanyID() != null) {
				    test2.setCompanyID(customerReview.getCompanyID());
				}

				if (customerReview.getDelFlag() != null) {
				    test2.setDelFlag(customerReview.getDelFlag());
				}

				if (customerReview.getDateAndTime() != null) {
				    test2.setDateAndTime(customerReview.getDateAndTime());
				}

				if (customerReview.getMessage() != null) {
				    test2.setMessage(customerReview.getMessage());
				}

				if (customerReview.getStars() != null) {
				    test2.setStars(customerReview.getStars());
				}

				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateCustomerReview function");
		return status;
	}

	@Override
	public List<customerReview> readCustomerReview(customerReview customerReview) {
		logger.info("start of readCustomerReview function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<customerReview> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_customer_review where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",customerReview.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					customerReview testBean=new customerReview();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setTableNumber(obj[1]+"");
					if(obj[2]!=null)
						testBean.setCompanyID(obj[2]+"");
					if(obj[3]!=null)
						testBean.setDelFlag(obj[3]+"");
					if(obj[4]!=null)
						testBean.setDateAndTime(obj[4]+"");
					if(obj[5]!=null)
						testBean.setMessage(obj[5]+"");
					if(obj[6]!=null)
						testBean.setStars(obj[6]+"");
					
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
			logger.error("exception at readCustomerReview function "+e);
			session.close();
		}
		logger.info("end of readCustomerReview function");
		return responseList;
	}

	@Override
	public String createInventory(inventory inventory) {
		logger.info("Start of createInventory function");
		Session session=this.sessionFactory.openSession();
		String status="Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				m_inventory inventoryEntity=new m_inventory();
				int id=getMaxIDFromTable("m_inventory");
				
				inventoryEntity.setId(id);
				
				if (inventory.getItemName() != null) {
				    inventoryEntity.setItemName(inventory.getItemName());
				}

				if (inventory.getPrice() != null) {
				    inventoryEntity.setPrice(inventory.getPrice());
				}

				if (inventory.getQuantity() != null) {
				    inventoryEntity.setQuantity(inventory.getQuantity());
				}

				if (inventory.getItemCode() != null) {
				    inventoryEntity.setItemCode(inventory.getItemCode());
				}

				if (inventory.getCompanyID() != null) {
				    inventoryEntity.setCompanyID(inventory.getCompanyID());
				}

				inventoryEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				inventoryEntity.setRecordInsertDate(dateAndTime);
				inventoryEntity.setRecordUpdateTime(dateAndTime);
				
				session.save(inventoryEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createInventory function,save method "+e);
			session.close();
		}
		logger.info("end of createInventory function");
		return status;
	}

	@Override
	public String updateInventory(inventory inventory) {
		logger.info("start of updateInventory function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = inventory.getId(); 
		try {
				session.beginTransaction();
				m_inventory test2=session.load(m_inventory.class, seq_no);
				
				if (inventory.getItemName() != null) {
				    test2.setItemName(inventory.getItemName());
				}

				if (inventory.getPrice() != null) {
				    test2.setPrice(inventory.getPrice());
				}

				if (inventory.getQuantity() != null) {
				    test2.setQuantity(inventory.getQuantity());
				}

				if (inventory.getItemCode() != null) {
				    test2.setItemCode(inventory.getItemCode());
				}

				if (inventory.getCompanyID() != null) {
				    test2.setCompanyID(inventory.getCompanyID());
				}

				if (inventory.getDelFlag() != null) {
				    test2.setDelFlag(inventory.getDelFlag());
				}
				
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				test2.setRecordUpdateTime(dateAndTime);


				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateInventory function");
		return status;
	}

	@Override
	public List<inventory> readInventory(inventory inventory) {
		logger.info("start of readInventory function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<inventory> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_inventory where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",inventory.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					inventory testBean=new inventory();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setItemName(obj[1]+"");
					if(obj[2]!=null)
						testBean.setPrice(obj[2]+"");
					if(obj[3]!=null)
						testBean.setQuantity(obj[3]+"");
					if(obj[4]!=null)
						testBean.setItemCode(obj[4]+"");
					if(obj[5]!=null)
						testBean.setCompanyID(obj[5]+"");
					if(obj[6]!=null)
						testBean.setDelFlag(obj[6]+"");
					if(obj[7]!=null)
						testBean.setRecordInsertDate(obj[7]+"");
					if(obj[8]!=null)
						testBean.setRecordUpdateTime(obj[8]+"");
					
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
			logger.error("exception at readInventory function "+e);
			session.close();
		}
		logger.info("end of readInventory function");
		return responseList;
	}

	@Override
	public String createBill(bill bill) {
		logger.info("Start of createBill function");
		Session session=this.sessionFactory.openSession();
		String status="Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				t_bill billEntity=new t_bill();
				int id=getMaxIDFromTable("t_bill");
				
				billEntity.setId(id);
				
				if (bill.getUserName() != null) {
				    billEntity.setUserName(bill.getUserName());
				}

				if (bill.getCompanyID() != null) {
				    billEntity.setCompanyID(bill.getCompanyID());
				}

				if (bill.getAddress() != null) {
				    billEntity.setAddress(bill.getAddress());
				}

				if (bill.getPhone() != null) {
				    billEntity.setPhone(bill.getPhone());
				}

				if (bill.getEmail() != null) {
				    billEntity.setEmail(bill.getEmail());
				}

				if (bill.getGstNumber() != null) {
				    billEntity.setGstNumber(bill.getGstNumber());
				}

				if (bill.getPaymentMode() != null) {
				    billEntity.setPaymentMode(bill.getPaymentMode());
				}

				if (bill.getItems() != null) {
				    billEntity.setItems(bill.getItems());
				}

				if (bill.getPrice() != null) {
				    billEntity.setPrice(bill.getPrice());
				}

				if (bill.getBillNo() != null) {
				    billEntity.setBillNo(bill.getBillNo());
				}

				if (bill.getPersons() != null) {
				    billEntity.setPersons(bill.getPersons());
				}

				billEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				billEntity.setDateAndTime(dateAndTime);
				
				session.save(billEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createBill function,save method "+e);
			session.close();
		}
		logger.info("end of createBill function");
		return status;
	}

	@Override
	public String updateBill(bill bill) {
		logger.info("start of updateBill function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = bill.getId(); 
		try {
				session.beginTransaction();
				t_bill test2=session.load(t_bill.class, seq_no);
				
				if (bill.getUserName() != null) {
				    test2.setUserName(bill.getUserName());
				}

				if (bill.getCompanyID() != null) {
				    test2.setCompanyID(bill.getCompanyID());
				}

				if (bill.getAddress() != null) {
				    test2.setAddress(bill.getAddress());
				}

				if (bill.getPhone() != null) {
				    test2.setPhone(bill.getPhone());
				}

				if (bill.getEmail() != null) {
				    test2.setEmail(bill.getEmail());
				}

				if (bill.getGstNumber() != null) {
				    test2.setGstNumber(bill.getGstNumber());
				}

				if (bill.getPaymentMode() != null) {
				    test2.setPaymentMode(bill.getPaymentMode());
				}

				if (bill.getItems() != null) {
				    test2.setItems(bill.getItems());
				}

				if (bill.getPrice() != null) {
				    test2.setPrice(bill.getPrice());
				}

				if (bill.getBillNo() != null) {
				    test2.setBillNo(bill.getBillNo());
				}

				if (bill.getPersons() != null) {
				    test2.setPersons(bill.getPersons());
				}

				if (bill.getDateAndTime() != null) {
				    test2.setDateAndTime(bill.getDateAndTime());
				}

				if (bill.getDelFlag() != null) {
				    test2.setDelFlag(bill.getDelFlag());
				}

				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateBill function");
		return status;
	}

	@Override
	public List<bill> readBill(bill bill) {
		logger.info("start of readBill function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<bill> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from t_bill where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",bill.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					bill testBean=new bill();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setUserName(obj[1]+"");
					if(obj[2]!=null)
						testBean.setCompanyID(obj[2]+"");
					if(obj[3]!=null)
						testBean.setAddress(obj[3]+"");
					if(obj[4]!=null)
						testBean.setPhone(obj[4]+"");
					if(obj[5]!=null)
						testBean.setEmail(obj[5]+"");
					if(obj[6]!=null)
						testBean.setGstNumber(obj[6]+"");
					if(obj[7]!=null)
						testBean.setPaymentMode(obj[7]+"");
					if(obj[8]!=null)
						testBean.setItems(obj[8]+"");
					if(obj[9]!=null)
						testBean.setPrice(obj[9]+"");
					if(obj[10]!=null)
						testBean.setBillNo(obj[10]+"");
					if(obj[11]!=null)
						testBean.setPersons(obj[11]+"");
					if(obj[12]!=null)
						testBean.setDateAndTime(obj[12]+"");
					if(obj[12]!=null)
						testBean.setDelFlag(obj[12]+"");
					
					
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
			logger.error("exception at readBill function "+e);
			session.close();
		}
		logger.info("end of readBill function");
		return responseList;
	}

	@Override
	public String createCoupon(coupon coupon) {
		logger.info("Start of createCoupon function");
		Session session=this.sessionFactory.openSession();
		String status="Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				t_coupon couponEntity=new t_coupon();
				
				
				couponEntity.setId(coupon.getId());
				
				
				if (coupon.getDescription() != null) {
				    couponEntity.setDescription(coupon.getDescription());
				}

				if (coupon.getAmount() != null) {
				    couponEntity.setAmount(coupon.getAmount());
				}

				if (coupon.getCompanyID() != null) {
				    couponEntity.setCompanyID(coupon.getCompanyID());
				}

				if (coupon.getExpDate() != null) {
				    couponEntity.setExpDate(coupon.getExpDate());
				}

				if (coupon.getDelFlag() != null) {
				    couponEntity.setDelFlag(coupon.getDelFlag());
				}
				
				session.save(couponEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createCoupon function,save method "+e);
			session.close();
		}
		logger.info("end of createCoupon function");
		return status;
	}

	@Override
	public String updateCoupon(coupon coupon) {
		logger.info("start of updateCoupon function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = coupon.getId(); 
		try {
				session.beginTransaction();
				t_coupon test2=session.load(t_coupon.class, seq_no);
				
				if (coupon.getDescription() != null) {
					test2.setDescription(coupon.getDescription());
				}

				if (coupon.getAmount() != null) {
					test2.setAmount(coupon.getAmount());
				}

				if (coupon.getCompanyID() != null) {
					test2.setCompanyID(coupon.getCompanyID());
				}

				if (coupon.getExpDate() != null) {
					test2.setExpDate(coupon.getExpDate());
				}

				if (coupon.getDelFlag() != null) {
					test2.setDelFlag(coupon.getDelFlag());
				}

				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateCoupon function");
		return status;
	}

	@Override
	public List<coupon> readCoupon(coupon coupon) {
		logger.info("start of readCoupon function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<coupon> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from t_coupon where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",coupon.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					coupon testBean=new coupon();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setDescription(obj[1]+"");
					if(obj[2]!=null)
						testBean.setAmount(obj[2]+"");
					if(obj[3]!=null)
						testBean.setCompanyID(obj[3]+"");
					if(obj[4]!=null)
						testBean.setExpDate(obj[4]+"");
					if(obj[5]!=null)
						testBean.setDelFlag(obj[5]+"");
					
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
			logger.error("exception at readCoupon function "+e);
			session.close();
		}
		logger.info("end of readCoupon function");
		return responseList;
	}

	@Override
	public String createAlert(alert alert) {
		logger.info("Start of createAlert function");
		Session session=this.sessionFactory.openSession();
		String status="Created";
		StringBuilder queryStr=new StringBuilder(0);
		session.beginTransaction();
		try {
				t_alert alertEntity=new t_alert();
				int id=getMaxIDFromTable("t_alert");
				
				alertEntity.setId(id);
				
				if (alert.getAlertName() != null) {
				    alertEntity.setAlertName(alert.getAlertName());
				}

				if (alert.getDescription() != null) {
				    alertEntity.setDescription(alert.getDescription());
				}

				if (alert.getStatus() != null) {
				    alertEntity.setStatus(alert.getStatus());
				}

				if (alert.getPriority() != null) {
				    alertEntity.setPriority(alert.getPriority());
				}

				if (alert.getCompanyID() != null) {
				    alertEntity.setCompanyID(alert.getCompanyID());
				}

				alertEntity.setDelFlag("N");
				LocalDate currentDate = LocalDate.now(); 
				LocalTime currentTime = LocalTime.now(); 
				String dateAndTime= currentDate+" "+currentTime;
				alertEntity.setDateAndTime(dateAndTime);
				
				session.save(alertEntity);
				session.getTransaction().commit();
			    session.close();
			}
			
		catch(Exception e)
		{
			status="error occured";
			status="Something went wrong, try again later";
			logger.error("Exception at createAlert function,save method "+e);
			session.close();
		}
		logger.info("end of createAlert function");
		return status;
	}

	@Override
	public String updateAlert(alert alert) {
		logger.info("start of updateAlert function");
		String status="Failed to update data";
		Session session=this.sessionFactory.openSession();
		List list=null;
		StringBuilder queryStr=new StringBuilder(0);
		
		int seq_no = alert.getId(); 
		try {
				session.beginTransaction();
				t_alert test2=session.load(t_alert.class, seq_no);
				
				if (alert.getAlertName() != null) {
				    test2.setAlertName(alert.getAlertName());
				}

				if (alert.getDescription() != null) {
				    test2.setDescription(alert.getDescription());
				}

				if (alert.getStatus() != null) {
				    test2.setStatus(alert.getStatus());
				}

				if (alert.getPriority() != null) {
				    test2.setPriority(alert.getPriority());
				}

				if (alert.getDelFlag() != null) {
				    test2.setDelFlag(alert.getDelFlag());
				}

				if (alert.getCompanyID() != null) {
				    test2.setCompanyID(alert.getCompanyID());
				}

				session.save(test2);
				session.getTransaction().commit();
			    session.close();
			    status="Data updated";
			}
			catch(Exception e){
				status="Failed to update data";
				session.close();
			}
	
		logger.info("end of updateAlert function");
		return status;
	}

	@Override
	public List<alert> readAlert(alert alert) {
		logger.info("start of readAlert function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<alert> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from t_alert where del_flag = 'N' and company_id=:company_id");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",alert.getCompanyID());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					alert testBean=new alert();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setAlertName(obj[1]+"");
					if(obj[2]!=null)
						testBean.setDescription(obj[2]+"");
					if(obj[3]!=null)
						testBean.setStatus(obj[3]+"");
					if(obj[4]!=null)
						testBean.setPriority(obj[4]+"");
					if(obj[5]!=null)
						testBean.setDateAndTime(obj[5]+"");
					if(obj[6]!=null)
						testBean.setDelFlag(obj[6]+"");
					if(obj[7]!=null)
						testBean.setCompanyID(obj[7]+"");
					
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
			logger.error("exception at readAlert function "+e);
			session.close();
		}
		logger.info("end of readAlert function");
		return responseList;
	}

	@Override
	public List<orders> totalSales(orderDetails orderDetails) {
		logger.info("start of totalSales function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		session.beginTransaction();
		List<orders> responseList=new ArrayList<>();
		StringBuilder queryStr=new StringBuilder(0);
		try {
			queryStr.append("select * from m_orders where del_flag = 'N' and company_id=:company_id and date_and_time > :fromDate and date_and_time < :toDate");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",orderDetails.getCompanyId())
																	  .setParameter("fromDate",orderDetails.getFromDateAndTime())
																	  .setParameter("toDate",orderDetails.getToDateAndTime());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					orders testBean=new orders();
					if(obj[0]!=null)
						testBean.setId(Integer.parseInt(obj[0]+""));
					if(obj[1]!=null)
						testBean.setTableNumber(obj[1]+"");
					if(obj[2]!=null)
						testBean.setCompanyID(obj[2]+"");
					if(obj[3]!=null)
						testBean.setItemsName(obj[3]+"");
					if(obj[4]!=null)
						testBean.setItemsCode(obj[4]+"");
					if(obj[5]!=null)
						testBean.setItemsPrice(obj[5]+"");
					if(obj[6]!=null)
						testBean.setDelFlag(obj[6]+"");
					if(obj[7]!=null)
						testBean.setPaymentStatus(obj[7]+"");
					if(obj[8]!=null)
						testBean.setPaymentMode(obj[8]+"");
					if(obj[9]!=null)
						testBean.setDateAndTime(obj[9]+"");
					if(obj[10]!=null)
						testBean.setProcessStatus(obj[10]+"");
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
			logger.error("exception at totalSales function "+e);
			session.close();
		}
		logger.info("end of totalSales function");
		return responseList;
	}

	@Override
	public String aov(orderDetails orderDetails) {
		logger.info("start of aov function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		int aov=0;
		session.beginTransaction();
		StringBuilder queryStr=new StringBuilder(0);
		try
		{	
			queryStr.append("select items_price from m_orders where del_flag = 'N' and company_id=:company_id and date_and_time > :fromDate and date_and_time < :toDate");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",orderDetails.getCompanyId())
																	  .setParameter("fromDate",orderDetails.getFromDateAndTime())
																	  .setParameter("toDate",orderDetails.getToDateAndTime());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for (int i=0;i<list.size();i++)
					aov+= Integer.parseInt((String) list.get(i));
				
				aov/=list.size();
			}
			session.getTransaction().commit();
		    session.close();
		}
		catch(Exception e)
		{
			logger.info("Exception at aov function "+e);
			session.close();
		}
		logger.info("end of aov function");
		return aov+"";
	}

	@Override
	public Map<String, List<String>> bestLeastSellingDish(orderDetails orderDetails) {
		logger.info("start of bestLeastSellingDish function");
		Session session=this.sessionFactory.openSession();
		List<?> list=null;
		int aov=0;
		session.beginTransaction();
		StringBuilder queryStr=new StringBuilder(0);
		Map<String, Integer> countMap = new HashMap<>();
		Map<String, List<String>> bestLeastSellingDish = new HashMap<>();
		try
		{	
			queryStr.append("select * from m_orders where del_flag = 'N' and company_id=:company_id and date_and_time > :fromDate and date_and_time < :toDate");
			Query<?> query=session.createSQLQuery(queryStr.toString()).setParameter("company_id",orderDetails.getCompanyId())
																	  .setParameter("fromDate",orderDetails.getFromDateAndTime())
																	  .setParameter("toDate",orderDetails.getToDateAndTime());
			list =  query.getResultList();
			if(list!=null && list.size()>0)
			{
				for(Iterator it=list.iterator();it.hasNext();)
				{
					Object[] obj=(Object[])it.next();
					String items = obj[3]+"";
					
					List<String> item = Arrays.asList(items.split(","));
					for (String eachItem : item) {
			            countMap.put(eachItem, countMap.getOrDefault(eachItem, 0) + 1);
			        }
					
				}
			}
			session.getTransaction().commit();
		    session.close();
		    
		    int maxValue=Integer.MIN_VALUE;
			int minValue=Integer.MAX_VALUE;
			
			for (Map.Entry<String,Integer> entry : countMap.entrySet()) 
			{
				if(entry.getValue()>maxValue)
					maxValue=entry.getValue();
				
				if(entry.getValue()<minValue)
					minValue=entry.getValue();
			}
	        List<String> bestSelling = new ArrayList<>();
	        List<String> leastSelling = new ArrayList<>();
	        
	        for (Map.Entry<String,Integer> entry : countMap.entrySet()) 
			{
				if(entry.getValue()==maxValue)
				{
					bestSelling.add(entry.getKey());
				}
				
				if(entry.getValue()==minValue)
				{
					leastSelling.add(entry.getKey());
				}
			}
			
	        bestLeastSellingDish.put("Best selling with "+maxValue+" order's", bestSelling);
	        bestLeastSellingDish.put("Least selling with "+minValue+" order's", leastSelling);
		}
		catch(Exception e)
		{
			logger.info("Exception at bestLeastSellingDish function "+e);
			session.close();
		}
		logger.info("end of bestLeastSellingDish function");
		
		return bestLeastSellingDish;
	}

}
