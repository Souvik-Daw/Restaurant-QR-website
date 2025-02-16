package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class inventory {

    int id;

    String itemName;

    String price;

    String quantity;

    String itemCode;

    String companyID;

    String delFlag;

    String recordInsertDate;

    String recordUpdateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRecordInsertDate() {
		return recordInsertDate;
	}

	public void setRecordInsertDate(String recordInsertDate) {
		this.recordInsertDate = recordInsertDate;
	}

	public String getRecordUpdateTime() {
		return recordUpdateTime;
	}

	public void setRecordUpdateTime(String recordUpdateTime) {
		this.recordUpdateTime = recordUpdateTime;
	}
	
}
