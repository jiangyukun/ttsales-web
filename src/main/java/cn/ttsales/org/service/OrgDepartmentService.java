package cn.ttsales.org.service;

import java.util.List;

import cn.ttsales.org.domain.OrgDepartment;

public interface OrgDepartmentService {
    List<OrgDepartment> queryDepartmentsByName(String name);

    void save(OrgDepartment department);

    OrgDepartment getDepartmentByName(String name);
}
