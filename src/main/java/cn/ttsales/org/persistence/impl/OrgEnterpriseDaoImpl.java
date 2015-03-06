package cn.ttsales.org.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.persistence.OrgEnterpriseDao;
import cn.ttsales.work.core.jpa.AbstractFacade;


@Repository("orgEnterpriseDao")
public class OrgEnterpriseDaoImpl extends AbstractFacade implements OrgEnterpriseDao{

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
