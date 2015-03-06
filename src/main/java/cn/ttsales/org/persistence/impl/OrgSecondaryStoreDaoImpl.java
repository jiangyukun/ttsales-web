package cn.ttsales.org.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.persistence.OrgSecondaryStoreDao;
import cn.ttsales.work.core.jpa.AbstractFacade;


@Repository("orgSecondaryStoreDao")
public class OrgSecondaryStoreDaoImpl  extends AbstractFacade implements OrgSecondaryStoreDao{

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
