package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_menu")
public class m_menu {
	
	@Id
    @Column(name = "id")
    int id;

    @Column(name = "itemName")
    String itemName;

    @Column(name = "itemCode")
    String itemCode;

    @Column(name = "itemStatus")
    String itemStatus;

    @Column(name = "itemPrice")
    String itemPrice;

    @Column(name = "delFlag")
    String delFlag;

    @Column(name = "category")
    String category;
    
    @Column(name = "companyId")
    String companyid;

    @Column(name = "recordInsertDate")
    String recordInsertDate;

    @Column(name = "effectiveFromDate")
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

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

}
