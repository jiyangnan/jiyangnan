package com.dfjh.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private String categoryId;
	private Integer id;
	private String categoryName;
	private Set users = new HashSet();

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String categoryId, Integer id, String categoryName) {
		this.categoryId = categoryId;
		this.id = id;
		this.categoryName = categoryName;
	}

	/** full constructor */
	public Category(String categoryId, Integer id, String categoryName,
			Set users) {
		this.categoryId = categoryId;
		this.id = id;
		this.categoryName = categoryName;
		this.users = users;
	}

	// Property accessors

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}