package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_orders")
public class m_orders {

	@Id
    @Column(name = "id")
    int id;

    @Column(name = "tableNumber")
    String tableNumber;

    @Column(name = "companyID")
    String companyID;

    @Column(name = "itemsName")
    String itemsName;

    @Column(name = "itemsCode")
    String itemsCode;

    @Column(name = "itemsPrice")
    String itemsPrice;

    @Column(name = "delFlag")
    String delFlag;

    @Column(name = "paymentStatus")
    String paymentStatus;

    @Column(name = "paymentMode")
    String paymentMode;

    @Column(name = "dateAndTime")
    String dateAndTime;

    @Column(name = "processStatus")
    String processStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}

	public String getItemsPrice() {
		return itemsPrice;
	}

	public void setItemsPrice(String itemsPrice) {
		this.itemsPrice = itemsPrice;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
}
