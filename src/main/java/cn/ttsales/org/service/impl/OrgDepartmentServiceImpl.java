package cn.ttsales.org.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ttsales.org.domain.OrgDepartment;
import cn.ttsales.org.persistence.OrgDepartmentDao;
import cn.ttsales.org.service.OrgDepartmentService;

@Service("orgDepartmentService")
public class OrgDepartmentServiceImpl implements OrgDepartmentService {
	@Autowired
	private OrgDepartmentDao orgDepartmentDao;

	public List<OrgDepartment> queryDepartmentsByName(String name) {
		return orgDepartmentDao.queryDepartmentByName(name);
	}

	public void save(OrgDepartment department) {
		department.setCreateTime(new Date());
		orgDepartmentDao.save(department);
	}

    @Override
    public OrgDepartment getDepartmentByName(String name) {
        return orgDepartmentDao.getDepartmentByName(name);
    }
}
