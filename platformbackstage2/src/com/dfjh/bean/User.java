package com.dfjh.bean;

import java.util.Date;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String uid;
	private Columns columns;
	private Company company;
	private Category category;
	private Integer id;
	private String uname;
	private String sex;
	private String email;
	private String phoneNumber;
	private String companyName;
	private String categoryName;
	private Integer level;
	private String url;
	private String password;
	private Integer accountSts;
	private Date accountOpenTime;
	private String accountTime;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String uid, Category category, Integer id, String uname,
			String categoryName, Integer level, String password) {
		this.uid = uid;
		this.category = category;
		this.id = id;
		this.uname = uname;
		this.categoryName = categoryName;
		this.level = level;
		this.password = password;
	}

	/** full constructor */
	public User(String uid, Columns columns, Company company,
			Category category, Integer id, String uname, String sex,
			String email, String phoneNumber, String companyName,
			String categoryName, Integer level, String url, String password,
			Integer accountSts, Date accountOpenTime, String accountTime) {
		this.uid = uid;
		this.columns = columns;
		this.company = company;
		this.category = category;
		this.id = id;
		this.uname = uname;
		this.sex = sex;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
		this.categoryName = categoryName;
		this.level = level;
		this.url = url;
		this.password = password;
		this.accountSts = accountSts;
		this.accountOpenTime = accountOpenTime;
		this.accountTime = accountTime;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Columns getColumns() {
		return this.columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccountSts() {
		return this.accountSts;
	}

	public void setAccountSts(Integer accountSts) {
		this.accountSts = accountSts;
	}

	public Date getAccountOpenTime() {
		return this.accountOpenTime;
	}

	public void setAccountOpenTime(Date accountOpenTime) {
		this.accountOpenTime = accountOpenTime;
	}

	public String getAccountTime() {
		return this.accountTime;
	}

	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}

}