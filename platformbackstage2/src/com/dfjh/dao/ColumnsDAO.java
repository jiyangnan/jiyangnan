package com.dfjh.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dfjh.bean.Columns;

/**
 * A data access object (DAO) providing persistence and search support for
 * Columns entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.dfjh.bean.Columns
 * @author MyEclipse Persistence Tools
 */

public class ColumnsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ColumnsDAO.class);
	// property constants
	public static final String ID = "id";
	public static final String CNAME = "cname";
	public static final String CLEVEL = "clevel";
	public static final String STS = "sts";

	protected void initDao() {
		// do nothing
	}

	public void save(Columns transientInstance) {
		log.debug("saving Columns instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Columns persistentInstance) {
		log.debug("deleting Columns instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Columns findById(java.lang.String id) {
		log.debug("getting Columns instance with id: " + id);
		try {
			Columns instance = (Columns) getHibernateTemplate().get(
					"com.dfjh.bean.Columns", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Columns instance) {
		log.debug("finding Columns instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Columns instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Columns as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findById(Object id) {
		return findByProperty(ID, id);
	}

	public List findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List findByClevel(Object clevel) {
		return findByProperty(CLEVEL, clevel);
	}

	public List findBySts(Object sts) {
		return findByProperty(STS, sts);
	}

	public List findAll() {
		log.debug("finding all Columns instances");
		try {
			String queryString = "from Columns";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Columns merge(Columns detachedInstance) {
		log.debug("merging Columns instance");
		try {
			Columns result = (Columns) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Columns instance) {
		log.debug("attaching dirty Columns instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Columns instance) {
		log.debug("attaching clean Columns instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ColumnsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ColumnsDAO) ctx.getBean("ColumnsDAO");
	}
}