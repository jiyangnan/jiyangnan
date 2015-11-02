package com.dfjh.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Columns entity. @author MyEclipse Persistence Tools
 */

public class Columns implements java.io.Serializable {

	// Fields

	private String columnId;
	private Integer id;
	private String cname;
	private Integer clevel;
	private Integer sts;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Columns() {
	}

	/** minimal constructor */
	public Columns(String columnId, Integer id) {
		this.columnId = columnId;
		this.id = id;
	}

	/** full constructor */
	public Columns(String columnId, Integer id, String cname, Integer clevel,
			Integer sts, Set users) {
		this.columnId = columnId;
		this.id = id;
		this.cname = cname;
		this.clevel = clevel;
		this.sts = sts;
		this.users = users;
	}

	// Property accessors

	public String getColumnId() {
		return this.columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getClevel() {
		return this.clevel;
	}

	public void setClevel(Integer clevel) {
		this.clevel = clevel;
	}

	public Integer getSts() {
		return this.sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}