package cn.ttsales.org.persistence;

import java.util.List;

import cn.ttsales.org.domain.OrgDepartment;

public interface OrgDepartmentDao {

	public void save(OrgDepartment department);

	public void edit(OrgDepartment department);

	public List<OrgDepartment> queryDepartmentByName(String name);

    OrgDepartment getDepartmentByName(String name);
}
