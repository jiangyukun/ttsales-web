package cn.ttsales.org.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.persistence.OrgMemberDao;
import cn.ttsales.work.core.jpa.AbstractFacade;


@Repository("orgMemberDao")
public class OrgMemberDaoImpl extends AbstractFacade  implements OrgMemberDao{
	
	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
