package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class menu {

    int id;

    String itemName;

    String itemCode;

    String itemStatus;

    String itemPrice;

    String delFlag;

    String category;
    
    String companyId;

    String recordInsertDate;

    String effectiveFromDate;

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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRecordInsertDate() {
		return recordInsertDate;
	}

	public void setRecordInsertDate(String recordInsertDate) {
		this.recordInsertDate = recordInsertDate;
	}

	public String getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(String effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
