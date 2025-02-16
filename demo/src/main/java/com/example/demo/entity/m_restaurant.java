package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_restaurant")
public class m_restaurant {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "userName")
     String userName;

    @Column(name = "pswd")
     String pswd;

    @Column(name = "companyId")
     String companyId;

    @Column(name = "email")
     String email;

    @Column(name = "lastLogin")
     String lastLogin;

    @Column(name = "delFlag")
     String delFlag;

    @Column(name = "recordInsertDate")
     String recordInsertDate;

    @Column(name = "totalTables")
     String totalTables;

    @Column(name = "bankAccountNumber")
     String bankAccountNumber;

    @Column(name = "ifscCode")
     String ifscCode;

    @Column(name = "gstNumber")
     String gstNumber;

    @Column(name = "address")
    String address;

    @Column(name = "phoneNumber")
    String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
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

	public String getTotalTables() {
		return totalTables;
	}

	public void setTotalTables(String totalTables) {
		this.totalTables = totalTables;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
