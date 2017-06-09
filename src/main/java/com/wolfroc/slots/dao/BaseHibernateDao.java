package com.wolfroc.slots.dao;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wolfroc.slots.exception.DaoException;



public class BaseHibernateDao extends HibernateDaoSupport{
	public BaseHibernateDao(){
	}
	
	private void prepareQuery(HibernateTemplate template,Query queryObject) {
		if (template.isCacheQueries()) {
			queryObject.setCacheable(true);
			if (template.getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(template.getQueryCacheRegion());
			}
		}
		if (template.getFetchSize() > 0) {
			queryObject.setFetchSize(template.getFetchSize());
		}
		if (template.getMaxResults() > 0) {
			queryObject.setMaxResults(template.getMaxResults());
		}
		SessionFactoryUtils.applyTransactionTimeout(queryObject, template.getSessionFactory());
	}


	public void delete(Object entity) throws DaoException {
		try{
			getHibernateTemplate().delete(entity);
		}
		catch(Throwable e){
			throw new DaoException("delete object exception.",e);
		}
	}

	public void deleteAll(Collection<?> entities) throws DaoException {
		try{
			getHibernateTemplate().deleteAll(entities);
		}
		catch(Throwable e){
			throw new DaoException("delete object collection exception.",e);
		}
	}

	public List<?> find(String queryString) throws DaoException {
		
		try{
			return getHibernateTemplate().find(queryString);
		}
		catch(Throwable e){
			throw new DaoException("find object exception.",e);
		}
	}

	public List<?> find(String queryString, Object value) throws DaoException {
		try{
			return getHibernateTemplate().find(queryString,value);
		}
		catch(Throwable e){
			throw new DaoException("find object exception.",e);
		}
	}

	public List<?> find(String queryString, Object[] values) throws DaoException {
		try{
			return getHibernateTemplate().find(queryString,values);
		}
		catch(Throwable e){
			throw new DaoException("find object exception.",e);
		}
	}

	public Object get(Class<?> entityClass, Serializable id) throws DaoException {
		try{
			return getHibernateTemplate().get(entityClass,id);
		}
		catch(Throwable e){
			throw new DaoException("get object exception.",e);
		}
	}

	public Iterator<?> iterate(String queryString) throws DaoException {
		try{
			return getHibernateTemplate().iterate(queryString);
		}
		catch(Throwable e){
			throw new DaoException("iterate object exception.",e);
		}
	}

	
	public Iterator<?> iterate(String queryString, Object value) throws DaoException {
		try{
			return getHibernateTemplate().iterate(queryString,value);
		}
		catch(Throwable e){
			throw new DaoException("iterate object exception.",e);
		}
	}

	
	public Iterator<?> iterate(String queryString, Object[] values) throws DaoException {
		try{
			return getHibernateTemplate().iterate(queryString,values);
		}
		catch(Throwable e){
			throw new DaoException("iterate object exception.",e);
		}
	}

	
	public Object load(Class<?> entityClass, Serializable id) throws DaoException {
		try{
			return getHibernateTemplate().load(entityClass,id);
		}
		catch(Throwable e){
			throw new DaoException("load object exception.",e);
		}
	}

	
	public List<?> loadAll(Class<?> entityClass) throws DaoException {
		try{
			return getHibernateTemplate().loadAll(entityClass);
		}
		catch(Throwable e){
			throw new DaoException("load all object exception.",e);
		}
	}


	
	public Object save(Object entity) throws DaoException {
		try{
			getHibernateTemplate().save(entity);			
		}
		catch(Throwable e){
			throw new DaoException("save object exception.",e);
		}
		return entity;
	}

	
	public Object saveOrUpdate(Object entity) throws DaoException {
		try{
			getHibernateTemplate().saveOrUpdate(entity);	
		}
		catch(Throwable e){
			throw new DaoException("save or update object exception.",e);
		}
		return entity;
	}
	public List<?> saveOrUpdateAll(List<?> entities) throws DaoException {
		try{
			getHibernateTemplate().saveOrUpdateAll(entities);	
		}
		catch(Throwable e){
			throw new DaoException("save or update all object exception.",e);
		}
		return entities;
	}
	
	public Object update(Object entity) throws DaoException {
		try{
			getHibernateTemplate().update(entity);
		}
		catch(Throwable e){
			throw new DaoException("update object exception.",e);
		}
		return entity;
	}
	
	public List<?> find(String queryString,int firstResult,int maxResults) throws DaoException {
		return find(queryString,null,firstResult,maxResults);
	}

	public List<?> find(String queryString, Object value,int firstResult,int maxResults) throws DaoException {
		return find(queryString,new Object[]{value},firstResult,maxResults);
	}

	public List<?> find(final String queryString,final Object[] values,final int firstResult,final int maxResults) throws DaoException {
		try{
			final HibernateTemplate template=getHibernateTemplate();			
			return template.executeFind(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query queryObject = session.createQuery(queryString);
					prepareQuery(template,queryObject);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							queryObject.setParameter(i, values[i]);
						}
					}
					queryObject.setFirstResult(firstResult);
					if(maxResults>0){
						queryObject.setMaxResults(maxResults);
					}					
					return queryObject.list();
				}
			});	
		}
		catch(Throwable e){
			throw new DaoException("find object exception.",e);
		}
	}
	
	public Iterator<?> iterate(String queryString,int firstResult,int maxResults) throws DaoException {
		return iterate(queryString,null,firstResult,maxResults);
	}
	
	public Iterator<?> iterate(String queryString, Object value,int firstResult,int maxResults) throws DaoException {
		return iterate(queryString,new Object[]{value},firstResult,maxResults);
	}
	
	public Iterator<?> iterate(final String queryString,final Object[] values,final int firstResult,final int maxResults) throws DaoException {
		try{
			final HibernateTemplate template=getHibernateTemplate();			
			return (Iterator<?>)template.execute(
					new HibernateCallback<Object>() {
						public Object doInHibernate(Session session) throws HibernateException {
							Query queryObject = session.createQuery(queryString);
							prepareQuery(template,queryObject);
							if (values != null) {
								for (int i = 0; i < values.length; i++) {
									queryObject.setParameter(i, values[i]);
								}
							}
							queryObject.setFirstResult(firstResult);
							if(maxResults>0){
								queryObject.setMaxResults(maxResults);
							}					
							return queryObject.iterate();
						}
				});	
		}
		catch(Throwable e){
			throw new DaoException("iterate object exception.",e);
		}
	}
	
	public int getRecordCount(String sql) throws DaoException {
		try{
			return ((Integer)getHibernateTemplate().iterate(sql).next()).intValue();
		}
		catch(Exception e){
			throw new DaoException("get recorde count exception.",e);
		}
	}
	
	public int getRecordCount(String sql,Object[] args) throws DaoException {
		try{
			return ((Integer)getHibernateTemplate().iterate(sql,args).next()).intValue();
		}
		catch(Exception e){
			throw new DaoException("get recorde count exception.",e);
		}
	}
}
