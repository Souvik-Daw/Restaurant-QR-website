package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_transactionAudit")
public class t_transactionAudit {
	
	@Id
    @Column(name = "id")
    int id;

    @Column(name = "operation")
    String operation;

    @Column(name = "dateAndTime")
    String dateAndTime;

    @Column(name = "companyID")
    String companyID;

    @Column(name = "tableImpacted")
    String tableImpacted;

    @Column(name = "description")
    String description;

    @Column(name = "delFlag")
    String delFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getTableImpacted() {
		return tableImpacted;
	}

	public void setTableImpacted(String tableImpacted) {
		this.tableImpacted = tableImpacted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
    
    

}
