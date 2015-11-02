package com.dfjh.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private String companyId;
	private Integer id;
	private String companyName;
	private String address;
	private Integer relation;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String companyId, Integer id, String companyName,
			Integer relation) {
		this.companyId = companyId;
		this.id = id;
		this.companyName = companyName;
		this.relation = relation;
	}

	/** full constructor */
	public Company(String companyId, Integer id, String companyName,
			String address, Integer relation, Set users) {
		this.companyId = companyId;
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.relation = relation;
		this.users = users;
	}

	// Property accessors

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRelation() {
		return this.relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}