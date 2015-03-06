/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysRegionDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.persistence.sys.SchemeDispenseDao;


/**
 * SysRegion Dao Impl
 * @author dandyzheng
 *
 */
@Repository("schemeDispenseDao")
public class SchemeDispenseDaoImpl extends AbstractFacade implements SchemeDispenseDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<String> getChildDeptIds(String deptId) {
		@SuppressWarnings("unchecked")
		List<Object> results = findNative("select getDeptChildList("+deptId+")");
		String resultStr = results.get(0).toString();
		Set<String> result = ArrayUtil.toSet(resultStr.split(","));
		if(result.contains("$")){
			result.remove("$");
		}
		return result;
	}
 
}
