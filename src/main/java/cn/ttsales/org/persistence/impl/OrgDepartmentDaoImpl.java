package cn.ttsales.org.persistence.impl;

/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename OperationScoreDaoImpl.java
 * @package mbk.persistence..impl
 * @author dandyzheng
 * @date 2012-7-27
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgDepartment;
import cn.ttsales.org.persistence.OrgDepartmentDao;
import cn.ttsales.work.core.jpa.AbstractFacade;

/**
 * OperationScore Dao Impl
 *
 * @author dandyzheng
 */
@Repository("orgDepartmentDao")
public class OrgDepartmentDaoImpl extends AbstractFacade implements
        OrgDepartmentDao {

    @PersistenceContext(unitName = "MAIN_DATABASE_PER")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional
    public void save(OrgDepartment department) {
        persist(department);
    }

    public void edit(OrgDepartment department) {
        merge(department);
    }

    @Override
    public List<OrgDepartment> queryDepartmentByName(String name) {
        List<OrgDepartment> departments = super.find("from OrgDepartment d where d.deptName like ?1", "%" + name + "%");
        return departments;
    }

    @Override
    public OrgDepartment getDepartmentByName(String name) {
        List<OrgDepartment> departments = super.find("from OrgDepartment d where d.deptName = ?1", name);
        if (departments != null && departments.size() > 0) {
            return departments.get(0);
        }
        return null;
    }
}
