package com.dfjh.dao;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dfjh.bean.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDAO{

	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
		
	}

}
