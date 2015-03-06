/**
 * Copyright (c) 2012 SK.All rights reserved.
 * @filename AbstractFacade.java
 * @package sk.core.jpa
 * @author dandyzheng
 * @date 2013-3-26
 */
package cn.ttsales.work.core.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author dandyzheng
 *
 */

public abstract class AbstractFacade {
	
	//@PersistenceContext(unitName="MAIN_DATABASE_PER")
	//private EntityManager entityManager;
	
	//@PersistenceContext(unitName="BDS2")
	//protected EntityManager entityManager2;
	
	public AbstractFacade() {
        
    }
	
	protected abstract EntityManager getEntityManager();
	
	public<T> T persist(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}
	

	public<T> List<T> persist(List<T> entitys) {
		for(T entity : entitys){
			getEntityManager().persist(entity);
		}
		return entitys;
	}
	
	public<T> void remove(T entity) {
		getEntityManager().remove(entity);
	}

	public<T> void remove(List<T> entitys) {
		for(T entity : entitys){
			getEntityManager().remove(getEntityManager().merge(entity));
		}
	}

	public<T> T merge(T entity) {
		return getEntityManager().merge(entity);
	}
	
	public<T> List<T> merge(List<T> entitys) {
		for(T entity : entitys){
			getEntityManager().merge(entity);
		}
		return entitys;
	}
	
	public<T> T find(Class<T> entityClass,Serializable entityId){
		return getEntityManager().find(entityClass, entityId);
	}

	@SuppressWarnings("unchecked")
	public<T> T findByField(Class<T> clz, String fieldName, Serializable value) {
		Query query = getEntityManager().createQuery("from " + clz.getName()
				+ " where " + fieldName + " = ?1");

		query.setParameter(1, value);
		List<?> list = query.getResultList();
		
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (T) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public<T> List<T> find(String jpql) {
		Query query = getEntityManager().createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<T> List<T> find(String jpql, Object param) {
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, param);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<T> List<T> find(String jpql, Object param1, Object param2) {
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, param1);
		query.setParameter(2, param2);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<T> List<T> find(String jpql, Object[] params) {
		Query query = getEntityManager().createQuery(jpql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
	
	/**
	 * 查询从start到end的数据
	 * 例如：start = 1 ；end = 10；即查出第1条到第10条，共10条数据
	 * 例如：start = 5 ；end = 7；即查出第5条到第7条，共3条数据
	 * @param jpql
	 * @param start
	 * @param end
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-5-15
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public<T> List<T> findRange(String jpql,int start,int end) {
		Query query = getEntityManager().createQuery(jpql).setFirstResult(start-1).setMaxResults(end-(start-1));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<T> List<T> findRange(String jpql,Object param,int start,int end) {
		Query query = getEntityManager().createQuery(jpql).setFirstResult(start-1).setMaxResults(end-(start-1));
		query.setParameter(1, param);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public<T> List<T> findRange(String jpql,Object param1,Object param2,int start,int end) {
		Query query = getEntityManager().createQuery(jpql).setFirstResult(start-1).setMaxResults(end-(start-1));
		query.setParameter(1, param1);
		query.setParameter(2, param2);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public<T> List<T> findRange(String jpql,Object[] params,int start,int end) {
		Query query = getEntityManager().createQuery(jpql).setFirstResult(start-1).setMaxResults(end-(start-1));
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public<T> PageModel<T> find(String jpql, PageParam pageParam, Object[] params) {
		PageModel pageResult = new PageModel();
		Query query = getEntityManager().createQuery(jpql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		query.setFirstResult(pageParam.getRows() * pageParam.getPage() - pageParam.getRows());
		query.setMaxResults(pageParam.getRows());
		//query.getResultList();
		pageResult.setRows(query.getResultList()); 
		
		query = getEntityManager().createQuery(getQueryCountJpql(jpql));
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		pageResult.setTotal((Long)query.getSingleResult());  
		pageResult.setCurrPage(pageParam.getPage());
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	public<T> List<T> find(String jpql, PageParam pageParam) {
		Query query = getEntityManager().createQuery(jpql);
		query.setFirstResult(pageParam.getRows() * pageParam.getPage() - pageParam.getRows());
		query.setMaxResults(pageParam.getRows());
		return query.getResultList();
	}
	private String getQueryCountsql(String sql){
//		int index = jpql.toUpperCase().indexOf("FROM");
//		return "select count(*) " + jpql.substring(index,jpql.length());
		return "select count(*) from (" + sql+ ") t";
	}
	
	private String getQueryCountJpql(String jpql){
		int index = jpql.toUpperCase().indexOf("FROM");
		return "select count(*) " + jpql.substring(index,jpql.length());
//		return "select count(*) from (" + jpql+ ") t";
	}

	public int execute(String jpql) {
		Query query = getEntityManager().createQuery(jpql);
		return query.executeUpdate();
	}

	public int execute(String jpql, Object param) {
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, param);
		return query.executeUpdate();
	}

	public int execute(String jpql, Object param1, Object param2) {
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, param1);
		query.setParameter(2, param2);
		return query.executeUpdate();
	}

	public int execute(String jpql, Object[] params) {
		Query query = getEntityManager().createQuery(jpql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public List findNative(String sql) {
		Query query = getEntityManager().createNativeQuery(sql);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List findNative(String sql,Object[] params) {
		Query query = getEntityManager().createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public<T> List<T> findNative(String sql,Class<T> entityClass) {
 		Query query = getEntityManager().createNativeQuery(sql, entityClass);
 		return query.getResultList();
	}
	
	/**
	 * 执行原生SQL语句,包括有返回值的存储过程
	 * @param sql 如:"{call init_plan_temp(?,?,?)}" 
	 * @param params new String[]{"1","2013-12-01","2013-12-20"})
	 * @return List<T> 
	 * @author dandyzheng
	 * @date 2013-12-19
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public<T> List<T> findNative(String sql,Object[] params,Class<T> entityClass) {
 		Query query = getEntityManager().createNativeQuery(sql, entityClass);
 		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
 		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public<T> PageModel<T> findNative(String sql, PageParam pageParam, Object[] params,Class<T> entityClass) {
		PageModel<T> pageResult = new PageModel<T>();
		Query query = getEntityManager().createNativeQuery(sql, entityClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		query.setFirstResult(pageParam.getRows() * pageParam.getPage() - pageParam.getRows());
		query.setMaxResults(pageParam.getRows());
		//query.getResultList();
		pageResult.setRows(query.getResultList()); 
		
		query = getEntityManager().createNativeQuery(getQueryCountsql(sql));
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		/* BigInteger t = new BigInteger("333");
		 t.longValue()*/
		pageResult.setTotal(new Long(((BigInteger)query.getSingleResult()).longValue()));  
		pageResult.setCurrPage(pageParam.getPage());
		return pageResult;
	}
	
 	public int executeNative(String sql) {
		Query query = getEntityManager().createNativeQuery(sql);
		return query.executeUpdate();
	}
 	
 	/**
	 * 执行原生SQL语句,包括无返回值的存储过程
	 * @param sql 如:"{call init_plan_temp(?,?,?)}" 
	 * @param params new String[]{"1","2013-12-01","2013-12-20"})
	 * @return 1：成功 
	 * @author dandyzheng
	 * @date 2013-12-19
	 * @see
	 */
 	public int executeNative(String sql,Object[] params) {
		Query query = getEntityManager().createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.executeUpdate();
	}
}
